package com.dogram.start;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.GenericXmlApplicationContext;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import model.dto.CommunityDto;
import model.dto.StoreDto;
import model.dto.UserDto;
import model.dto.WantListDto;
import service.CommunityService;
import service.StoreService;

@CrossOrigin
@Controller
@RequestMapping(value="/store", produces="application/json; charset=utf-8")
public class StoreController {
	
	@GetMapping("/newproduct")
	public String newPeoduct() {
		return "store";
	}

	
//	private static final String FILE_SERVER_PATH = "/home/cat/img";
//	private static String img = null;
	
//	@RequestMapping("/upload")
//	public String upload(@RequestParam("uploadFile") MultipartFile file, ModelAndView mv, Model model) throws IllegalStateException, IOException {
//		if(!file.getOriginalFilename().isEmpty()) {
//			file.transferTo(new File("/home/cat/img", file.getOriginalFilename()));
//			model.addAttribute("msg", "File uploaded successfully.");
//			img = FILE_SERVER_PATH+"/"+file.getOriginalFilename();
//		}else {
//			model.addAttribute("msg", "Please select a valid mediaFile..");
//		}
//		
//		return "join";
//	}
//	
	
	@PostMapping("/")
	@ResponseBody
	public List<StoreDto> read(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody StoreDto dto,UserDto userDto) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService comm = ctx.getBean("store",StoreService.class);
		
		List<StoreDto> list =null ;
		if(cookie.getName() != null) {
			try {
				Long userNum = comm.ckeckCookie(cookie.getValue());
				userDto.setNum(userNum);
				dto.setUserNum(userNum);
				list = comm.read(userDto,dto);
				
				return list;
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		
		}
		
		
		
		
		return list;
	}
	
	
	@PostMapping("/newproduct")
	@ResponseBody
	public int newPeoduct(@CookieValue(value="id", required=false) Cookie cookie,
			@RequestParam("img") MultipartFile img, HttpServletRequest file,Model model,ModelAndView mv) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService comm = ctx.getBean("store",StoreService.class);
		
		StoreDto dto = new StoreDto();
		
		dto.setTitle(file.getParameter("title"));
		dto.setPrice(file.getParameter("price"));
		dto.setCategory(file.getParameter("category"));
		dto.setContent(file.getParameter("content"));
		
//		if(cookie.getName().equals("id")){
			try {
//				Long userNum = comm.ckeckCookie(cookie.getValue());
				Long userNum = comm.ckeckCookie("hhh");
				dto.setImg(comm.upload(img, mv, model));
				int num = comm.create(dto, userNum);
				
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
//		}
				catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return -1;
		
		
	}
	
	
	@PostMapping("/addwant")
	@ResponseBody
	public int  addWant(HttpServletRequest request,@RequestBody WantListDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService comm = ctx.getBean("store",StoreService.class);
		HttpSession session = request.getSession();
//		String id = (String) session.getAttribute("id");
		String id = cookie.getValue();
		int num;
		if(id != null) {
		try {
			Long userNum = comm.ckeckCookie(id);
			dto.setUserNum(userNum);
			
			Long c = comm.checkWant(dto);
			if(c == 0L) num = comm.addWant(dto);
			else num = comm.deleteWant(dto);
		
			return num;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}
		}
		
		
		return -11;
	}
	
	
	@PostMapping("/update")
	@ResponseBody
	public int update(HttpServletRequest request,@RequestBody StoreDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService comm = ctx.getBean("store",StoreService.class);
		HttpSession session = request.getSession();
//		String id = (String) session.getAttribute("id");
		String id = cookie.getValue();
		
	
		// 등록자, 게시물의 정보가 다를경의 리턴값 구분 필요
		
		int num;
		if(id != null) {
			try {
				Long userNum = comm.ckeckCookie(id);
				dto.setUserNum(userNum);
				
				num = comm.update(dto);
			
				return num;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
		}
		
		return -1;
	}
	
	
	
	@PostMapping("/delete")
	@ResponseBody
	public int delete(HttpServletRequest request,@RequestBody StoreDto dto,@CookieValue(value="id", required=false) Cookie cookie) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		StoreService comm = ctx.getBean("store",StoreService.class);
		HttpSession session = request.getSession();
//		String id = (String) session.getAttribute("id");
		String id = cookie.getValue();
		
		// 등록자, 게시물의 정보가 다를경의 리턴값 구분 필요
		
		int num;
		if(id != null) {
			try {
				Long userNum = comm.ckeckCookie(id);
				dto.setUserNum(userNum);
				
				num = comm.delete(dto);
			
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
