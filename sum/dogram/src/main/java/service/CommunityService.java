package service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import configuration.DateNow;
import configuration.DateNow2;
import configuration.MailConfig;
import model.dao.CommunityDao;
import model.dto.CommunityDto;
import model.dto.UserDto;
import service.servicei.CommunityServiceI;

@Component
@Repository
public class CommunityService  implements CommunityServiceI{
	
	@Autowired
    CommunityDao dao;
	

    public CommunityService(CommunityDao communityDao) {
        this.dao = communityDao;
    }
    
    public Long ckeckCookie(String cookie) throws ClassNotFoundException, SQLException {
    	
    	return dao.checkCookie(cookie);
    }

	
    public int create(CommunityDto dto,Long num) throws SQLException, ClassNotFoundException {
    	
        dto.setUserNum(num);
       dao.create(dto);
            
            System.out.println("create Community Success");
			return 1;
    }
    
    
    

	public List<CommunityDto> readMe(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		List<CommunityDto> list = dao.readMe(dto);
		
		
		return list;
	}
	
	public List<CommunityDto> read(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<CommunityDto> list = dao.read(dto);
		System.out.println("read Community Success");
		return list;
	}
	
//	public List<CommunityDto> read2(UserDto userDto,CommunityDto dto) throws SQLException {
//		// TODO Auto-generated method stub
//		
//		dao.checkAddress(userDto);
//		List<CommunityDto> list = dao.read2(dto);
//		
//		
//		return list;
//	}
	
	

	public int update(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		int num = dao.checkUser(dto);
		if(num == 1) {
			dao.update(dto);
			 System.out.println("update Community Success");
				return 1;
		}
		
		return -1;
	}
	
	public int delete(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		int num = dao.checkUser(dto);
		if(num == 1) {
			dao.delete(dto);
			 System.out.println("delete Community Success");
				return 1;
		}
		
		return -1;
	}

	

	public String upload(MultipartFile file, ModelAndView mv, Model model) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		String img;
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
    	DateNow2 date = ctx.getBean("datenow2",DateNow2.class);
		String name = date.date()+".jpg";
		
		file.getOriginalFilename();
		if(!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File("/home/cat/eclipse-web/dogram/src/main/webapp/resources/img", name));
//			file.transferTo(new File("/usr/local/apache-tomcat-9.0.45/webapps/dogram/resources/img", name));
			model.addAttribute("msg", "File uploaded successfully.");
			img = "img/"+name;
			System.out.println("99");
		}else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
			img = "-1";
			System.out.println("4");
		}
		System.out.println("4");
		return img;
	}

	public CommunityDto readOne(CommunityDto dto, int num) throws SQLException {
		// TODO Auto-generated method stub
		
		dao.readOne(dto,num);
		
		return dto;
	}
    
    
    
    
    
    
    
    
    
    
    

}
