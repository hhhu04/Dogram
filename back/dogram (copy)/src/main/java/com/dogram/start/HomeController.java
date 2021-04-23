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
import model.dto.UserDto;
import model.dao.UserDao;
import service.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/", produces="application/json; charset=utf-8")
//@RequestMapping(value="/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
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
	
	@PostMapping("/cookie")
	@ResponseBody
	public int cookie(@RequestBody UserDto dto, HttpServletResponse response, HttpServletRequest request) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		User user = ctx.getBean("user",User.class);
		
		int check = -1;
		
		try {
			
			check = user.login(dto, response, request);
			return check;
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		
	
		
		return check;
	}
	
	
	@PostMapping("/register")
	@ResponseBody
	public int register(@RequestBody UserDto dto) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		User user = ctx.getBean("user",User.class);
		
		int check = 0;
		try {
			
			check = user.read(dto);
			
			if(check != -1) {
				user.create(dto);
				return check;
			}
			
		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return check;
	}
	
	
}
