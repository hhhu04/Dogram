package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

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
	
	public Long getNum() throws SQLException {
		Long num = 1L;
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num from user order by num desc limit 1");
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	            return rs.getLong("num");
	        }
	        stat.close();

	        db.disconnect();
		
		
		
		return num;
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
		 
		 	Long num = getNum();
		 	num++;
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into user (num,id,password,email,address,phone_number,pet_name,img,created_at"
	                + ",credit_rating,credit_grade) value(?,?,?,?,?,?,?,?,?,?,?)");
	        	System.out.println(dto.getId());
	        preparedStatement.setLong(1, num); //num
	        preparedStatement.setString(2, dto.getId()); //id
	        preparedStatement.setString(3, dto.getPassword()); //password
	        preparedStatement.setString(4, dto.getEmail()); //email
	        preparedStatement.setString(5, dto.getAddress()); //addree
	        preparedStatement.setString(6, dto.getPhoneNumber()); //phone
	        preparedStatement.setString(7, dto.getPetName()); //pet
	        preparedStatement.setString(8, dto.getImg()); // img
	        preparedStatement.setDate(9, Date.valueOf(dto.getCreatedAt().toLocalDate())); //joinat
	        preparedStatement.setInt(10, dto.getCreditRating()); //creditrating
	        preparedStatement.setString(11, dto.getCreditGrade()); //creditgrade
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 1;
	    }
	

}
