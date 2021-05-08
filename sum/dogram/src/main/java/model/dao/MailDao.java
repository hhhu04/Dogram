package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.DBHandler;
import model.DataBase;
import model.dao.daol.MailDaoI;
import model.dto.MailDto;
import model.dto.UserDto;

@Component
public class MailDao implements MailDaoI{
	
private final DataBase db;
	
	public MailDao(DBHandler db) {
		this.db = db;
		
	}
	
	@Autowired

public int checkUser(MailDto dto) throws ClassNotFoundException, SQLException {
		System.out.println("2");
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
		c.prepareStatement("select id,password from user where user.email = ?");
		
		preparedStatement.setString(1, dto.getEmail());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	String id = rs.getString("id");
	        	String password = rs.getString("password");
	        	
	        	dto.setId(id);
	        	dto.setPassword(password);
	        	dto.setFrom("ShinTest94@gmail.com");
	        	dto.setSubject("요청하신 계정의 정보");
	        	dto.setContent("id : "+id+" pw : "+password);
	            return 1;
	        }
	        stat.close();

	        db.disconnect();
		
		return 0;
		
	}
	
	
	
}
