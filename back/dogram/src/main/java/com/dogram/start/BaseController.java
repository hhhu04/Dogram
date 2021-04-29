package com.dogram.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

	@GetMapping("/store/newproduct")
	public String newPeoduct() {
		return "store";
	}

	
}
