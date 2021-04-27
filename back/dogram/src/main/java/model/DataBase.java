package model;

import java.sql.Connection;

public interface DataBase {
	
	public Connection connect();
	public int disconnect();

}
