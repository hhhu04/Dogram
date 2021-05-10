package service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import model.dao.UserDao;
import model.dto.UserDto;

@Component
@Repository
public class UserService {

	
	 /*
    static ParkingDAO dao = new DaoFactory().parkingDAO();
    static ParkingDTO dto = new ParkingDTO();
    */
	
	
	
	@Autowired
    UserDao dao;

    public UserService(UserDao userDao) {
        this.dao = userDao;
    }


    public int create(UserDto dto) throws SQLException, ClassNotFoundException {

        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreditRating(0);
        dto.setCreditGrade("댕댕이");

            dao.create(dto);
                System.out.println("create User Success");
            return 1;
    }
    
    public int read(UserDto dto) throws SQLException, ClassNotFoundException{
    	
    	int num = dao.checkUser2(dto);
    	System.out.println("Check User Success");
    	return num;
    }
    
    
    
    public int login(UserDto dto) throws SQLException, ClassNotFoundException{
    	
    	int num = dao.loginUser(dto);
    	System.out.println(num);
    	
    	if(num == 1) {
    		if(dto.getPassword().equals("탈퇴한 회원입니다.")) return -1;
    		else {
			
//			 HttpSession session = request.getSession();
//			 session.setAttribute("id", dto.getId());
//			 String id = (String) session.getAttribute("id");
    			System.out.println("Check User Success");
			return num;
    		}
		}
    	
    	
    	return num;
    }
    
    public int update(UserDto dto,String cookie) throws ClassNotFoundException, SQLException {
    	Long useerNum =  dao.checkCookie(cookie);
    	dto.setNum(useerNum);
    	int num = dao.checkUser(dto);
    	if(num == -1) {
    		num = dao.update(dto);
    		System.out.println("update User Success");
        	return num;
    	}
    	
    	
    	return -1;
    }
    
    public int delete(UserDto dto,String cookie) throws ClassNotFoundException, SQLException {
    	Long useerNum =  dao.checkCookie(cookie);
    	System.out.println(useerNum);
    	dto.setNum(useerNum);
    	int num = dao.checkUser(dto);
    	System.out.println("1");
    	if(num == -1) {
    		num = dao.delete(dto);
    		System.out.println("2");
    		System.out.println("delete User Success");
        	return num;
    	}
    	
    	
    	return -1;
    }


	public int myPage(UserDto dto, String cookie) throws SQLException, ClassNotFoundException {
		Long useerNum =  dao.checkCookie(cookie);
    	System.out.println(useerNum);
    	dto.setNum(useerNum);
		dao.myPage(dto);
		// TODO Auto-generated method stub
		return 1;
	}

	
	public String upload(MultipartFile file, ModelAndView mv, Model model) throws IllegalStateException, IOException {
		String img;
		
		if(!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File("/home/cat/eclipse-web/dogram/src/main/webapp/resources/img", file.getOriginalFilename()));
			model.addAttribute("msg", "File uploaded successfully.");
			img = "img/"+file.getOriginalFilename();
			System.out.println("99");
		}else {
			model.addAttribute("msg", "Please select a valid mediaFile..");
			img = "-1";
		}
		
		
		return img;
	}
    
	
}
