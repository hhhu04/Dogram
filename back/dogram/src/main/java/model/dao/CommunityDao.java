package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import configuration.DateNow;
import model.DBHandler;
import model.DataBase;
import model.dao.daol.CommunityDaoI;
import model.dto.CommunityDto;
import model.dto.UserDto;

@Component
public class CommunityDao implements CommunityDaoI{
	
	private final DataBase db;
	
	public CommunityDao(DBHandler db) {
		this.db = db;
		
	}
	
	public Long getNum() throws SQLException {
		Long num = 1L;
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num from community order by num desc limit 1");
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	            return rs.getLong("num");
	        }
	        stat.close();

	        db.disconnect();
		
		return num;
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
	
	 public int create(CommunityDto dto) throws ClassNotFoundException, SQLException{
		 Long num = getNum();
		 	num++;
		 
		 	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into community (num,img,title,body,comment_count,like_count,tag,user_num,created_at) value(?,?,?,?,?,?,?,?,?)");

	        preparedStatement.setLong(1, num);
	        preparedStatement.setString(2, dto.getTitle());
	        preparedStatement.setString(3, dto.getImg());
	        preparedStatement.setString(4, dto.getBody());
	        preparedStatement.setInt(5, dto.getCommentCount());
	        preparedStatement.setInt(6, dto.getLikeCount());
	        preparedStatement.setString(7, dto.getTag());
	        preparedStatement.setLong(8, dto.getUserNum());
	        preparedStatement.setString(9, date.date());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 0;
	    }
	

}
