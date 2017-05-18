package net.mem.web.admin;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mem.dao.ProduitRepository;
import net.mem.dao.entities.Produit;

@Controller
@RequestMapping(value="/Admin/Preparation")
public class AdminPreparationController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/Index")
	public String panelPreparation(Model model){
		model.addAttribute("preparation",produitRepository.produitsPreparation());
		return "admin/preparation";
	}
	

	@RequestMapping(value="SupprimerProduitPrepare")
	public String deleteProduit(Model model, Long id) {
		Produit prod = produitRepository.findOne(id);
		prod.setPreparation(false);
		produitRepository.save(prod);
		 return panelPreparation(model);
	}

}
