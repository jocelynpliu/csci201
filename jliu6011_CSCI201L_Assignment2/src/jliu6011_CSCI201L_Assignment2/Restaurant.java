package jliu6011_CSCI201L_Assignment2;

import java.util.ArrayList;

public class Restaurant {
	private String name, address;
	private double latitude, longitude;
	private int drivers;
	private ArrayList<String> menu;
	private ArrayList<Driver> driverThreads;

	public Restaurant(String name, String address, double latitude, double longitude, int drivers,
			ArrayList<String> menu) {
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.drivers = drivers;
		this.menu = menu;
	}

	public Driver getAvailableDriver() {
		for (int i = 0; i < drivers; i++) {
			if (driverThreads.get(i).isAvailable()) {
				return driverThreads.get(i);
			}
		}
		return driverThreads.get(0); // returns first driver if all are busy, first driver will complete delivery
										// when free
	}

	public void initializeDriverList() {
		driverThreads = new ArrayList<Driver>();
	}

	public void createDrivers() {
		for (int i = 0; i < drivers; i++) {
			Driver driver = new Driver(this);
			this.driverThreads.add(driver);
		}
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public int getDrivers() {
		return drivers;
	}

	public void displayMenu() {
		if (menu.size() == 1) {
			System.out.println(menu.get(0) + ".");
		} else if (menu.size() == 2) {
			System.out.println(menu.get(0) + " and " + menu.get(1) + ".");
		} else {
			for (int k = 0; k < menu.size() - 1; k++) {
				System.out.print(menu.get(k) + ", ");
			}
			System.out.println("and " + menu.get(menu.size() - 1) + ".");
		}
	}

	public ArrayList<String> getMenu() {
		return menu;
	}

}