package com.bordingvista.promotion.client;

import com.bordingvista.promotion.pojo.Product;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


/**
 * This class is used to test product creation by giving mock product object
 * 
 * @author imran.zahid
 */
public class ProductClient {
	public static void main(String[] args) {
		try {

			Product product = new Product();
			
			product.setDescription("Xiomi Mini");
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8084/com.bordingvista.promotion/rest/service/addproduct");

			ClientResponse response = webResource.accept("application/json")
					.type("application/json").post(ClientResponse.class, product);

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
