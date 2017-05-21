package net.mem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mem.dao.entities.Commande;
import net.mem.dao.entities.Panier;
import net.mem.dao.entities.Plateau;

@Controller
@RequestMapping(value="/Commande")
public class CommandeController {

	@RequestMapping(value="/Index")
	public String index(Model model){
		model.addAttribute("page", "Commande");
		return "commande";
	}
	
	@RequestMapping(value="/commanderPlateau")
	public String commanderPlateau(Model model){
		model.addAttribute("plateau", new Plateau());
		model.addAttribute("typePlateau",Plateau.TypePlateau.values());
		return index(model);
	}
	
	@RequestMapping(value="/commanderPanier")
	public String commanderPanier(Model model){
		model.addAttribute("panier", new Panier());
		return index(model);
	}
}
