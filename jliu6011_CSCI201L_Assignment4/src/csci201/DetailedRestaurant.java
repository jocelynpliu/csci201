package csci201;

public class DetailedRestaurant {
	private String name;
	private String address;
	private String phone;
	private String cuisine;
	private String price;
	private Double rating;
	
	public DetailedRestaurant(String name) {
		this.name = name;
	}
	
	public DetailedRestaurant(String name, String address, String phone, String cuisine, String price, Double rating) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.cuisine = cuisine;
		this.price = price;
		this.rating = rating;
	}
	
	public String getName() { 
		return name;
	}
}
