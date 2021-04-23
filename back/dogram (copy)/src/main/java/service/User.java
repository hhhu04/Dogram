package service;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDao;
import model.dto.UserDto;

public class User {

	
	 /*
    static ParkingDAO dao = new DaoFactory().parkingDAO();
    static ParkingDTO dto = new ParkingDTO();
    */

    UserDao dao;

    public User(UserDao userDao) {
        this.dao = userDao;
    }


    public void create( UserDto dto) throws SQLException, ClassNotFoundException {

        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreditRating(0);
        dto.setCreditGrade("댕댕이");

            dao.create(dto);
                System.out.println("create User Success");
            
    }
    
    public int read(UserDto dto) throws SQLException, ClassNotFoundException{
    	
    	int num = dao.checkUser(dto);
    	System.out.println("Check User Success");
    	return num;
    }
    
public int login(UserDto dto,HttpServletResponse response, HttpServletRequest request) throws SQLException, ClassNotFoundException{
    	
    	int num = dao.loginUser(dto);
    	
    	
    	if(num == 0) {
			Cookie cookie = new Cookie("id",dto.getId());
			response.addCookie(cookie);
			return num;
		}
    	
    	System.out.println("Check User Success");
    	return num;
    }

    
	
}
