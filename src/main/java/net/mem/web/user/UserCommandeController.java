package net.mem.web.user;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.mem.dao.CommandeRepository;
import net.mem.dao.ConfectionRepository;
import net.mem.dao.ProduitRepository;
import net.mem.dao.UtilisateurRepository;
import net.mem.dao.entities.Commande;
import net.mem.dao.entities.Confection;
import net.mem.dao.entities.Ligne;
import net.mem.dao.entities.Panier;
import net.mem.dao.entities.Plateau;
import net.mem.dao.entities.Produit;
import net.mem.dao.entities.Utilisateur;
import net.mem.web.CommandeController;
import net.mem.dao.entities.Commande.Etat;
import net.mem.dao.entities.Ligne.Id;

@Controller
@RequestMapping(value = "/User/Commande")
public class UserCommandeController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ConfectionRepository confectionRepository;

	@RequestMapping(value = "Index")
	public String panelCommande(Model model, Principal principal) {
		List<Commande> commandesTerminee = commandeRepository.chargerCommandeTermine(principal.getName());
		List<Commande> commandesEnAttente = commandeRepository.chargerCommandeEnAttente(principal.getName());
		if (commandesTerminee.size() != 0) {
			model.addAttribute("commandesTerminee", commandesTerminee);
		}
		if (commandesEnAttente.size() != 0) {
			model.addAttribute("commandesEnAttente", commandesEnAttente);
		}

		return "user/commande";
	}

	@RequestMapping(value = "ajouterProduit", method = RequestMethod.POST)
	public String ajouterProduit(Principal principal, @Valid Ligne ligne, BindingResult errors) {
		if (errors.hasErrors()) {
			return "redirect:/Produit/Index?id=" + ligne.getProduit().getId();
		}

		Commande commande = commandeRepository.chargerDerniereCommande(principal.getName());
		if (commande == null) {
			commande = new Commande(utilisateurRepository.findByUserName(principal.getName()));
			commande.setEtat(Etat.EnAttente);
			commande = commandeRepository.save(commande);
		}
		Produit produit = produitRepository.findOne(ligne.getProduit().getId());
		ligne.setId(new Id(commande.getId(), produit.getId()));
		double prix = calculPrixLigne(produit.getPrix(), ligne.getQuantite(), produit.getUnite());
		ligne.setPrix(prix);
		ligne.setCommande(commande);
		commande.getLignes().add(ligne);
		commande.setTotal(commande.caltulMontantTotal());
		commandeRepository.save(commande);

		// return "redirect:/Produit/Index?id="+ligne.getProduit().getId();
		return "redirect:/User/Panier";
	}

	private double calculPrixLigne(double prix, int quantite, String unite) {
		double montant = 0;
		switch (unite) {
		case "Kg":
			montant = (prix * quantite) / 1000;
			break;
		case "G":
			montant = (prix * quantite) / 100;
			break;
		case "Pce":
			montant = (prix * quantite);

		}
		return montant;
	}

	@RequestMapping(value = "commanderPlateau", method = RequestMethod.POST)
	public String commanderPlateau(@Valid Plateau plateau, BindingResult errors, Principal principal, Model model) {

		if (plateau.getTypePlateauString().equals("Type de repas")) {
			FieldError error = new FieldError("typePlateauString", "typePlateauString",
					"Vous devez sélectionner un type de plateau");
			errors.addError(error);
		}
		if (errors.hasErrors()) {
			return "commande";
		}
		Utilisateur u = utilisateurRepository.findByUserName(principal.getName());
		if (u.getTelephone() == null || u.getAdresse() == null) {
			model.addAttribute("utilisateur", u);
		}

		plateau.setTypePlateau(Plateau.TypePlateau.getEnum(plateau.getTypePlateauString()));
		plateau.setMontant(Plateau.TypePlateau.getEnum(plateau.getTypePlateauString()).getPrix() * plateau.getNbPers());
		model.addAttribute("commande", saveConfection(principal, plateau));
		model.addAttribute("plateau", plateau);
		return "commandeAValider";
	}

	@RequestMapping(value = "commanderPanier", method = RequestMethod.POST)
	public String commanderPanier(@Valid Panier panier, BindingResult errors, Principal principal, Model model) {
		if (errors.hasErrors()) {
			return "commande";
		}
		model.addAttribute("commande", saveConfection(principal, panier));
		model.addAttribute("panier", panier);
		return "commandeAValider";
	}

	private Commande saveConfection(Principal principal, Confection conf) {
		confectionRepository.save(conf);
		Commande commande = new Commande(utilisateurRepository.findByUserName(principal.getName()));
		commande.setEtat(Etat.EnAttente);
		commande.setConfection(conf);
		commandeRepository.save(commande);
		return commande;
	}

	@RequestMapping(value = "CommandeAValider")
	public String commandeValid(Principal principal, Long id, @Valid Commande commande, BindingResult errors,
			Model model) {
		return "commandeAValider";
	}

	@RequestMapping(value = "CommandeAValider", method = RequestMethod.POST)
	public String commandeAValider(Principal principal, Long id, @Valid Commande commande, BindingResult errors,
			Model model) {
		if (commande.getCommande_prevu_pour() == null) {
			FieldError error = new FieldError("commande_prevu_pour", "commande_prevu_pour",
					"Vous devez sélectionner une date");
			errors.addError(error);
		}
		
		if (errors.hasErrors()) {
			return commandeValid(principal, id, commande, errors, model);

		}
		
		Utilisateur u = utilisateurRepository.findByUserName(principal.getName());
		if (u.getTelephone() == null || u.getAdresse() == null) {
			u.setTelephone(commande.getUtilisateur().getTelephone());
			u.setAdresse(commande.getUtilisateur().getAdresse());
			utilisateurRepository.save(u);
		}
		
		Commande c = commandeRepository.findOne(id);
		c.setCommande_prevu_pour(commande.getCommande_prevu_pour());
		c.setEtat(Etat.EnCours);
		commandeRepository.save(c);
		return "redirect:/User/Commande/Index";
	}

	@RequestMapping(value = "SupprimerLigne")
	public String deleteLigne(Principal principal, Long id) {
		Commande commande = commandeRepository.chargerDerniereCommande(principal.getName());
		commandeRepository.supprimerLaLigne(commande.getId(), id);
		commande.setTotal(commande.caltulMontantTotal());
		commandeRepository.save(commande);
		return "redirect:/User/Panier";
	}

	@RequestMapping(value = "Visualiser")
	public String visualiser(Model model, Long id) {
		Commande commande = commandeRepository.findOne(id);
		model.addAttribute("commande", commande);
		return "user/commandeligne";
	}

}
