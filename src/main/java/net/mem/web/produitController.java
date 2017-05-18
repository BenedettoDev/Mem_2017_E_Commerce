package net.mem.web;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mem.dao.CategorieRepository;
import net.mem.dao.ProduitRepository;
import net.mem.dao.entities.Ligne;
import net.mem.dao.entities.Produit;

@Controller
@RequestMapping(value = "/Produit")
public class produitController {
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private CategorieRepository categorieRepository;

	@Value("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value = "/Index")
	public String Index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "cat", defaultValue = "-1") String categorie) {

		if (id != null) {
			Produit produit = produitRepository.findOne(id);
			if (produit == null)
				return "redirect:Index";
			model.addAttribute("produit", produit);
			model.addAttribute("ligne",new Ligne());
			return "single-produit";
		}

		Page<Produit> pageProd = null;

		if (!categorie.equals("")) {
			pageProd = produitRepository.chercherProduitsByCategory(categorie, new PageRequest(p, 5));
		} 
		if (!mc.equals("")) {
			pageProd = produitRepository.chercherProduits("%" + mc + "%", new PageRequest(p, 5));
		}
		

		
		int pageCount = pageProd.getTotalPages();
		int[] pages = new int[pageCount];
		for (int i = 0; i < pageCount; i++)
			pages[i] = i;

		model.addAttribute("page", "Produit");
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("pageProduits", (pageProd == null)? produitRepository.findAll() : pageProd);
		model.addAttribute("titre", "Produits");
		model.addAttribute("motCle", mc);
		model.addAttribute("categories", categorieRepository.findAll());

		model.addAttribute("derniersProduits",produitRepository.derniersProduitsAjoute());
		model.addAttribute("suggestion", produitRepository.produitsEnSuggestion());
		model.addAttribute("preparation", produitRepository.produitsPreparation());
		return "produit";
	}
	
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(imageDir+id);
		if(!f.exists()){
			f = new File(imageDir+"defaut");
		}
		return IOUtils.toByteArray(new FileInputStream(f));
	}
}
