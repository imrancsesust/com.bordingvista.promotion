package com.bordingvista.promotion.pojo;

import java.util.ArrayList;

public class Product {
	private int id;
	private String description;
	private ArrayList<Promotion> promotionList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(ArrayList<Promotion> promotionList) {
		this.promotionList = promotionList;
	}
	
}
