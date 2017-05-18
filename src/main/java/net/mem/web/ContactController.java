package net.mem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Contact")
public class ContactController {

	@RequestMapping(value = "/Index")
	public String Index(Model model) {
		model.addAttribute("page", "Contact");
		return "contact";
	}

}
