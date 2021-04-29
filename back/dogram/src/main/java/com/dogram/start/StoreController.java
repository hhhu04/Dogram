package com.dogram.start;

import java.sql.SQLException;

import javax.servlet.http.Cookie;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.dto.StoreDto;
import service.CommunityService;
import service.StoreService;

@Controller
@RequestMapping(value="/store", produces="application/json; charset=utf-8")
public class StoreController {
	
	@GetMapping("/store/newproduct")
	public String newPeoduct() {
		return "store";
	}

	
	@PostMapping("newproduct")
	@ResponseBody
	public int newPeoduct(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody StoreDto dto) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService comm = ctx.getBean("store",StoreService.class);
		
		if(cookie.getName().equals("id")){
			try {
				Long userNum = comm.ckeckCookie(cookie.getValue());
				int num = comm.create(dto, userNum);
				
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
		}
		
		
		return -1;
		
		
	}

}
