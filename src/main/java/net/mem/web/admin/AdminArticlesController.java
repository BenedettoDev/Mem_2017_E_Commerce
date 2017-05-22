package net.mem.web.admin;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.mem.dao.ArticleRepository;
import net.mem.dao.ProduitRepository;
import net.mem.dao.entities.Article;

@Controller
@RequestMapping(value="/Admin/Article")
public class AdminArticlesController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * Permet à l'administrateur de visualiser ses articles via un panel de gestion des
	 * articles.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/Index")
	public String panelArticle(Model model){
		model.addAttribute("articles",articleRepository.findAll());
		return "admin/articles";
	}
	
	/**
	 * Methode get permettant de rediriger l'administrateur vers le formulaire des articles 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="AjouterArticle",method= RequestMethod.GET)
	public String ajouterArticleForm(Model model) {
		model.addAttribute("article",new Article());
		model.addAttribute("produits",produitRepository.findAll());
		return "admin/form/articleForm";
	}

	/**
	 * Utilise le formulaire d'ajout pour éditer un article
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="EditerArticle",method= RequestMethod.GET)
	public String ajouterArticleForm(Model model,Long id) {
		model.addAttribute("article",articleRepository.findOne(id));
		model.addAttribute("produits",produitRepository.findAll());
		return "admin/form/articleForm";
	}
	
	
	/**
	 * L'ajout de l'article et ça date de création.
	 * @param model
	 * @param article
	 * @return
	 */
	@RequestMapping(value="AjouterArticle",method= RequestMethod.POST)
	public String ajouterArticle(Model model, Article article) {
		System.out.println(article.toString());
		article.setDate(new Date());
		articleRepository.save(article);
		return panelArticle(model);
	}
	
	
	
	
	
	/**
	 * Supprime définitivement l'article de l'application
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="SupprimerArticle")
	public String supprimerArticle (Model model, Long id) {
		articleRepository.delete(articleRepository.findOne(id));
		return panelArticle(model);
	}
	/**
	 * L'article est rendu visible pour tous 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value="RendreVisibleArticle") 
	public String visibleArticle(Model model,Long id) {
		Article art = articleRepository.findOne(id);
		if (art != null) {
			art.setVisible(true);
			articleRepository.save(art);
		}
		return panelArticle(model);
	}
	/**
	 * L'article n'est pas rendu visible au public (visiteur et utilisateur). 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value="RendreNonVisibleArticle") 
	public String nonVisibleArticle(Model model,Long id) {
		Article art = articleRepository.findOne(id);
		if (art != null) {
			art.setVisible(false);
			articleRepository.save(art);
		}
		return panelArticle(model);
	}
}
