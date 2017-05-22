package net.mem.web.user;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.mem.dao.CommandeRepository;
import net.mem.dao.UtilisateurRepository;
import net.mem.dao.entities.Commande;
import net.mem.dao.entities.Commande.Etat;
import net.mem.dao.entities.Utilisateur;

@Controller
@RequestMapping(value="/User")
public class UserUtilisateurController {
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	@RequestMapping(value="/Index")
	public String Index() {
		return "user/index";
	}
	
	@RequestMapping(value="/Panier", method = RequestMethod.GET)
	public String panier(Principal principal,Model model) {
		Commande lastCommande = commandeRepository.chargerDerniereCommande(principal.getName());
		if (lastCommande != null){
			if (lastCommande.getLignes() != null) {
				model.addAttribute("ligne",lastCommande.getLignes());
			}
			model.addAttribute("commande", lastCommande);
		}
		return "user/panier/panier";
	}
	
	@RequestMapping(value="/comfirmerCommande",method = RequestMethod.POST)
	public String confirmation(Principal principal,Model model,Commande commandeForm,Long id) {
		Commande commande = commandeRepository.findOne(id);
		if (commande.getLignes().size() == 0) {
			return panier(principal, model);
		}
		commande.setEtat(Etat.EnCours);
		commande.setDate_fin(new Date());
		commande.setCommande_prevu_pour(commandeForm.getCommande_prevu_pour());
		commandeRepository.save(commande);
		return panier(principal, model);
	}
	@RequestMapping(value="/Compte/Index", method = RequestMethod.GET)
	public String panelCompte(Model model,Principal principal) {
		model.addAttribute("compte", utilisateurRepository.findOne(principal.getName()));
		return "user/compte";
	}
	
	@RequestMapping(value="/Compte/EditerUtilisateur", method = RequestMethod.POST)
	public String editerCompte (Model model,Principal principal,Utilisateur utilisateur) {
		Utilisateur u = utilisateurRepository.findOne(principal.getName());
		u.setTelephone(utilisateur.getTelephone());
		u.setMail(utilisateur.getMail());
		u.setAdresse(utilisateur.getAdresse());
		utilisateurRepository.save(u);
		return panelCompte(model, principal);
	}
}
