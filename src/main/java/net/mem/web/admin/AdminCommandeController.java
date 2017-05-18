package net.mem.web.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mem.dao.CommandeRepository;
import net.mem.dao.entities.Commande;
import net.mem.dao.entities.Commande.Etat;

@Controller
@RequestMapping(value="/Admin/Commande")
public class AdminCommandeController {
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@RequestMapping(value="/Index")
	public String panelCommande(Model model){
		model.addAttribute("commandesJour", commandeRepository.commandeDuJour());
		List<Commande> commandes = commandeRepository.commandesSemaine();
		if (commandes.size() != 0){
			model.addAttribute("commandesSemaine", commandes);
		}
		
		commandes = commandeRepository.chargerCommandeTermine();
		if (commandes.size() != 0){
			System.out.println("perdu");
		}
		model.addAttribute("commandesTerminee",commandes);
		return "admin/commande";
	}
	
	@RequestMapping(value="Visualiser")
	public String visualiser(Model model, Long id) {
		Commande commande = commandeRepository.findOne(id);
		model.addAttribute("commande", commande);
		return "admin/commandeligne";		
	}
	
	@RequestMapping(value="Terminer")
	public String terminer(Model model,Long id) {
		Commande commande = commandeRepository.findOne(id);
		commande.setEtat(Etat.terminee);
		commande.setDate_fin(new Date());
		commandeRepository.save(commande);
		return panelCommande(model);
	}

}
