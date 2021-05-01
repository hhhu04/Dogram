package com.dogram.start;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dto.ManagerDto;
import service.ManagerService;
import service.UserService;

@Controller
@RequestMapping(value="/manager", produces="application/json; charset=utf-8")
public class ManagerController {
	
	@GetMapping("/login")
	public String lasd() {
		return "store";
	}
	
	
	@PostMapping("/login")
	@ResponseBody
	public int login(@RequestBody ManagerDto dto, HttpServletResponse response, HttpServletRequest request) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		ManagerService manager = ctx.getBean("manager",ManagerService.class);
		
		int check = -1;
		
		try {
			
			check = manager.login(dto, response, request);
			return check;
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return 0;
	}
	
	@PostMapping("/join")
	@ResponseBody
	public int join(@RequestBody ManagerDto dto, HttpServletResponse response, HttpServletRequest request) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		ManagerService manager = ctx.getBean("manager",ManagerService.class);
		
		int check = -1;
		
		try {
			
			check = manager.create(dto);
			return check;
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return 0;
	}

}
