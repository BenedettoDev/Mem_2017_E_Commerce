package net.mem.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Admin")
public class AdminContoller {
	
	@Autowired
	@RequestMapping(value="/Index")
	public String Index() {
		return "admin/admin";
	}
	
	@RequestMapping(value="/Loggin")
	public String loggin(){
		return "admin/loggin";
	}
	
	@RequestMapping(value="/Logout")
	public String logout() {
		return "index";
	}
	
	
	
	
	
	

	
}
