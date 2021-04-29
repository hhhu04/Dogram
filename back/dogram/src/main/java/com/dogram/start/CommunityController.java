package com.dogram.start;

import java.sql.SQLException;
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
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import model.dto.UserDto;
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
	public String read(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody UserDto userDto) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		CommunityService comm = ctx.getBean("community",CommunityService.class);
		
		if(cookie.getName() != null) {
			if(cookie.getName().equals("id")){
				try {
					Long userNum = comm.ckeckCookie(cookie.getValue());
					userDto.setNum(userNum);
					int num = comm.read(userDto);
					
					
					///주소 설정
					
					
					
					ObjectMapper mapper = new ObjectMapper();

					String json = mapper.writeValueAsString(CommunityDao.map);
					json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(CommunityDao.map); 

					return json;
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		
		return "";
	}
	
	
	
	@PostMapping("/addlike")
	@ResponseBody
	public int like(@RequestBody LikeListDto dto, HttpServletResponse response, HttpServletRequest request) {
//		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		LikeListService like = ctx.getBean("mlikeList",LikeListService.class);
		
		int check = -1;
		
		try {
			
//			check = like.create(dto);
			return check;
		
		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return 0;
	}
	
	
}
