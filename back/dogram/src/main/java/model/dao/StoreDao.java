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
import model.dao.daol.StroeDaoI;
import model.dto.CommunityDto;
import model.dto.LikeListDto;
import model.dto.StoreDto;
import model.dto.UserDto;
import model.dto.WantListDto;

@Component
public class StoreDao implements StroeDaoI{
	
	private final DataBase db;
	
	public StoreDao(DBHandler db) {
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
	
	
	 public int create(StoreDto dto) throws ClassNotFoundException, SQLException{
		 
		 	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
	    	DateNow date = ctx.getBean("datenow",DateNow.class);
		 
		 
		 
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into store (user_num,content,price,created_at,category,want_count,title) value(?,?,?,?,?,?,?)");

	        preparedStatement.setLong(1, dto.getUserNum());
	        preparedStatement.setString(2, dto.getContent());
	        preparedStatement.setString(3, dto.getPrice());
	        preparedStatement.setString(4, date.date());
	        preparedStatement.setString(5, dto.getCategory());
	        preparedStatement.setInt(6, 0);
	        preparedStatement.setString(7, dto.getTitle());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 0;
	    }

	public List<StoreDto> read(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<StoreDto> list = new ArrayList<StoreDto>();
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from store");
			
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		
		        	StoreDto dto2 = new StoreDto();
		        	
		        	Long num = rs.getLong("num");
		        	Long userNum = rs.getLong("user_num");
		        	String product = rs.getString("content");
		        	String price = rs.getString("price");
		        	String createdAt = rs.getString("created_at");
		        	String updatedAt = rs.getString("updated_at");
		        	String category = rs.getString("category");
		        	int wantCount = rs.getInt("want_count");
		        	String buyer = rs.getString("buyer");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		        	
		        	dto2.setNum(num);
		        	dto2.setUserNum(userNum);
		        	dto2.setContent(product);
		        	dto2.setPrice(price);
		        	dto2.setCreatedAt(dateTime);
		        	dto2.setUpdatedAt(dateTime);
		        	dto2.setCategory(category);
		        	dto2.setWantCount(wantCount);
		        	dto2.setBuyer(userNum);
		        	
		        	list.add(dto2);
		        	
		        }
		
		return list;
	}

	public int addWant(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("123");
		Connection c = db.connect();

        PreparedStatement preparedStatement = c.prepareStatement(
                "insert into want_list (user_num,store_num,title) value(?,?,?)");

        preparedStatement.setLong(1, dto.getUserNum());
        preparedStatement.setLong(2, dto.getStoreNum());
        preparedStatement.setString(3, dto.getTitle());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        db.disconnect();
        
        return 0;
	}
	

	public Long delCheck(WantListDto dto) throws SQLException {
		System.out.println("1111");
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num from want_list where store_num = ? and user_num = ?");
		
		preparedStatement.setLong(1, dto.getStoreNum());
		preparedStatement.setLong(2, dto.getUserNum());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	long cc = rs.getLong("num");
	        	System.out.println(cc);
	            return cc;
	        }
	        stat.close();

	        db.disconnect();
		
		return 0L;
	}



	public int delet(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("del");
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("delete from want_list where store_num = ? and user_num = ?");
		
		preparedStatement.setLong(1, dto.getStoreNum());
		preparedStatement.setLong(2, dto.getUserNum());
		
		 int rs = preparedStatement.executeUpdate();
		 
		 
		  stat.close();

	        db.disconnect();
		return rs;
	}



	public int update(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
    	DateNow date = ctx.getBean("datenow",DateNow.class);
	 
        Connection c = db.connect();

        PreparedStatement preparedStatement = c.prepareStatement(
                "update store set content=?, price=?, updated_at=?, category=?, title=? where num=? and user_num=?");

        preparedStatement.setString(1, dto.getContent());
        preparedStatement.setString(2, dto.getPrice());
        preparedStatement.setString(3, date.date());
        preparedStatement.setString(4, dto.getCategory());
        preparedStatement.setString(5, dto.getTitle());
        preparedStatement.setLong(6, dto.getNum());
        preparedStatement.setLong(7, dto.getUserNum());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        db.disconnect();
        return 1;
	}



	public int delete(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("delete from store where num = ? and user_num = ?");
		
		preparedStatement.setLong(1, dto.getNum());
		preparedStatement.setLong(2, dto.getUserNum());
		
		 int rs = preparedStatement.executeUpdate();
		 
		 
		  stat.close();

	        db.disconnect();
		return rs;
	}



	public List<WantListDto> myRead(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
		List<WantListDto> list = new ArrayList<WantListDto>();
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from want_list where user_num = ?");
			
			preparedStatement.setLong(1, dto.getUserNum());
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		
		        	WantListDto dto2 = new WantListDto();
		        	
		        	Long num = rs.getLong("num");
		        	Long user_num = rs.getLong("user_num");
		        	Long storeNum = rs.getLong("store_num");
		        	String title = rs.getString("title");
		        	
		        	dto2.setUserNum(num);
		        	dto2.setStoreNum(storeNum);
		        	dto2.setTitle(title);
		        	
		        	list.add(dto2);
		        	
		        }
		
		return list;
		
	}



	public int getTitle(WantListDto dto) throws SQLException {
		// TODO Auto-generated method stub
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select title from store where num = ?");
		
		preparedStatement.setLong(1, dto.getStoreNum());
		
		 ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	String cc = rs.getString("title");
	        	dto.setTitle(cc);
	        	System.out.println(cc);
	            return 1;
	        }
	        stat.close();

	        db.disconnect();
		
		return -1;
	}



	public List<StoreDto> read2(StoreDto dto) throws SQLException {
		// TODO Auto-generated method stub
		List<StoreDto> list = new ArrayList<StoreDto>();
		 Connection c = db.connect();
			Statement stat = c.createStatement();
			
			PreparedStatement preparedStatement = 
					c.prepareStatement("select * from store where user_num=?");
			
			preparedStatement.setLong(1, dto.getUserNum());
			
			 ResultSet rs = preparedStatement.executeQuery();
			
		        while(rs.next()) {
		
		        	StoreDto dto2 = new StoreDto();
		        	
		        	Long num = rs.getLong("num");
		        	Long userNum = rs.getLong("user_num");
		        	String product = rs.getString("content");
		        	String price = rs.getString("price");
		        	String createdAt = rs.getString("created_at");
		        	String updatedAt = rs.getString("updated_at");
		        	String category = rs.getString("category");
		        	int wantCount = rs.getInt("want_count");
		        	String buyer = rs.getString("buyer");
		        	
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        	LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		        	
		        	dto2.setNum(num);
		        	dto2.setUserNum(userNum);
		        	dto2.setContent(product);
		        	dto2.setPrice(price);
		        	dto2.setCreatedAt(dateTime);
		        	dto2.setUpdatedAt(dateTime);
		        	dto2.setCategory(category);
		        	dto2.setWantCount(wantCount);
		        	dto2.setBuyer(userNum);
		        	
		        	list.add(dto2);
		        	
		        }
		
		return list;
	}
	
	
	
	
	
	
	
	
	
}
