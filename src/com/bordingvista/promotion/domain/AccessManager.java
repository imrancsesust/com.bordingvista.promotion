package com.bordingvista.promotion.domain;

import java.sql.Connection;
import java.util.ArrayList;

import com.bordingvista.promotion.dao.Access;
import com.bordingvista.promotion.dao.Database;
import com.bordingvista.promotion.pojo.Product;
import com.bordingvista.promotion.pojo.Promotion;

/**
 * This class is a wrapper class of database
 * 
 * @author imran.zahid
 */
public class AccessManager {
	
	/**
	 * This method is used to get all products with tagged promotions
	 * 
	 * @return products Collection of all products. 
	 */
	public ArrayList<Product> getProducts() throws Exception {
		Database database = new Database();
		Connection connection = database.getConnection();
		Access access = new Access();
		return access.getProducts(connection);
	}
	
	/**
	 * This method is used to get all promotions with tagged product
	 * 
	 * @return promotions Collection of all promotions. 
	 */
	public ArrayList<Promotion> getPromotions() throws Exception {
		Database database = new Database();
		Connection connection = database.getConnection();
		Access access = new Access();
		return access.getPromotions(connection);
	}
	
	/**
	 * This method is used to get add product in database
	 *  
	 * @param product Product Object
	 * @return product Added product in database with unique auto generated Id.
	 */ 
	public Product addProduct(Product product) throws Exception {
		Database database = new Database();
		Connection connection = database.getConnection();
		Access access = new Access();
		return access.addProduct(connection, product);
	}
	
	/**
	 * This method is used to get add promotion in database
	 * 
	 * @param promotion Promotion Object
	 * @return product Added promotion in database with unique auto generated Id.
	 */
	public Promotion addPromotion(Promotion promotion) throws Exception {
		Database database = new Database();
		Connection connection = database.getConnection();
		Access access = new Access();
		return access.addPromotion(connection, promotion);
	}
}
