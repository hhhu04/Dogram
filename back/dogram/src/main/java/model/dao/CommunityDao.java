package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.format.DataFormatMatcher;

import configuration.DateNow;
import model.DBHandler;
import model.DataBase;
import model.dao.daol.CommunityDaoI;
import model.dto.CommunityDto;
import model.dto.UserDto;
import service.CommunityService;

@Component
public class CommunityDao implements CommunityDaoI{
	
	private final DataBase db;
	
	public CommunityDao(DBHandler db) {
		this.db = db;
		
	}
	
	public static Map<Integer,CommunityDto> map = new HashMap<Integer,CommunityDto>();
	
	
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
		
		 	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into community (img,title,body,comment_count,like_count,tag,user_num,created_at) value(?,?,?,?,?,?,?,?)");

	        preparedStatement.setString(1, dto.getTitle());
	        preparedStatement.setString(2, dto.getImg());
	        preparedStatement.setString(3, dto.getBody());
	        preparedStatement.setInt(4, dto.getCommentCount());
	        preparedStatement.setInt(5, dto.getLikeCount());
	        preparedStatement.setString(6, dto.getTag());
	        preparedStatement.setLong(7, dto.getUserNum());
	        preparedStatement.setString(8, date.date());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 0;
	    }
	 
	 
	 
	 public int create(CommunityDto dto, String address) throws ClassNotFoundException, SQLException{
			
		 	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into community (img,title,body,comment_count,like_count,tag,user_num,created_at,address) value(?,?,?,?,?,?,?,?,?)");

	        preparedStatement.setString(1, dto.getTitle());
	        preparedStatement.setString(2, dto.getImg());
	        preparedStatement.setString(3, dto.getBody());
	        preparedStatement.setInt(4, dto.getCommentCount());
	        preparedStatement.setInt(5, dto.getLikeCount());
	        preparedStatement.setString(6, dto.getTag());
	        preparedStatement.setLong(7, dto.getUserNum());
	        preparedStatement.setString(8, date.date());
	        preparedStatement.setString(9, address);
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 0;
	    }
	 
	 public int checkAddress(UserDto userDto) throws SQLException {
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select address from user where user.num = ?");
			
			preparedStatement.setLong(1, userDto.getNum());
			
			 ResultSet rs = preparedStatement.executeQuery();
		        if(rs.next()) {
		        	
		        	userDto.setAddress("address");
		        	
		        }
		        stat.close();

		        db.disconnect();
			
		 
		 return 0;
		 
	 }
	 
 public String checkAddress(Long num) throws SQLException {
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select address from user where user.num = ?");
			
			preparedStatement.setLong(1, num);
			CommunityDto dto = new CommunityDto();
			 ResultSet rs = preparedStatement.executeQuery();
		        if(rs.next()) {
		        	
		        	dto.setAddress(rs.getString("address"));
		        	
		        }
		        else dto.setAddress(null);
		        stat.close();

		        db.disconnect();
			
		 
		 return dto.getAddress();
		 
	 }
	 
	 public int read(CommunityDto dto) throws SQLException {
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from community");
			
			
			 ResultSet rs = preparedStatement.executeQuery();
			
			 	int count = 0;
		        while(rs.next()) {
		        	
		        	Long num = rs.getLong("num");
		        	String img = rs.getString("img");
		        	String title = rs.getString("title");
		        	String body = rs.getString("body");
		        	int commentCount = rs.getInt("comment_count");
		        	int likeCount = rs.getInt("like_count");
		        	String tag = rs.getString("tag");
		        	String createdAt = rs.getString("created_at");
		        	String updatedAt = rs.getString("updated_at");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		        	
		        	dto.setNum(num);
		        	dto.setImg(img);
		        	dto.setTitle(title);
		        	dto.setBody(body);
		        	dto.setCommentCount(commentCount);
		        	dto.setLikeCount(likeCount);
		        	dto.setTag(tag);
		        	dto.setCreatedAt(dateTime);
		        	
		        	map.put(count, dto);
		        	count++;
		        }
		        if(map.size() > 0) return 1;
		        stat.close();
		        db.disconnect();
		 return 0;
	 }
	 
	 public int read(CommunityDto dto, UserDto userDto) throws SQLException {
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from community where address = ?");
			
			preparedStatement.setString(1, userDto.getAddress());
			
			 ResultSet rs = preparedStatement.executeQuery();
			 
			 int count = 0;
		        while(rs.next()) {
		        	
		        	Long num = rs.getLong("num");
		        	String img = rs.getString("img");
		        	String title = rs.getString("title");
		        	String body = rs.getString("body");
		        	int commentCount = rs.getInt("comment_count");
		        	int likeCount = rs.getInt("like_count");
		        	String tag = rs.getString("tag");
		        	String createdAt = rs.getString("created_at");
		        	String updatedAt = rs.getString("updated_at");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		        	
		        	dto.setNum(num);
		        	dto.setImg(img);
		        	dto.setTitle(title);
		        	dto.setBody(body);
		        	dto.setCommentCount(commentCount);
		        	dto.setLikeCount(likeCount);
		        	dto.setTag(tag);
		        	dto.setCreatedAt(dateTime);
		        	
		        	map.put(count, dto);
		        	count++;
		        }
		        if(map.size() > 0) return 1;
		        stat.close();
		        db.disconnect();
		 return 0;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	

}
