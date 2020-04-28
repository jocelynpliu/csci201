package jliu6011_CSCI201L_Assignment2;

public class Order {
	private Double readyTime;
	private String restaurant, food;

	public Order(Double readyTime, String restaurant, String food) {
		this.readyTime = readyTime * 1000; // convert to milliseconds
		this.restaurant = restaurant;
		this.food = food;
	}

	public Double getReadyTime() {
		return readyTime;
	}
	
	public String getRestaurant() {
		return restaurant;
	}
	
	public String getFood() {
		return food;
	}

}
