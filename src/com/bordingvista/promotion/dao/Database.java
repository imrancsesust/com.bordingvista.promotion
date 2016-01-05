package com.bordingvista.promotion.dao;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Class that contains jdbc connection with mysql server
 * 
 *@author imran.zahid
 */
public class Database {
	
	/**
	 * @return connection Database connection
	 *
	 */
	public Connection getConnection() throws Exception {
		try {
			String conneectionUrl = "jdbc:mysql://localhost:3306/promotionservice";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(conneectionUrl, "root",
					"");
			return connection;
		} catch (Exception e) {
			throw e;
		}

	}
}
