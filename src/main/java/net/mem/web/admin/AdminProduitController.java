package net.mem.web.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.mem.dao.AllergeneRepository;
import net.mem.dao.CategorieRepository;
import net.mem.dao.LieuRepository;
import net.mem.dao.ProduitRepository;
import net.mem.dao.entities.Allergene;
import net.mem.dao.entities.Categorie;
import net.mem.dao.entities.Lieu;
import net.mem.dao.entities.Produit;


@Controller
@RequestMapping(value="/Admin/Produit")
public class AdminProduitController {
	@Autowired 
	private ProduitRepository produitRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private AllergeneRepository allergeneRepository;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value="/Index")
	public String Index(Model model,@RequestParam(name="page",defaultValue="0")int p,@RequestParam(name="motCle",defaultValue="")String mc){
		Page<Produit> pageProd =  produitRepository.chercherProduits("%"+mc+"%",new PageRequest(p, 5));
		
		int pageCount = pageProd.getTotalPages();
		int []pages=new int[pageCount];
		for(int i=0;i<pageCount;i++)pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pageCourante",p);
		model.addAttribute("pageProduits",pageProd);
		model.addAttribute("titre","Produits");
		model.addAttribute("motCle",mc);
	
		return "admin/produit";
	}
	
	@RequestMapping(value="/Form",method=RequestMethod.GET)
	public String formProduit(Model model) {
		List <Lieu> lieux = lieuRepository.findAll();
		List <Categorie> categories = categorieRepository.findAll();
		Map<String, String> unites = (Map<String, String>) new HashMap<String,String>();
		unites.put("Pce","Pièce");
		unites.put("Kg","Kilogramme");
		unites.put("G","Gramme");
				
		//pour le select
		model.addAttribute("lieux", lieux);
		//pour insert un nouveau lieu
		model.addAttribute("lieu",new Lieu());
		model.addAttribute("unites", unites);
		model.addAttribute("categories",categories);
		model.addAttribute("produit",new Produit());
	
		model.addAttribute("allergenes",allergeneRepository.findAll());
		return "admin/form/formProduit";
	}
	
	@RequestMapping(value="/GestionLieu",method=RequestMethod.GET)
	public String gestionLieu(Model model) {
		List<Lieu> lieux = lieuRepository.findAll(); 
		model.addAttribute("listeLieu",lieux);
		model.addAttribute("lieu",new Lieu());
		return "admin/GestionLieu";
	}
	
	@RequestMapping(value="/GestionCategorie",method=RequestMethod.GET)
	public String panelCategorie(Model model) {
		List<Categorie> categorie = categorieRepository.findAll(); 
		model.addAttribute("listeCategorie",categorie);
		model.addAttribute("categorie",new Categorie());
		return "admin/GestionCategorie";
	}
	
	@RequestMapping(value="/GestionAllergene",method=RequestMethod.GET)
	public String gestionAllergene(Model model) {
		List<Allergene> allergenes = allergeneRepository.findAll();
		System.out.println(allergenes.size());
		model.addAttribute("listeAllergene", allergenes);
		model.addAttribute("allergene",new Allergene());
		return "admin/GestionAllergene";
		
	}
	
	@RequestMapping(value="SaveAllergene",method=RequestMethod.POST)
	public String saveAllergene(Model model,Allergene allergene,BindingResult bindingResult) {
		allergeneRepository.save(allergene);
		return gestionAllergene(model);
	}
	
	@RequestMapping(value="SaveCategorie",method=RequestMethod.POST)
	public String saveCategorie(Model model,Categorie categorie, BindingResult bindingResult){
		categorieRepository.save(categorie);
		return panelCategorie(model);
	}
	
	@RequestMapping(value="SaveLieu",method=RequestMethod.POST)
	public String saveLieu(Model model,Lieu lieu, BindingResult bindingResult){
		lieuRepository.save(lieu);
		return gestionLieu(model);
	}
	
	
	
	@RequestMapping(value="SaveProduit",method=RequestMethod.POST)
	public String saveProduit( Produit prod, BindingResult bindingResult,@RequestParam(name="img_name")MultipartFile file) throws Exception{
		if (bindingResult.hasErrors()){
			return "admin/formProduit";
		}
		
		// Enregistrement du nom originale dans la db
		if (!file.isEmpty()) {
			prod.setImg(file.getOriginalFilename());
		} else {
			prod.setImg("defaut");
		}
		produitRepository.save(prod);
		
		// Telechargement de l'image dans un dossier
		if (!file.isEmpty()) {
			file.transferTo(new File(imageDir+"/"+prod.getId()));
		}
		return "redirect:Index";
	}
	
	@RequestMapping(value="SupprimerProduit")
	public String deleteProduit(Long id) {
		produitRepository.delete(id);
		File file = new File(imageDir+"/"+id);
		file.delete();
		 return "redirect:Index";
	}
	
	@RequestMapping(value="SupprimerLieu")
	public String deleteLieu(Long id) {
		lieuRepository.delete(id);
		 return "redirect:GestionLieu";
	}
	
	@RequestMapping(value="Editer")
	public String edit(Long id,Model model){
		List <Lieu> lieux = lieuRepository.findAll();
		List <Categorie> categories = categorieRepository.findAll();				
		Map<String, String> unites = (Map<String, String>) new HashMap<String,String>();
		unites.put("Pce","Pièce");
		unites.put("Kg","Kilogramme");
		unites.put("G","Gramme");
		
		
		Produit prod = produitRepository.getOne(id);
		model.addAttribute("produit",prod);
		model.addAttribute("lieux",lieux);
		model.addAttribute("unites",unites);
		model.addAttribute("categories",categories);
		return "admin/EditProduit";

	}
	
	@RequestMapping(value="Update",method=RequestMethod.POST)
	public String update(@Valid Produit prod, BindingResult bindingResult,@RequestParam(name="img_name")MultipartFile file) throws Exception{
		if (bindingResult.hasErrors()){
			return "admin/EditProduit";
		}
		
		if (!file.isEmpty()) {
			prod.setImg(file.getOriginalFilename());
			file.transferTo(new File(imageDir+"/"+prod.getId()));
		}
		produitRepository.save(prod);
		 return "redirect:Index";
	}
}
