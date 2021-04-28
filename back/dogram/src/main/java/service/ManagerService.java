package service;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.dao.ManagerDao;
import model.dto.ManagerDto;
import model.dto.StoreDto;
import model.dto.UserDto;
import service.servicei.ManagerI;

@Component
@Repository
public class ManagerService implements ManagerI{
	
	@Autowired
	ManagerDao dao;
	
	public ManagerService(ManagerDao dao) {
		this.dao = dao;
	}
	
	 
	 public Long ckeckCookie(String cookie) throws ClassNotFoundException, SQLException {
	    	
	    	return dao.checkCookie(cookie);
	    }
		
	 
	 public int create(ManagerDto dto) throws ClassNotFoundException, SQLException {
		 
		 System.out.println(toString());
        dao.create(dto);
		 
        System.out.println("create Community Success");
		 return 1;
	 }
	 
	 public int login(ManagerDto dto,HttpServletResponse response, HttpServletRequest request) throws SQLException, ClassNotFoundException{
	    	
	    	int num = dao.loginManager(dto);
	    	
	    	
	    	if(num == 0) {
				Cookie cookie = new Cookie("grade",dto.getGrade());
				response.addCookie(cookie);
				System.out.println("Check User Success");
				return num;
			}
	    	
	    	
	    	return num;
	    }
	 

}
