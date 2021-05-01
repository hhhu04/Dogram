package com.dogram.start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.CommunityDao;
import model.dto.CommentDto;
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import model.dto.UserDto;
import service.CommentService;
import service.CommunityService;
import service.LikeListService;
import service.UserService;

@Controller
@RequestMapping(value="/feed", produces="application/json; charset=utf-8")
public class CommunityController {

	@GetMapping("/newfeed")
	public String community() {
		return "newfeed";
	}
	
	
	@PostMapping("/newfeed")
	@ResponseBody
	public int newfeed(@RequestBody CommunityDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService comm = ctx.getBean("community",CommunityService.class);
		if(cookie.getName() != null) {
		if(cookie.getName().equals("id")){
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
		}
		return -1;
	}
	
	
	@PostMapping("/")
	@ResponseBody
	public List<CommunityDto> read(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody UserDto userDto,CommunityDto dto) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService comm = ctx.getBean("community",CommunityService.class);
		
		if(cookie.getName() != null) {
				try {
					Long userNum = comm.ckeckCookie(cookie.getValue());
					userDto.setNum(userNum);
					dto.setUserNum(userNum);
					List<CommunityDto> list = comm.read(userDto,dto);
					
					return list;
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			
		}
		return null;
		
	}
	
	
	@PostMapping("/addlike")
	@ResponseBody
	public int addlike(@RequestBody LikeListDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		LikeListService like = ctx.getBean("likeList",LikeListService.class);
		int num = -1;
		if(cookie.getName() != null) {
			try {
				Long uNum = like.ckeckCookie(cookie.getValue());
				dto.setUserNum(uNum);
				Long c = like.checkLike(dto);
					if(c == 0L) num = like.create(dto);
					else num = like.delete(dto);
					
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
		}
		
		return -3;
	}
	
	
	@PostMapping("/update")
	@ResponseBody
	public int update(@RequestBody CommunityDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService comm = ctx.getBean("community",CommunityService.class);
		if(cookie.getName() != null) {
		if(cookie.getName().equals("id")){
			try {
				Long userNum = comm.ckeckCookie(cookie.getValue());
				dto.setUserNum(userNum);
				int num = comm.update(dto);
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
		}
		}
		return -1;
	}
	
	
	@PostMapping("/delete")
	@ResponseBody
	public int delete(@RequestBody CommunityDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService comm = ctx.getBean("community",CommunityService.class);
		if(cookie.getName() != null) {
			try {
				Long userNum = comm.ckeckCookie(cookie.getValue());
				dto.setUserNum(userNum);
				int num = comm.delete(dto);
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
		}
		
		return -1;
	}
	
	
	@PostMapping("/addcomment")
	@ResponseBody
	public int addComment(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody CommentDto dto) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommentService comme = ctx.getBean("comment",CommentService.class);
		int num = -1;
		if(cookie.getName() != null) {
			try {
				comme.ckeckCookie(cookie.getValue(),dto);
				num = comme.create(dto);
					
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
