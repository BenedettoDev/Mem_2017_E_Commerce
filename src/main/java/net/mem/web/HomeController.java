package net.mem.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.mem.dao.UtilisateurRepository;
import net.mem.dao.entities.Utilisateur;

@Controller
public class HomeController {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@RequestMapping(value={"/Index","/"})
	public String home() {
		return "index";
	}
	
	@RequestMapping("/Login")
	public String login () {
		return "login";
	}
	
	@RequestMapping("/Logout")
	public String logout() {
		return "logout";
	}
	
	@RequestMapping(value={"/Inscription"})
	public String inscription(Model model) {
		model.addAttribute("utilisateur",new Utilisateur());
		return "inscription";
	}
	
	@RequestMapping(value="/SaveUtilisateur",method=RequestMethod.POST)
	public String saveUtilisateur(@Valid Utilisateur utilisateur,BindingResult errors){
		if (errors.hasErrors()) {
			return "Inscription";
		}
		Md5PasswordEncoder encryptMD5 = new Md5PasswordEncoder();
		String pass = encryptMD5.encodePassword(utilisateur.getPass(),null);
		utilisateur.setPassword(pass);
		utilisateur.insciption();
		utilisateurRepository.save(utilisateur);
		return "index";
	}
}
