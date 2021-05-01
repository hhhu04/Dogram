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
import model.dao.daol.CommentDaoI;
import model.dto.CommentDto;
import model.dto.UserDto;

@Component
public class CommentDao implements CommentDaoI{
	
	private final DataBase db;
	
	public CommentDao(DBHandler db) {
		this.db = db;
		
	}
	
public int checkCookie(String cookie,CommentDto dto) throws SQLException {
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num,id from user where user.id = ?");
		
		preparedStatement.setString(1, cookie);
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	dto.setUserId(rs.getString("id"));
	        	dto.setUserNum(rs.getLong("num"));
	            return 1;
	        }
	        stat.close();

	        db.disconnect();
		
		return -1;
	}
	
	 public int create(CommentDto dto) throws ClassNotFoundException, SQLException{
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into comment (comment,created_at,community_num,user_num,user_id) value(?,?,?,?,?)");

	        preparedStatement.setString(1, dto.getComment());
	        preparedStatement.setString(2, date.date());
	        preparedStatement.setLong(3, dto.getCommunityNum());
	        preparedStatement.setLong(4, dto.getUserNum());
	        preparedStatement.setString(5, dto.getUserId());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 1;
	    }
	

}
