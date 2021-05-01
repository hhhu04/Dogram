package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	

}
