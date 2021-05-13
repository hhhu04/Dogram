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
import model.dao.daol.CommentDaoI;
import model.dto.CommentDto;
import model.dto.CommunityDto;
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
	        System.out.println(dto);
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

	public int delete(CommentDto dto) throws SQLException {
		
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
				c.prepareStatement("delete from comment where comment.num = ? and comment.user_num=?");
			
			
			preparedStatement.setLong(1, dto.getNum());
			preparedStatement.setLong(2, dto.getUserNum());
			
			
			 int rs = preparedStatement.executeUpdate();
			 
			 
			  stat.close();

		       db.disconnect();
		 
		 
		 return rs;
	}

	public List<CommentDto> read(CommentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<CommentDto> list = new ArrayList<CommentDto>();
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select * from comment where community_num=?");
		
		preparedStatement.setLong(1, dto.getCommunityNum());
		System.out.println(dto.getCommunityNum());
		 ResultSet rs = preparedStatement.executeQuery();
		
	        while(rs.next()) {
	        	CommentDto dto2 = new CommentDto();
	        	
	        	Long num = rs.getLong("num");
	        	String comment = rs.getString("comment");
	        	String createdAt = rs.getString("created_at");
	        	String updatedAt = rs.getString("updated_at");
	        	Long communityNum = rs.getLong("community_num");
	        	Long userNum = rs.getLong("user_num");
	        	String userId =rs.getString("user_id");
	        	
	        	
	        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
	        	LocalDateTime dateTime2 = null; 
	        	if(updatedAt != null) dateTime2 = LocalDateTime.parse(updatedAt, formatter);
	        	
	        	dto2.setNum(num);
	        	dto2.setComment(comment);
	        	dto2.setCreatedAt(dateTime);
	        	dto2.setUpdatedAt(dateTime2);
	        	dto2.setCommunityNum(communityNum);
	        	dto2.setUserNum(userNum);
	        	dto2.setUserId(userId);
	        	
	        	list.add(dto2);
	        }
	        stat.close();
	        db.disconnect();
	 return list;
	}
	
	public List<CommentDto> read2(CommentDto dto,Long cnum) throws SQLException {
		// TODO Auto-generated method stub
		List<CommentDto> list = new ArrayList<CommentDto>();
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select * from comment where community_num=?");
		
		preparedStatement.setLong(1, cnum);
		System.out.println(dto.getCommunityNum());
		 ResultSet rs = preparedStatement.executeQuery();
		
	        while(rs.next()) {
	        	CommentDto dto2 = new CommentDto();
	        	
	        	Long num = rs.getLong("num");
	        	String comment = rs.getString("comment");
	        	String createdAt = rs.getString("created_at");
	        	String updatedAt = rs.getString("updated_at");
	        	Long communityNum = rs.getLong("community_num");
	        	Long userNum = rs.getLong("user_num");
	        	String userId =rs.getString("user_id");
	        	
	        	
	        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
	        	LocalDateTime dateTime2 = null; 
	        	if(updatedAt != null) dateTime2 = LocalDateTime.parse(updatedAt, formatter);
	        	
	        	dto2.setNum(num);
	        	dto2.setComment(comment);
	        	dto2.setCreatedAt(dateTime);
	        	dto2.setUpdatedAt(dateTime2);
	        	dto2.setCommunityNum(communityNum);
	        	dto2.setUserNum(userNum);
	        	dto2.setUserId(userId);
	        	
	        	list.add(dto2);
	        }
	        stat.close();
	        db.disconnect();
	 return list;
	}

	public Long checkself(String cookie, CommentDto dto) throws SQLException {
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num from user where user.id = ?");
		
		preparedStatement.setString(1, cookie);
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	Long num = rs.getLong("num"); 
	        	System.out.println(num);
	            return num;
	        }
	        stat.close();

	        db.disconnect();
		
		return -1L;
	}

	public int update(CommentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
				c.prepareStatement("update comment set comment=?,updated_at=? where user_num=? and community_num=? and num=?");
			
			
			preparedStatement.setString(1, dto.getComment());
			preparedStatement.setString(2, date.date());
			preparedStatement.setLong(3, dto.getUserNum());
			preparedStatement.setLong(4, dto.getCommunityNum());
			preparedStatement.setLong(5, dto.getNum());
			
			 int rs = preparedStatement.executeUpdate();
			 
			  stat.close();

		       db.disconnect();
		 
		 
		 return rs;
	}
	
	
	
	
	
	
	
	
	
	

}
