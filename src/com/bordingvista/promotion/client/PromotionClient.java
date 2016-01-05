package com.bordingvista.promotion.client;

import com.bordingvista.promotion.pojo.Product;
import com.bordingvista.promotion.pojo.Promotion;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * This class is used to test promotion creation by giving mock promotion object
 * 
 * @author imran.zahid
 */
public class PromotionClient {
	public static void main(String[] args) {
		try {

			Promotion promotion = new Promotion();

			promotion.setDescription("Eid Offer Kids");
			promotion.setDiscount(1000);
			Product product = new Product();
			product.setId(7);
			promotion.setProduct(product);
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8084/com.bordingvista.promotion/rest/service/addpromotion");

			ClientResponse response = webResource.accept("application/json")
					.type("application/json")
					.post(ClientResponse.class, promotion);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Server response .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
