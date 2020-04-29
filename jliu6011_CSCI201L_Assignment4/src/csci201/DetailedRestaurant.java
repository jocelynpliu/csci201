package csci201;

public class DetailedRestaurant {
	private String name;
	private String img_url;
	private String address;
	private String phone;
	private String cuisine;
	private String price;
	private Double rating;
	
	public DetailedRestaurant(String name) {
		this.name = name;
	}
	
	public DetailedRestaurant(String name, String img_url, String address, String phone, String cuisine, String price, Double rating) {
		this.name = name;
		this.img_url = img_url;
		this.address = address;
		this.phone = phone;
		this.cuisine = cuisine;
		this.price = price;
		this.rating = rating;
	}
	
	public String getName() { 
		return name;
	}
	
	public String getImageURL() {
		return img_url;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getCuisine() {
		return cuisine;
	}
	
	public String getPrice() {
		return price;
	}
	
	public Double getRating() {
		return rating;
	}
}


