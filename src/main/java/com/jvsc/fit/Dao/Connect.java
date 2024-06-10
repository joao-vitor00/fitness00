package com.jvsc.fit.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection open() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:h2:file:C:\\Users\\jcarvalho\\Desktop\\fit\\BDD", "sa", "");
		return con;
	}
}
