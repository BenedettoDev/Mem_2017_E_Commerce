package net.mem.web.admin;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mem.dao.ProduitRepository;
import net.mem.dao.entities.Produit;

@Controller
@RequestMapping(value="/Admin/Suggestion")
public class AdminSuggestionController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/Index")
	public String panelSuggestion(Model model){
		model.addAttribute("suggestion",produitRepository.produitsEnSuggestion());
		return "admin/suggestion";
	}
	
	@RequestMapping(value="/produitASuggerer")
	public String suggererProduit(Model model, Long id) {
		Produit prod = produitRepository.findOne(id);
		prod.setSuggestion(true);
		produitRepository.save(prod);
		return panelSuggestion(model);
	}
	
	@RequestMapping(value="SupprimerProduitSuggerer")
	public String deleteProduit(Model model, Long id) {
		Produit prod = produitRepository.findOne(id);
		prod.setSuggestion(false);
		produitRepository.save(prod);
		 return panelSuggestion(model);
	}

}
