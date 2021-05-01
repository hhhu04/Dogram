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
import model.dto.CommunityDto;
import model.dto.UserDto;


@Component
public class UserDao implements UserDaoI{
	
	private final DataBase db;
	
	public UserDao(DBHandler db) {
		this.db = db;
		
	}
	
public Long checkCookie(String cookie) throws ClassNotFoundException, SQLException {
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num from user where user.id = ?");
		
		preparedStatement.setString(1, cookie);
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	
	            return rs.getLong("num");
	        }
	        stat.close();

	        db.disconnect();
		
		return -1L;
		
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
				c.prepareStatement("select id from user where user.num = ?");
		
		preparedStatement.setLong(1, dto.getNum());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	
	            return -1;
	        }
	        stat.close();

	        db.disconnect();
		
		return 1;
		
	}
	
public int checkUser2(UserDto dto) throws ClassNotFoundException, SQLException {
		
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
	                "insert into user (id,password,email,address,phone_number,name,img,created_at"
	                + ",credit_rating,credit_grade) value(?,?,?,?,?,?,?,?,?,?)");
	        	System.out.println(dto.getId());
	        preparedStatement.setString(1, dto.getId()); //id
	        preparedStatement.setString(2, dto.getPassword()); //password
	        preparedStatement.setString(3, dto.getEmail()); //email
	        preparedStatement.setString(4, dto.getAddress()); //addree
	        preparedStatement.setString(5, dto.getPhoneNumber()); //phone
	        preparedStatement.setString(6, dto.getName()); //pet
	        preparedStatement.setString(7, dto.getImg()); // img
	        preparedStatement.setString(8, date.date()); //joinat
	        preparedStatement.setInt(9, dto.getCreditRating()); //creditrating
	        preparedStatement.setString(10, dto.getCreditGrade()); //creditgrade
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 1;
	    }
	 
	 
	
	 
	 
	 	public int update(UserDto dto) throws SQLException {
		 
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
				c.prepareStatement("update user set id=?,password=?,name=?,email=?,img=?,updated_at=?,phone_number=? where user.num = ?");
			
			
			preparedStatement.setString(1, dto.getId());
			preparedStatement.setString(2, dto.getPassword());
			preparedStatement.setString(3, dto.getName());
			preparedStatement.setString(4, dto.getEmail());
			preparedStatement.setString(5, dto.getImg());
			preparedStatement.setString(6, date.date());
			preparedStatement.setString(7, dto.getPhoneNumber());
			preparedStatement.setLong(8, dto.getNum());
			
			 int rs = preparedStatement.executeUpdate();
			 
			 
			  stat.close();

		       db.disconnect();
		 
		 
		 return rs;
	 }
	 
	 
	 	public int delete(UserDto dto) throws SQLException {
			 
			 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		    	DateNow date = ctx.getBean("datenow",DateNow.class);
			 
			 Connection c = db.connect();
				Statement stat = c.createStatement();
				
				PreparedStatement preparedStatement = 
					c.prepareStatement("update user set id=?,password=?,name=?,email=?,img=?,phone_number=?,updated_at=? where user.num = ?");
				
				
				preparedStatement.setString(1, "탈퇴한 회원입니다.");
				preparedStatement.setString(2, "탈퇴한 회원입니다.");
				preparedStatement.setString(3, "탈퇴한 회원입니다.");
				preparedStatement.setString(4, "탈퇴한 회원입니다.");
				preparedStatement.setString(5, "탈퇴한 회원입니다.");
				preparedStatement.setString(6, "탈퇴한 회원입니다.");
				preparedStatement.setString(7, date.date());
				preparedStatement.setLong(8, dto.getNum());
				
				 int rs = preparedStatement.executeUpdate();
				 
				 
				  stat.close();

			       db.disconnect();
			 
			 
			 return rs;
		 }
		 
	 
	 
	 
	 
	 
	 
	 
	

}
