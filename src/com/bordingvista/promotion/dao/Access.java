package com.bordingvista.promotion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.bordingvista.promotion.pojo.Product;
import com.bordingvista.promotion.pojo.Promotion;

/**
 * Class that contains all the different methods to query data for rest call 
 *
 * @author imran.zahid
 */
public class Access {
	
	/**
	 * This method is used to get all products with tagged promotions
	 * 
	 * @param connection This is the database connection
	 * @return products Collection of all products.
	 */   
	public ArrayList<Product> getProducts(Connection connection)
			throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();
		PreparedStatement statement = connection
				.prepareStatement("select * from product");
		ResultSet resultSet = statement.executeQuery();
		try {
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setDescription(resultSet.getString("description"));
				PreparedStatement statementPromotion = connection
						.prepareStatement("select * from promotion where  id in(select promotion_id from product_promotion where product_id = "
								+ product.getId() + ")");
				ResultSet promotionSet = statementPromotion.executeQuery();
				ArrayList<Promotion> promotions = new ArrayList<Promotion>();
				while (promotionSet.next()) {
					Promotion promotion = new Promotion();
					promotion.setId(promotionSet.getInt("id"));
					promotion.setDescription(promotionSet
							.getString("description"));
					promotion.setDiscount(promotionSet.getDouble("discount"));
					promotions.add(promotion);
				}
				statementPromotion.close();
				product.setPromotionList(promotions);
				productList.add(product);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productList;
	}

	/**
	 * This method is used to get all promotions with tagged product
	 * 
	 * @param connection This is the database connection
	 * @return promotions Collection of all promotions.
	 */ 
	public ArrayList<Promotion> getPromotions(Connection connection)
			throws SQLException {
		ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
		PreparedStatement statement = connection
				.prepareStatement("select * from promotion");
		ResultSet resultSet = statement.executeQuery();
		try {
			while (resultSet.next()) {
				Promotion promotion = new Promotion();
				promotion.setId(resultSet.getInt("id"));
				PreparedStatement statementProduct = connection
						.prepareStatement("select * from product where  id in(select product_id from product_promotion where promotion_id = "
								+ promotion.getId() + ")");
				ResultSet productSet = statementProduct.executeQuery();
				Product product = null;
				while (productSet.next()) {
					product = new Product();
					product.setId(productSet.getInt("id"));
					product.setDescription(productSet.getString("description"));
				}
				statementProduct.close();
				promotion.setDescription(resultSet.getString("description"));
				promotion.setDiscount(resultSet.getDouble("discount"));
				promotion.setProduct(product);
				promotionList.add(promotion);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return promotionList;
	}
	
	/**
	 * This method is used to get add product in database
	 * 
	 * @param connection This is the database connection 
	 * @param product Product Object
	 * @return product Added product in database with unique auto generated Id.
	 */ 
	public Product addProduct(Connection connection, Product product) {
		String query = " insert into product (description)" + " values ('"
				+ product.getDescription() + "')";
		try {
			PreparedStatement statement = connection.prepareStatement(query,
					new String[] { "id" });
			int result = statement.executeUpdate();
			if (result == 1) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					System.out.println(generatedKeys.getLong(1));
					product.setId((int) generatedKeys.getLong(1));
				} else {
					System.out.println("Autoincremented Product ID not found.");
				}
			} else {
				System.out.println("Product is not added in Database.");
			}
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}
	
	/**
	 * This method is used to get add promotion in database
	 * 
	 * @param connection This is the database connection 
	 * @param promotion Promotion Object
	 * @return product Added promotion in database with unique auto generated Id.
	 */
	public Promotion addPromotion(Connection connection, Promotion promotion) {
		String queryPromotion = "insert into promotion (description, discount)"
				+ " values ('" + promotion.getDescription() + "', "
				+ promotion.getDiscount() + ")";
		try {
			PreparedStatement statementPromotion = connection.prepareStatement(
					queryPromotion, new String[] { "id" });
			int result = statementPromotion.executeUpdate();
			if (result == 1) {
				ResultSet generatedKeys = statementPromotion.getGeneratedKeys();
				if (generatedKeys.next()) {
					promotion.setId((int) generatedKeys.getLong(1));
				} else {
					System.out.println("Autoincremented Product ID not found.");
				}
			} else {
				System.out.println("Prmotion is not added in Database.");
			}
			String relationQuery = "insert into product_promotion (product_id, promotion_id)"
					+ " values ("
					+ promotion.getProduct().getId()
					+ ", "
					+ promotion.getId() + ")";
			PreparedStatement joinStatement = connection
					.prepareStatement(relationQuery);
			joinStatement.execute(relationQuery);
			statementPromotion.close();
			joinStatement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return promotion;
	}

}
