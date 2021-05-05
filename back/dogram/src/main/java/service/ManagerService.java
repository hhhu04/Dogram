package service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.dao.ManagerDao;
import model.dto.CommunityDto;
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
		
	 
	 public int create(ManagerDto dto,HttpServletRequest request) throws ClassNotFoundException, SQLException {
		 
        dao.create(dto);
		 
       
        
        System.out.println("create manager Success");
		 return 1;
	 }
	 
	 public int login(ManagerDto dto,HttpServletResponse response, HttpServletRequest request) throws SQLException, ClassNotFoundException{
	    	
	    	int num = dao.loginManager(dto);
	    	
	    	
	    	if(num == 0) {
				Cookie cookie = new Cookie("grade",dto.getGrade());
				response.addCookie(cookie);
				 HttpSession session = request.getSession();
				 session.setAttribute("grade", dto.getGrade());
				System.out.println("Check manager Success");
				return num;
			}
	    	
	    	
	    	return num;
	    }


	public List<UserDto> readUser(UserDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		List<UserDto> list = dao.readUser(dto);
		
		return list;
	}


	public List<CommunityDto> readFeed(CommunityDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<CommunityDto> list = dao.readFeed(dto);
		
		return list;
	}


	public List<StoreDto> readStore(StoreDto dto) {
		// TODO Auto-generated method stub
		List<StoreDto> list = dao.readStore(dto);
		
		return list;
	}
	 

}
