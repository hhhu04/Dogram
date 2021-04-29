package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import configuration.DateNow;
import model.DBHandler;
import model.DataBase;
import model.dao.daol.UserDaoI;
import model.dto.UserDto;


@Component
public class UserDao implements UserDaoI{
	
	private final DataBase db;
	
	public UserDao(DBHandler db) {
		this.db = db;
		
	}
	
public int loginUser(UserDto dto) throws ClassNotFoundException, SQLException {
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select id from user where user.id = ? and user.password=?");
		
		preparedStatement.setString(1, dto.getId());
		preparedStatement.setString(2, dto.getPassword());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	
	            return 0;
	        }
	        stat.close();

	        db.disconnect();
		
		return -1;
		
	}
	
	public int checkUser(UserDto dto) throws ClassNotFoundException, SQLException {
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select id from user where user.id = ?");
		
		preparedStatement.setString(1, dto.getId());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	
	            return -1;
	        }
	        stat.close();

	        db.disconnect();
		
		return 1;
		
	}
	
	 public int create(UserDto dto) throws ClassNotFoundException, SQLException{
		 
		 	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into user (id,password,email,address,phone_number,pet_name,img,created_at"
	                + ",credit_rating,credit_grade) value(?,?,?,?,?,?,?,?,?,?)");
	        	System.out.println(dto.getId());
	        preparedStatement.setString(1, dto.getId()); //id
	        preparedStatement.setString(2, dto.getPassword()); //password
	        preparedStatement.setString(3, dto.getEmail()); //email
	        preparedStatement.setString(4, dto.getAddress()); //addree
	        preparedStatement.setString(5, dto.getPhoneNumber()); //phone
	        preparedStatement.setString(6, dto.getPetName()); //pet
	        preparedStatement.setString(7, dto.getImg()); // img
	        preparedStatement.setString(8, date.date()); //joinat
	        preparedStatement.setInt(9, dto.getCreditRating()); //creditrating
	        preparedStatement.setString(10, dto.getCreditGrade()); //creditgrade
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 1;
	    }
	

}
