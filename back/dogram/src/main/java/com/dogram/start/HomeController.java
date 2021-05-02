package com.dogram.start;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import model.dto.Dto;
import model.dto.LikeListDto;
import model.dto.MailDto;
import model.dto.UserDto;
import model.dao.UserDao;
import service.LikeListService;
import service.MailService;
import service.UserService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@Controller
@RequestMapping(value="/user", produces="application/json; charset=utf-8")
//@RequestMapping(value="/user")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@GetMapping("/message")
	public String message() {
		return "chat";
	}
	
	
	@GetMapping("/intro")
	public String intro() {
		return "intro";
	}
	
	
	@GetMapping("/logout")
	public int logout(HttpServletResponse response, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
//		int num =0;
//		for(Cookie cookie : cookies)
//		{
//			if(cookie.getName().equals("id")) {
//				cookie.setMaxAge(0);
//				response.addCookie(cookie);
//				num--;
//			}
//		}	
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		session.invalidate();
		
		System.out.println("logout");
		return 1;
//		if(num < 0) return 1;
//		else return -1;
	}
	
	@GetMapping("login")
	public String ligin() {
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody UserDto dto, HttpServletResponse response, HttpServletRequest request) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		
		int check = -1;
		
		try {
			
			
			
			check = user.login(dto, response, request);
			return Integer.toString(check);
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		
	
		
		return Integer.toString(check);
	}
	
	
	@PostMapping("/join")
	@ResponseBody
	public String join(@RequestBody UserDto dto) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		
		int check = -1;
		try {
			
			check = user.read(dto);
			
			if(check == 1) {
				user.create(dto);
				return Integer.toString(check);
			}
			
		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return Integer.toString(check);
	}

	

	@PostMapping("/update")
	@ResponseBody
	public String update(@CookieValue(value="id", required=false) Cookie cookie, @RequestBody UserDto dto,HttpServletResponse response, HttpServletRequest request) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		int num = -1;
		
		 HttpSession session = request.getSession();
		 session.setAttribute("id", dto.getId());
		 String id = (String) session.getAttribute("id");
		
		
//		if(cookie.getName() != null) {
//			if(cookie.getValue().equals("탈퇴한 회원입니다.")) return Integer.toString(num);
			
			try {
//				num = user.update(dto,cookie.getValue());
				num = user.update(dto,id);
				return Integer.toString(num);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		
		
		return Integer.toString(num);
	}
	
	
	@PostMapping("/delete")
	@ResponseBody
	public String delete(@CookieValue(value="id", required=false) Cookie cookie, @RequestBody UserDto dto,HttpServletResponse response, HttpServletRequest request) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		int num = -1;
		
		 HttpSession session = request.getSession();
		 session.setAttribute("id", dto.getId());
		 String id = (String) session.getAttribute("id");
		
		if(cookie.getName() != null) {
//			if(cookie.getValue().equals("탈퇴한 회원입니다.")) return Integer.toString(num);
			if(id.equals("탈퇴한 회원입니다.")) return Integer.toString(num);
			
			try {
//				num = user.delete(dto,cookie.getValue());
				num = user.delete(dto,id);
//				Cookie[] cookies = request.getCookies();
//				for(Cookie cookiee : cookies)
//				{
//					if(cookiee.getName().equals("id")) {
//						cookiee.setMaxAge(0);
//						response.addCookie(cookiee);
//					}
//				}
//				return Integer.toString(num);
				
				session.invalidate();
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return Integer.toString(num);
	}
	
	
	@PostMapping("/mypage")
	@ResponseBody
	public UserDto myPag(@RequestBody UserDto dto,@CookieValue(value="id", required=false) Cookie cookie,HttpServletResponse response, HttpServletRequest request) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		
		int num = -1;
		
		 HttpSession session = request.getSession();
		 session.setAttribute("id", dto.getId());
		 String id = (String) session.getAttribute("id");
		
		
		if(cookie.getName() != null) {
			try {
//				user.myPage(dto,cookie.getValue());
				user.myPage(dto,id);
				return dto;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	@PostMapping("/likeList")
	@ResponseBody
	public List<LikeListDto> likeList(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody LikeListDto dto,HttpServletResponse response, HttpServletRequest request){
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		LikeListService like = ctx.getBean("likeList",LikeListService.class);
		
		UserDto userDto=new UserDto();
		 HttpSession session = request.getSession();
		 session.setAttribute("id", userDto.getId());
		 String id = (String) session.getAttribute("id");
		
		List<LikeListDto> list = null;
		Long uNum;
		try {
//			uNum = like.ckeckCookie(cookie.getValue());
			uNum = like.ckeckCookie(id);
			dto.setUserNum(uNum);
			list = like.read(dto);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	
	
	
	
	
}
