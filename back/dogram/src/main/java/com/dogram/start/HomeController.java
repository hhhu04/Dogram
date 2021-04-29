package com.dogram.start;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import model.dto.Dto;
import model.dto.MailDto;
import model.dto.UserDto;
import model.dao.UserDao;
import service.MailService;
import service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/user", produces="application/json; charset=utf-8")
//@RequestMapping(value="/user")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "intro";
	}
	
	
	
	@GetMapping("/join")
	public String join() {
		
		return "join";
	}
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("id")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "home";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public int login(@RequestBody UserDto dto, HttpServletResponse response, HttpServletRequest request) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		
		int check = -1;
		
		try {
			
			check = user.login(dto, response, request);
			return check;
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		
	
		
		return check;
	}
	
	
	@PostMapping("/join")
	@ResponseBody
	public UserDto join(@RequestBody UserDto dto) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		
		int check = 0;
		try {
			
			check = user.read(dto);
			
			if(check == 1) {
				user.create(dto);
				return dto;
			}
			
		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return dto;
	}

	
//	@GetMapping("/findid")
//	public String findId() {
//		return "findid";
//	}
//	
//
//	
//	@PostMapping("/findid")
//	@ResponseBody
//	public int findId(@RequestBody MailDto dto) {
//		
////		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
////		MailService mail = ctx.getBean("mail",MailService.class);
//		MailService mail= new MailService();
//		
//		int num = 0;
//		 try {
//			 System.out.println(dto.toString());
//			 System.out.println();
//			 String email = dto.getEmail();
//			 
//			num  = mail.mailSend(email);
//			return num;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return 0;
//		}
//		
//		
//	}
	
	
	
	
}
