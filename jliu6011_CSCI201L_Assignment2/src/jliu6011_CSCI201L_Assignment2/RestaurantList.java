package jliu6011_CSCI201L_Assignment2;

import java.util.ArrayList;

public class RestaurantList {
	ArrayList<Restaurant> data = new ArrayList<Restaurant>();
	
	public ArrayList<Restaurant> getData() {
		return data;
	}
	
	public int size() {
		return data.size();
	}
	
	public Restaurant getRestaurant(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getName().equals(name)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	public void display() {
		for (int i = 0; i < data.size(); i++) {
			System.out.println("name: " + data.get(i).getName());
			System.out.println("address: " + data.get(i).getAddress());
			System.out.println("coordinates: " + data.get(i).getLatitude() + ", " + data.get(i).getLongitude());
			System.out.println("drivers: " + data.get(i).getDrivers());
			System.out.print("menu: ");
			data.get(i).displayMenu();
			System.out.println();
		}
	}
}