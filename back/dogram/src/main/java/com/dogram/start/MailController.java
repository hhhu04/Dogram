package com.dogram.start;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dto.MailDto;
import service.MailService;

@Controller
@RequestMapping(value="/", produces="application/json; charset=utf-8")
public class MailController {

	
	@GetMapping("/user/findid")
	public String findId() {
		return "findid";
	}
	

	
	@PostMapping("/user/findid")
	@ResponseBody
	public int findId(@RequestBody MailDto dto) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		MailService mail = ctx.getBean("mail",MailService.class);
//		MailService mail= new MailService();
		int num = 0;
		 try {
			 
			num  = mail.mailSend(dto);
			return num;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 111;
		}
		
		
	}
	
	
}
