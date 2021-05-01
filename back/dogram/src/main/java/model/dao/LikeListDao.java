package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import model.DBHandler;
import model.DataBase;
import model.dao.daol.LikeListDaoI;
import model.dto.LikeListDto;
import model.dto.UserDto;

@Component
public class LikeListDao implements LikeListDaoI{
	
	private final DataBase db;
	
	public LikeListDao(DBHandler db) {
		this.db = db;
		
	}
	
	 public int create(LikeListDto dto) throws ClassNotFoundException, SQLException{
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into like_list (community_num,user_num) value(?,?)");

	        preparedStatement.setLong(1, dto.getCommunityNum());
	        preparedStatement.setLong(2, dto.getUserNum());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 1;
	    }

	public Long checkCookie(String cookie) throws SQLException {
		
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

	
	
	public Long delCheck(LikeListDto dto) throws SQLException {
		System.out.println("1111");
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("select num from like_list where community_num = ? and user_num = ?");
		
		preparedStatement.setLong(1, dto.getCommunityNum());
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
	
	public int delet(LikeListDto dto) throws SQLException {
		System.out.println("del");
		Connection c = db.connect();
		Statement stat = c.createStatement();
		
		PreparedStatement preparedStatement = 
				c.prepareStatement("delete from like_list where community_num = ? and user_num = ?");
		
		preparedStatement.setLong(1, dto.getCommunityNum());
		preparedStatement.setLong(2, dto.getUserNum());
		
		 int rs = preparedStatement.executeUpdate();
		 
		 
		  stat.close();

	        db.disconnect();
		return rs;
	}

}
