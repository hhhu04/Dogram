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
import model.dto.CommentDto;
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import model.dto.UserDto;
import service.CommentService;
import service.CommunityService;
import service.LikeListService;

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
		
public String img(Long cookie) throws ClassNotFoundException, SQLException {
			
			Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select img from user where user.num = ?");
			
			preparedStatement.setLong(1, cookie);
			
			 ResultSet rs = preparedStatement.executeQuery();
		        if(rs.next()) {
		        	return rs.getString("img");
		        }
		        stat.close();

		        db.disconnect();
			
			return "-1";
		}
		
		
	
	 public int create(CommunityDto dto) throws ClassNotFoundException, SQLException{
		
		 String userImg = img(dto.getUserNum());
		 	System.out.println(userImg+"1223123");
		 
		 	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into community (title,img,content,comment_count,like_count,category,user_num,created_at,user_img) value(?,?,?,?,?,?,?,?,?)");

	        preparedStatement.setString(1, dto.getTitle());
	        preparedStatement.setString(2, dto.getImg());
	        preparedStatement.setString(3, dto.getContent());
	        preparedStatement.setInt(4, 0);
	        preparedStatement.setInt(5, 0);
	        preparedStatement.setString(6, dto.getCategory());
	        preparedStatement.setLong(7, dto.getUserNum());
	        preparedStatement.setString(8, date.date());
	        preparedStatement.setString(9, userImg);
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
	 
	 
	 
 
	 
//	 public List<CommunityDto> read(CommunityDto cdto) throws SQLException {
//		 List<CommunityDto> list = new ArrayList<CommunityDto>();
		 
	 public List<CommunityDto> readMe(CommunityDto cdto) throws SQLException {
		 List<CommunityDto> list = new ArrayList<CommunityDto>();
		 List<LikeListDto> list2 = new ArrayList<LikeListDto>();
		 List<CommentDto> list3 = new ArrayList<CommentDto>();
		 System.out.println(list);
		 
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		LikeListService like = ctx.getBean("likeList",LikeListService.class);
		CommentService comme = ctx.getBean("comment",CommentService.class);
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from community where user_num=?");
			
			preparedStatement.setLong(1, cdto.getUserNum());
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		        	CommunityDto dto = new CommunityDto();
		        	LikeListDto dto2 = new LikeListDto();
		        	CommentDto dto3 = new CommentDto();
		        	
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
		        	String userImg = rs.getString("user_img");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
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
		        	dto.setUserImg(userImg);
		        	
		        	
		        	list2 = like.readAll(dto2,num);
		        	list3 = comme.read2(dto3,num);
		        	if(list2.equals( null)) {
		        		dto2.setId("없음");
		        		list2.add(dto2);
		        	}
		        	if(list3.equals( null)) {
		        		dto3.setComment("없음");
		        		list3.add(dto3);
		        	}
		        	
		        	dto.setList(list2);
		        	dto.setClist(list3);
//		        	System.out.println(dto2);
		        	list.add(dto);
		        	
//		        	System.out.println(list);
		        }
		        stat.close();
		        db.disconnect();
		 return list;
	 }
	 
	 
	 
	 
	 public List<CommunityDto> read(CommunityDto cdto) throws SQLException {
		 List<CommunityDto> list = new ArrayList<CommunityDto>();
		 List<LikeListDto> list2 = new ArrayList<LikeListDto>();
		 List<CommentDto> list3 = new ArrayList<CommentDto>();
		 System.out.println(list);
		 
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		LikeListService like = ctx.getBean("likeList",LikeListService.class);
		CommentService comme = ctx.getBean("comment",CommentService.class);
		 
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from community");
			
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		        	CommunityDto dto = new CommunityDto();
		        	LikeListDto dto2 = new LikeListDto();
		        	CommentDto dto3 = new CommentDto();
		        	
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
		        	String userImg = rs.getString("user_img");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
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
		        	dto.setUserImg(userImg);
		        	
		        	
		        	list2 = like.readAll(dto2,num);
		        	list3 = comme.read2(dto3,num);
		        	if(list2.equals( null)) {
		        		dto2.setId("없음");
		        		list2.add(dto2);
		        	}
		        	if(list3.equals( null)) {
		        		dto3.setComment("없음");
		        		list3.add(dto3);
		        	}
		        	
		        	dto.setList(list2);
		        	dto.setClist(list3);
//		        	System.out.println(dto2);
		        	list.add(dto);
		        	
//		        	System.out.println(list);
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

	public CommunityDto readOne(CommunityDto dto,int nums) throws SQLException {
		// TODO Auto-generated method stub
		
		List<LikeListDto> list2 = new ArrayList<LikeListDto>();
		 List<CommentDto> list3 = new ArrayList<CommentDto>();
		 
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
			LikeListService like = ctx.getBean("likeList",LikeListService.class);
			CommentService comme = ctx.getBean("comment",CommentService.class);
		
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select * from community where num = ?");
		preparedStatement.setInt(1, nums);
		
		 ResultSet rs = preparedStatement.executeQuery();
		
		 if(rs.next()) {
			 	LikeListDto dto2 = new LikeListDto();
	        	CommentDto dto3 = new CommentDto();
			 
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
	        	String userImg = rs.getString("user_img");
	        	
	        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
	        	if(updatedAt != null) {
	        	LocalDateTime dateTime2 = LocalDateTime.parse(updatedAt, formatter);
	        	dto.setUpdatedAt(dateTime2);
	        	}
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
	        	dto.setUserImg(userImg);
//	        	System.out.println(dto);
	        	list2 = like.readAll(dto2,num);
	        	list3 = comme.read2(dto3,num);
	        	if(list2.equals( null)) {
	        		dto2.setId("없음");
	        		list2.add(dto2);
	        	}
	        	if(list3.equals( null)) {
	        		dto3.setComment("없음");
	        		list3.add(dto3);
	        	}
	        	dto.setList(list2);
	        	dto.setClist(list3);
	        	
	        	
//	        	System.out.println(list);
	        }
	        stat.close();
	        db.disconnect();
		
		
		return dto;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	

}
