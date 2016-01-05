package com.bordingvista.promotion;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bordingvista.promotion.domain.AccessManager;
import com.bordingvista.promotion.pojo.Product;
import com.bordingvista.promotion.pojo.Promotion;

/**
 * This class is used as REST API of Bording Vista promotion service
 * 
 * @author imran.zahid
 */

@Path("/service")
public class Service {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloFromService() {
		return "Hello Bording Vista promotional service";
	}

	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = null;
		try{
			products =  new AccessManager().getProducts();
		} catch (Exception e){
			e.printStackTrace();
		}
		return products;
	}
	
	@POST
	@Path("/addproduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Product addProduct(Product product) {
		Product addedProduct = null;
		try {
			addedProduct = new AccessManager().addProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addedProduct;
	}
	
	@POST
	@Path("/addpromotion")
	@Produces(MediaType.APPLICATION_JSON)
	public Promotion addPromotion(Promotion promotion) {
		Promotion addedPromotion = null;
		try {
			addedPromotion = new AccessManager().addPromotion(promotion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addedPromotion;
	}
	
	@GET
	@Path("/promotions")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Promotion> getAllPromotion() {
		ArrayList<Promotion> promotions = null;
		try{
			promotions =  new AccessManager().getPromotions();
		} catch (Exception e){
			e.printStackTrace();
		}
		return promotions;
	}
}
