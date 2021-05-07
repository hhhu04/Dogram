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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
//	public static ArrayList<CommunityDto> list = new ArrayList<CommunityDto>();
	
	
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
	                "insert into community (img,title,content,comment_count,like_count,category,user_num,created_at) value(?,?,?,?,?,?,?,?)");

	        preparedStatement.setString(1, dto.getTitle());
	        preparedStatement.setString(2, dto.getImg());
	        preparedStatement.setString(3, dto.getContent());
	        preparedStatement.setInt(4, 0);
	        preparedStatement.setInt(5, 0);
	        preparedStatement.setString(6, dto.getCategory());
	        preparedStatement.setLong(7, dto.getUserNum());
	        preparedStatement.setString(8, date.date());
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
	 
 
	 
	 public List<CommunityDto> read(CommunityDto cdto) throws SQLException {
		 List<CommunityDto> list = new ArrayList<CommunityDto>();
		 System.out.println(list);
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from community");
			
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		        	CommunityDto dto = new CommunityDto();
		        	Long num = rs.getLong("num");
		        	String img = rs.getString("img");
		        	String title = rs.getString("title");
		        	String body = rs.getString("content");
		        	int commentCount = rs.getInt("comment_count");
		        	int likeCount = rs.getInt("like_count");
		        	String tag = rs.getString("category");
		        	String createdAt = rs.getString("created_at");
		        	String updatedAt = rs.getString("updated_at");
		        	String address = rs.getString("address");
		        	Long userNum = rs.getLong("user_num");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		        	System.out.println(title);
		        	dto.setNum(num);
		        	dto.setImg(img);
		        	dto.setTitle(title);
		        	dto.setContent(body);
		        	dto.setCommentCount(commentCount);
		        	dto.setLikeCount(likeCount);
		        	dto.setCategory(tag);
		        	dto.setCreatedAt(dateTime);
		        	dto.setAddress(address);
		        	dto.setUserNum(userNum);
		        	dto.setUpdatedAt(dateTime);
		        	System.out.println(dto);
		        	list.add(dto);
		        	
		        	System.out.println(list);
		        }
		        stat.close();
		        db.disconnect();
		 return list;
	 }
	 
	 public List<CommunityDto> read2(CommunityDto cdto) throws SQLException {
		 List<CommunityDto> list = new ArrayList<CommunityDto>();
		 System.out.println(list);
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from community where user_num=?");
			
			preparedStatement.setLong(1, cdto.getUserNum());
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		        	CommunityDto dto = new CommunityDto();
		        	Long num = rs.getLong("num");
		        	String img = rs.getString("img");
		        	String title = rs.getString("title");
		        	String body = rs.getString("content");
		        	int commentCount = rs.getInt("comment_count");
		        	int likeCount = rs.getInt("like_count");
		        	String tag = rs.getString("category");
		        	String createdAt = rs.getString("created_at");
		        	String updatedAt = rs.getString("updated_at");
		        	String address = rs.getString("address");
		        	Long userNum = rs.getLong("user_num");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		        	System.out.println(title);
		        	dto.setNum(num);
		        	dto.setImg(img);
		        	dto.setTitle(title);
		        	dto.setContent(body);
		        	dto.setCommentCount(commentCount);
		        	dto.setLikeCount(likeCount);
		        	dto.setCategory(tag);
		        	dto.setCreatedAt(dateTime);
		        	dto.setAddress(address);
		        	dto.setUserNum(userNum);
		        	dto.setUpdatedAt(dateTime);
		        	System.out.println(dto);
		        	list.add(dto);
		        	
		        	System.out.println(list);
		        }
		        stat.close();
		        db.disconnect();
		 return list;
	 }
	 
	 
 public int checkUser(CommunityDto dto) throws SQLException {
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select user_num from community where community.user_num = ?");
			
			preparedStatement.setLong(1, dto.getUserNum());
			
			 ResultSet rs = preparedStatement.executeQuery();
		        if(rs.next()) {
		        	
		        	return 1;
		        	
		        }
		        stat.close();

		        db.disconnect();
			
		 
		 return -1;
		 
	 }
	
	 
	 public int update(CommunityDto dto) throws SQLException {
		 
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
//					c.prepareStatement("update community set (img,title,body,tag,updated_at) value (?,?,?,?,?) where community.num = ?");
				c.prepareStatement("update community set img=?,title=?,body=?,category=?,updated_at=? where community.num = ?");
			
			
			preparedStatement.setString(1, dto.getImg());
			preparedStatement.setString(2, dto.getTitle());
			preparedStatement.setString(3, dto.getContent());
			preparedStatement.setString(4, dto.getCategory());
			preparedStatement.setString(5, date.date());
			preparedStatement.setLong(6, dto.getNum());
			
			 int rs = preparedStatement.executeUpdate();
			 
			 
			 
			 
			  stat.close();

		       db.disconnect();
		 
		 
		 return rs;
	 }
	 
	 public int delete(CommunityDto dto) throws SQLException {
		 
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
				c.prepareStatement("delete from community where community.num = ?");
			
			
			preparedStatement.setLong(1, dto.getNum());
			
			
			 int rs = preparedStatement.executeUpdate();
			 
			 
			  stat.close();

		       db.disconnect();
		 
		 
		 return rs;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	

}
