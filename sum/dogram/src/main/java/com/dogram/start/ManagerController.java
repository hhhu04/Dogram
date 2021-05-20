package com.dogram.start;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dto.CommunityDto;
import model.dto.ManagerDto;
import model.dto.StoreDto;
import model.dto.UserDto;
import service.ManagerService;
import service.UserService;

@CrossOrigin
@Controller
@RequestMapping(value="/admin", produces="application/json; charset=utf-8")
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
	public int join(@RequestBody ManagerDto dto, HttpServletResponse response, HttpServletRequest request,@CookieValue(value="id", required=false) Cookie cookie) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		ManagerService manager = ctx.getBean("manager",ManagerService.class);
		
		 HttpSession session = request.getSession();
	     String grade = (String) session.getAttribute("grade");
//		String grade = cookie.getValue();
		
		int check = -1;
		
		if(grade.equals("master")) {
		
		try {
			
			check = manager.create(dto,request);
			return check;
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		}
		
		return -1;
	}

	
	@PostMapping("/user")
	@ResponseBody
	public List<UserDto> user(@RequestBody UserDto dto,@CookieValue(value="grade", required=false) Cookie cookie, HttpServletRequest request) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		ManagerService manager = ctx.getBean("manager",ManagerService.class);
		HttpSession session = request.getSession();
//	    String grade = (String) session.getAttribute("grade");
	    String grade = cookie.getValue();
	    List<UserDto> list = null;
	    
	    if(grade != null) {
	    	try {
				manager.ckeckCookie(grade);
				list = manager.readUser(dto);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		return list;
	}
	
	
	@PostMapping("/feed")
	@ResponseBody
	public List<CommunityDto> feed(@RequestBody CommunityDto dto,@CookieValue(value="grade", required=false) Cookie cookie, HttpServletRequest request) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		ManagerService manager = ctx.getBean("manager",ManagerService.class);
		HttpSession session = request.getSession();
//	    String grade = (String) session.getAttribute("grade");
	    String grade = cookie.getValue();
	    List<CommunityDto> list = null;
	    System.out.println(grade);
	    if(grade != null) {
	    	try {
				manager.ckeckCookie(grade);
				list = manager.readFeed(dto);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		return list;
	}
	
	@PostMapping("/store")
	@ResponseBody
	public List<StoreDto> store(@RequestBody StoreDto dto,@CookieValue(value="grade", required=false) Cookie cookie, HttpServletRequest request) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		ManagerService manager = ctx.getBean("manager",ManagerService.class);
		HttpSession session = request.getSession();
//	    String grade = (String) session.getAttribute("grade");
	    String grade = cookie.getValue();
	    List<StoreDto> list = null;
	    System.out.println(grade);
	    if(grade != null) {
	    	try {
				manager.ckeckCookie(grade);
				list = manager.readStore(dto);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}
