package net.mem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.mem.dao.ArticleRepository;
import net.mem.dao.UtilisateurRepository;
import net.mem.dao.entities.Utilisateur;

@Controller
@RequestMapping(value="/Actualite")
public class ActualiteController {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	/**
	 * Permet de récupérer la liste des actualités et retourne les informations via le model
	 * pour compléter la page html actualite  
	 * 
	 * @param model
	 * @return nom de la page html
	 */
	@RequestMapping(value={"/Index","/"})
	public String panelActualite(Model model) {
		model.addAttribute("listActualite",articleRepository.findArticleVisible());
		return "actualite";
	}
	
	
}
