package com.dogram.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/community/*", produces="application/json; charset=utf-8")
public class CommunityController {

	@GetMapping("/community")
	public String community() {
		return "community";
	}
	
	
}
