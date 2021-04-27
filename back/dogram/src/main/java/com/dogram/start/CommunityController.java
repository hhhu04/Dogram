package com.dogram.start;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dto.CommunityDto;
import service.CommunityService;
import service.UserService;

@Controller
@RequestMapping(value="/", produces="application/json; charset=utf-8")
public class CommunityController {

	@GetMapping("/feed/newfeed")
	public String community() {
		return "newfeed";
	}
	
	@PostMapping("/feed/newfeed")
	@ResponseBody
	public int newfeed(@RequestBody CommunityDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService comm = ctx.getBean("community",CommunityService.class);
		
		if(cookie !=null){
			try {
				Long userNum = comm.ckeckCookie(cookie.getValue());
//				Long userNum = comm.ckeckCookie("hhh");
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
