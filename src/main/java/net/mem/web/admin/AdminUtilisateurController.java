package net.mem.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mem.dao.UtilisateurRepository;

@Controller
@RequestMapping(value = "/Admin/Utilisateur")
public class AdminUtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@RequestMapping(value="Index")
	public String listeUtilisateur(Model model) {
		model.addAttribute("utilisateurs",utilisateurRepository.findAll());
		return "admin/utilisateur";
	}
	
	@RequestMapping(value="Editer")
	public String editerUtilisateur(Model model, String username) {
		model.addAttribute("utilisateur",utilisateurRepository.findByUserName(username));
		return "admin/formUtilisateur";
	}
	
	@RequestMapping(value="Supprimer")
	public String supprimerUtilisateur(Model model, String username) {
		utilisateurRepository.delete(utilisateurRepository.findByUserName(username));
		return listeUtilisateur(model);
	}

}
