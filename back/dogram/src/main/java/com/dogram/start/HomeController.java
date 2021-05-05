package com.dogram.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.Multipart;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.Getter;
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import model.dto.MailDto;
import model.dto.StoreDto;
import model.dto.UserDto;
import model.dto.WantListDto;
import model.dao.UserDao;
import service.CommunityService;
import service.LikeListService;
import service.MailService;
import service.StoreService;
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
	
	
	
	@GetMapping("/file")
	public String file() {
		return "file";
	}
	
	
	
	
	@RequestMapping("/join2")
	@ResponseBody
	public int join2(@RequestParam("file") MultipartFile img, HttpServletRequest file,Model model,ModelAndView mv) {
		System.out.println("123");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		UserDto dto = new UserDto();
		
		dto.setId(file.getParameter("id"));
		dto.setPassword(file.getParameter("password"));
		dto.setEmail(file.getParameter("email"));
		dto.setName(file.getParameter("name"));
		dto.setAddress(file.getParameter("address"));
		dto.setPhoneNumber(file.getParameter("phoneNumber"));
		
		int check = -1;
		
		try {
			dto.setImg(user.upload(img, mv,model));
			check = user.read(dto);
			if(check == 1) {
				return user.create(dto);
			}
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(file.getParameter("id"));
		System.out.println(file.getParameter("password"));
		
		

		
		return check;
	}
	
	
	
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
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
//	@PostMapping("/join")
//	@ResponseBody
//	public int join(@RequestBody UserDto dto) {
//		System.out.println("123");
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
//		UserService user = ctx.getBean("user",UserService.class);
//		
//	
//		int check = -1;
//		try {
//			
//			check = user.read(dto);
//			
//			if(check == 1) {
//				return user.create(dto);
//			}
//			
//		}catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//		return check;
//	}

	

	@RequestMapping("/update")
	@ResponseBody
	public int update(@CookieValue(value="id", required=false) Cookie cookie,HttpServletResponse response, 
			HttpServletRequest request,@RequestParam("file") MultipartFile img, HttpServletRequest file,Model model,ModelAndView mv) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		UserService user = ctx.getBean("user",UserService.class);
		int num = -1;
		
		UserDto dto = new UserDto();
		
		 HttpSession session = request.getSession();
		 session.setAttribute("id", dto.getId());
		 String id = (String) session.getAttribute("id");
//		if(cookie.getName() != null) {
//			if(cookie.getValue().equals("탈퇴한 회원입니다.")) return Integer.toString(num);
			try {
				
				dto.setId(file.getParameter("id"));
				dto.setPassword(file.getParameter("password"));
				dto.setEmail(file.getParameter("email"));
				dto.setName(file.getParameter("name"));
				dto.setAddress(file.getParameter("address"));
				dto.setPhoneNumber(file.getParameter("phoneNumber"));
				dto.setImg(user.upload(img, mv,model));
				
				num = user.update(dto,id);
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//		}
			catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return num;
	}
	
//	@PostMapping("/update")
//	@ResponseBody
//	public String update(@CookieValue(value="id", required=false) Cookie cookie, @RequestBody UserDto dto,HttpServletResponse response, HttpServletRequest request) {
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
//		UserService user = ctx.getBean("user",UserService.class);
//		int num = -1;
//		
//
//		
//		 HttpSession session = request.getSession();
//		 session.setAttribute("id", dto.getId());
//		 String id = (String) session.getAttribute("id");
//		
//		
////		if(cookie.getName() != null) {
////			if(cookie.getValue().equals("탈퇴한 회원입니다.")) return Integer.toString(num);
//			
//			try {
////				num = user.update(dto,cookie.getValue());
//				num = user.update(dto,id);
//				return Integer.toString(num);
//			} catch (ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////		}
//		
//		
//		return Integer.toString(num);
//	}
	
	
	
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
				user.myPage(dto,cookie.getValue());
//				user.myPage(dto,id);
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
	
	@PostMapping("/wantList")
	@ResponseBody
	public List<WantListDto> wantList(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody WantListDto dto,HttpServletResponse response, HttpServletRequest request){
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService want = ctx.getBean("store",StoreService.class);
		
		UserDto userDto=new UserDto();
		 HttpSession session = request.getSession();
		 session.setAttribute("id", userDto.getId());
//		 String id = (String) session.getAttribute("id");
		 String id = cookie.getValue();
		
		List<WantListDto> list = null;
		Long uNum;
		try {
//			uNum = like.ckeckCookie(cookie.getValue());
			uNum = want.ckeckCookie(id);
			dto.setUserNum(uNum);
			list = want.read(dto);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	
	@PostMapping("/feedList")
	@ResponseBody
	public List<CommunityDto> feedList(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody CommunityDto dto,HttpServletResponse response, HttpServletRequest request){
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService commu = ctx.getBean("community",CommunityService.class);
		
		UserDto userDto=new UserDto();
		 HttpSession session = request.getSession();
		 session.setAttribute("id", userDto.getId());
//		 String id = (String) session.getAttribute("id");
		 String id = cookie.getValue();
		
		List<CommunityDto> list = null;
		Long uNum;
		try {
//			uNum = like.ckeckCookie(cookie.getValue());
			uNum = commu.ckeckCookie(id);
			dto.setUserNum(uNum);
			list = commu.read(dto);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@PostMapping("/storeList")
	@ResponseBody
	public List<StoreDto> storeList(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody StoreDto dto,HttpServletResponse response, HttpServletRequest request){
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService commu = ctx.getBean("store",StoreService.class);
		
		UserDto userDto=new UserDto();
		 HttpSession session = request.getSession();
		 session.setAttribute("id", userDto.getId());
//		 String id = (String) session.getAttribute("id");
		 String id = cookie.getValue();
		
		List<StoreDto> list = null;
		Long uNum;
		try {
//			uNum = like.ckeckCookie(cookie.getValue());
			uNum = commu.ckeckCookie(id);
			dto.setUserNum(uNum);
			list = commu.read(dto);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
