package com.dogram.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Get {

	
	
	
	@GetMapping("/")
	public String intro() {
		return "app";
	}
	
	@GetMapping("/#/")
	public String intro2() {
		return "app";
	}
	
	
	@GetMapping("#/feed")
	public String feed() {
		return "app";
	}
	
	@GetMapping("#/auth/login")
	public String login() {
		return "app";
	}
	
	
	@GetMapping("#/auth/join")
	public String join() {
		return "app";
	}
	
	
	@GetMapping("#/store")
	public String store() {
		return "app";
	}
	
//	
	
	
	

	

	
	
	
	

	
	
}
