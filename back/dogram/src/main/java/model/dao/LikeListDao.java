package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import model.DBHandler;
import model.DataBase;
import model.dao.daol.LikeListDaoI;
import model.dto.UserDto;

@Component
public class LikeListDao implements LikeListDaoI{
	
	private final DataBase db;
	
	public LikeListDao(DBHandler db) {
		this.db = db;
		
	}
	
	 public int create(UserDto dto) throws ClassNotFoundException, SQLException{
	        Connection c = db.connect();

	        PreparedStatement preparedStatement = c.prepareStatement(
	                "insert into user (id,password,email) value(?,?,?)");

	        preparedStatement.setString(1, "123");
	        preparedStatement.setString(2, "123");
	        preparedStatement.setString(3, "123@123");
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	        db.disconnect();
	        return 0;
	    }
	

}
