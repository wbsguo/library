package com.example.sticklistlibrary;

public class FoodBean {
	private String ficationId="ficationId";//分类id
	private String foodName="foodName";//食物名
	private String ficationName;
	private int heard_id;
	public String getFicationId() {
		return ficationId;
	}
	public void setFicationId(String ficationId) {
		this.ficationId = ficationId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getHeard_id() {
		return heard_id;
	}
	public void setHeard_id(int heard_id) {
		this.heard_id = heard_id;
	}

	public String getFicationName() {
		return ficationName;
	}

	public void setFicationName(String ficationName) {
		this.ficationName = ficationName;
	}
}
