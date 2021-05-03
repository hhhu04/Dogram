package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import configuration.DateNow;
import model.DBHandler;
import model.DataBase;
import model.dao.daol.ManagerDaoI;
import model.dto.ManagerDto;
import model.dto.UserDto;

@Component
public class ManagerDao implements ManagerDaoI{
	
	private final DataBase db;
	
	public ManagerDao(DBHandler db) {
		this.db = db;
		
	}
	
public int loginManager(ManagerDto dto) throws ClassNotFoundException, SQLException {
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select grade from manager where manager.id = ? and manager.password=?");
		
		preparedStatement.setString(1, dto.getId());
		preparedStatement.setString(2, dto.getPassword());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	dto.setGrade(rs.getString("grade"));
	            return 0;
	        }
	        stat.close();
	        
	        db.disconnect();
		
		return -1;
		
	}
	
	 public int create(ManagerDto dto) throws ClassNotFoundException, SQLException{
	        Connection c = db.connect();

	        
	        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into manager (id,password,name,phone_number,created_at,grade) value(?,?,?,?,?,?)");

	        preparedStatement.setString(1, dto.getId());
	        preparedStatement.setString(2, dto.getPassword());
	        preparedStatement.setString(3, dto.getName());
	        preparedStatement.setString(4, dto.getPhoneNumber());
	        preparedStatement.setString(5, date.date());
	        preparedStatement.setString(6, "사원");
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 0;
	    }

	public Long checkCookie(String cookie) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserDto> readUser(UserDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<UserDto> list = new ArrayList<UserDto>();
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select * from user");
		
		
		 ResultSet rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        UserDto dto2 = new UserDto();
	        	
	        Long num = rs.getLong("num");
	        String id = rs.getString("id");
	        String password = rs.getString("password");
	        String email = rs.getString("email");
	        String address = rs.getString("address");
	        String phoneNumber = rs.getString("phone_number");
	        String name = rs.getString("name");
	        String img = rs.getString("img");
	        String createdAt = rs.getString("created_at");
	        String updatedAt = rs.getString("updated_at");
	        int creditRating = rs.getInt("credit_ration");
	        String creditGrade = rs.getString("credit_grade");
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
        	if(updatedAt != null) {
        	LocalDateTime dateTime2 = LocalDateTime.parse(createdAt, formatter);
        	dto2.setUpdatedAt(dateTime2);
        	}else dto2.setUpdatedAt(null);
	    	
	    	dto2.setNum(num);
	    	dto2.setId(id);
	    	dto2.setPassword(password);
	    	dto2.setEmail(email);
	    	dto2.setAddress(address);
	    	dto2.setPhoneNumber(phoneNumber);
	    	dto2.setName(name);
	    	dto2.setImg(img);
	    	dto2.setCreatedAt(dateTime);
	        dto2.setCreditRating(creditRating);
	        dto2.setCreditGrade(creditGrade);
	        
	        list.add(dto2);
	        }
	        stat.close();
	        
	        db.disconnect();
		
		
		
		
		return list;
	}
	

}
