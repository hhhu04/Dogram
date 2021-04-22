package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class UserController {
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@ResponseBody
	@PostMapping("/register")
	public String register() {
		return null;
	}
	
	@GetMapping("/hi")
	public String hi() {
		return "hi";
	}
	

}
