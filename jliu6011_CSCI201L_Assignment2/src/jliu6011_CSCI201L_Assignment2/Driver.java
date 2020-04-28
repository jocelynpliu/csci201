package jliu6011_CSCI201L_Assignment2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Driver extends Thread {
	private Double restaurantLatitude, restaurantLongitude; 
	private static Double userLatitude, userLongitude;
	private Boolean available;
	private Lock driverLock;
	private ArrayList<Order> orders;

	public Driver(Restaurant restaurant) {
		restaurantLatitude = restaurant.getLatitude();
		restaurantLongitude = restaurant.getLongitude();
		available = true;
		driverLock = new ReentrantLock();
		orders = new ArrayList<Order>();
	}

	public void run() {
		try {
			available = false;
			driverLock.lock();
			Order currOrder = orders.get(0);
			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp1 + " Starting delivery of " + currOrder.getFood() + " from " + currOrder.getRestaurant() + "!");
			Double distance = 2 * calculateDistance(restaurantLatitude, restaurantLongitude);
			long deliveryTime = Math.round(distance * 1000);
			
			Thread.sleep(deliveryTime);
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp2  + " Finished delivery of " + currOrder.getFood() + " from " + currOrder.getRestaurant() + "!");
			orders.remove(0);
			available = true;
			
		} catch (InterruptedException ie) {
			System.out.println("Thread was interrupted");
		} finally {
			driverLock.unlock();
		}
		
	}
	
	public static void setUserCoordinates(Double lat, Double lon) {
		userLatitude = lat;
		userLongitude = lon;
	}

	public void addOrder(Order o) {
		orders.add(o);
	}

	public Boolean isAvailable() {
		return available;
	}

	public void setBusy() {
		available = false;
	}
	
	/**
	 * Calculates distance from user to the specified coordinates
	 *
	 * @param latitude  Latitude of restaurant
	 * @param longitude Longitude of restaurant
	 * @return Distance from user to restaurant
	 */
	private static double calculateDistance(Double latitude, Double longitude) {
		return 3963.0 * Math.acos((Math.sin(Math.toRadians(userLatitude)) * Math.sin(Math.toRadians(latitude)))
				+ Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(latitude))
						* Math.cos(Math.toRadians(userLongitude) - Math.toRadians(longitude)));
	}
}
