package com.jvsc.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	public static Connection open() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fit", "postgres", "1612");
		if(con==null){
			System.out.println("Connection failed");
		}
		return con;
	}
}
