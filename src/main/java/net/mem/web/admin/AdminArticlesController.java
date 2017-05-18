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
	
	@RequestMapping(value="/Index")
	public String panelArticle(Model model){
		model.addAttribute("articles",articleRepository.findAll());
		return "admin/articles";
	}
	@RequestMapping(value="AjouterArticle",method= RequestMethod.GET)
	public String ajouterArticleForm(Model model) {
		model.addAttribute("article",new Article());
		return "admin/form/articleForm";
	}
	
	@RequestMapping(value="ArticleVisible",method= RequestMethod.GET)
	public String rendreVisible(Model model,Long id) {
		return panelArticle(model);
	}
	@RequestMapping(value="AjouterArticle",method= RequestMethod.POST)
	public String ajouterArticle(Model model, Article article) {
		article.setDate(new Date());
		articleRepository.save(article);
		return panelArticle(model);
	}
	
	@RequestMapping(value="SupprimerArticle")
	public String supprimerArticle (Model model, Long id) {
		articleRepository.delete(articleRepository.findOne(id));
		return panelArticle(model);
	}
}
