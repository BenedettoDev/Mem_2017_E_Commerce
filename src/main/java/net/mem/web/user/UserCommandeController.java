package net.mem.web.user;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import net.mem.dao.entities.Commande.Etat;
import net.mem.dao.entities.Ligne.Id;

@Controller
@RequestMapping(value="/User/Commande")
public class UserCommandeController {

	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired 
	private ProduitRepository produitRepository;
	
	@Autowired
	private ConfectionRepository confectionRepository;
	
	@RequestMapping(value="Index")
	public String panelCommande(Model model,Principal principal) {
		List<Commande> commandesTerminee = commandeRepository.chargerCommandeTermine(principal.getName());
		List<Commande> commandesEnAttente = commandeRepository.chargerCommandeEnAttente(principal.getName());
		if (commandesTerminee.size() != 0){
			model.addAttribute("commandesTerminee", commandesTerminee);
		}
		if (commandesEnAttente.size() != 0){
			model.addAttribute("commandesEnAttente", commandesEnAttente);
		}
		
		return "user/commande";
	}
	
	@RequestMapping(value="ajouterProduit",method = RequestMethod.POST)
	public String ajouterProduit(Principal principal,@Valid Ligne ligne,BindingResult errors) {
		if (errors.hasErrors()) {
			return "redirect:/Produit/Index?id="+ligne.getProduit().getId();
		}
	
		Commande commande = commandeRepository.chargerDerniereCommande(principal.getName());
		if (commande == null) {
			commande = new Commande(utilisateurRepository.findByUserName(principal.getName()));
			commande.setEtat(Etat.EnAttente);
			commande = commandeRepository.save(commande);
		}
		Produit produit = produitRepository.findOne(ligne.getProduit().getId());
		ligne.setId(new Id(commande.getId(), produit.getId()));
		double prix = calculPrixLigne(produit.getPrix(),ligne.getQuantite(),produit.getUnite());
		ligne.setPrix(prix);
		ligne.setCommande(commande);
		commande.getLignes().add(ligne);
		commande.setTotal(commande.caltulMontantTotal());
		commandeRepository.save(commande);
		
//		return "redirect:/Produit/Index?id="+ligne.getProduit().getId();
		return "redirect:/User/Panier";
	}

	private double calculPrixLigne(double prix, int quantite, String unite) {
		double montant = 0;
		switch (unite) {
		case "Kg":
			montant = (prix*quantite)/1000;
			break;
		case "G":
			montant = (prix*quantite)/100;			
			break;
		case "Pce":
			montant =(prix*quantite);
	
		}
		return montant;
	}
	
	

	@RequestMapping(value="commanderPlateau",method = RequestMethod.POST)
	public String commanderPlateau(Principal principal,@Valid Plateau plateau, Model model,BindingResult errors) {
		if (errors.hasErrors()) {
			return "redirect:/Commande/commanderPlateau";
		}
		plateau.setTypePlateau(Plateau.TypePlateau.getEnum(plateau.getTypePlateauString()));
		plateau.setMontant(200.0);
		model.addAttribute("commande", saveConfection(principal, plateau));
		model.addAttribute("plateau",plateau);
		return "commandeAValider";
	}
	
	

	@RequestMapping(value="commanderPanier",method = RequestMethod.POST)
	public String commanderPanier(Principal principal, Panier panier,Model model) {
		model.addAttribute("commande",saveConfection(principal,panier));
		model.addAttribute("panier",panier);
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
	
	@RequestMapping(value="CommandeAValider",method = RequestMethod.POST)
	public String commandeAValider(Principal principal,Long id,Commande commande) {
		Commande  c = commandeRepository.findOne(id) ;
		c.setCommande_prevu_pour(commande.getCommande_prevu_pour());
		c.setEtat(Etat.EnCours);
		commandeRepository.save(c);
		return "redirect:/Index";
	}

	@RequestMapping(value="SupprimerLigne")
	public String deleteLigne(Principal principal, Long id) {
		Commande commande = commandeRepository.chargerDerniereCommande(principal.getName());	
		commandeRepository.supprimerLaLigne(commande.getId(), id);
		commande.setTotal(commande.caltulMontantTotal());
		commandeRepository.save(commande);
		return "redirect:/User/Panier";
	}
	
	@RequestMapping(value="Visualiser")
	public String visualiser(Model model, Long id) {
		Commande commande = commandeRepository.findOne(id);
		model.addAttribute("commande", commande);
		return "user/commandeligne";		
	}
	
	
}
