package jliu6011_CSCI201L_Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class Assignment2 {
	private static Scanner s;
	private static RestaurantList restaurantList;
	private static ArrayList<Order> schedule;
	private static Double userLatitude;
	private static Double userLongitude;
	private static int totalDrivers;

	public static void main(String[] args) {
		// gets filename from user input
		s = new Scanner(System.in);

		loadRestaurantFile();
		loadOrderFile();
		getCoordinates();
		Driver.setUserCoordinates(userLatitude, userLongitude);

		try {
			ExecutorService executor = Executors.newFixedThreadPool(totalDrivers);
			long start = System.currentTimeMillis();

			for (int i = 0; i < schedule.size(); i++) {
				Order currOrder = schedule.get(i);
				if (System.currentTimeMillis() - start < currOrder.getReadyTime()) {
					long wait = Math.round(currOrder.getReadyTime() - (System.currentTimeMillis() - start));
					Thread.sleep(wait);
				}
				Restaurant currRestaurant = restaurantList.getRestaurant(currOrder.getRestaurant());
				Driver currDriver = currRestaurant.getAvailableDriver();
				currDriver.addOrder(currOrder);
				executor.execute(currDriver);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {};
			System.out.println("All orders complete!");
			
		} catch (InterruptedException ie) {
			System.out.println("Thread was interrupted.");
		}
		
		
		s.close();
	}

	private static void loadRestaurantFile() {

		String filename1 = null;
		while (filename1 == null) {
			try {
				System.out.println("What is the name of the file containing the restaurant information?");
				filename1 = s.nextLine();
				// read in file, json --> java object Restaurant
				Gson gson = new Gson();
				restaurantList = gson.fromJson(new FileReader(filename1), RestaurantList.class);
			} catch (FileNotFoundException fnfe) {
				filename1 = null;
				System.out.println("The file could not be found.");
			}
		}

		totalDrivers = 0;
		// getting total number of drivers
		for (int i = 0; i < restaurantList.getData().size(); i++) {
			totalDrivers += restaurantList.getData().get(i).getDrivers();
			restaurantList.getData().get(i).initializeDriverList();
			restaurantList.getData().get(i).createDrivers();
		}
	}

	private static void loadOrderFile() {
		String filename2 = null;
		while (filename2 == null) {
			try {
				schedule = new ArrayList<Order>();
				System.out.println("What is the name of the file containing the schedule information?");
				filename2 = s.nextLine();
				File file2 = new File(filename2);
				Scanner fs = new Scanner(file2);

				while (fs.hasNextLine()) {
					String orderLine = fs.nextLine();
					List<String> orderList = Arrays.asList(orderLine.split(","));
					Double tempReady = Double.parseDouble(orderList.get(0));
					String tempName = orderList.get(1).trim();
					String tempFood = orderList.get(2).trim();
					Order tempOrder = new Order(tempReady, tempName, tempFood);
					schedule.add(tempOrder);
				}
				fs.close();
			} catch (FileNotFoundException fnfe) {
				filename2 = null;
				System.out.println("The file could not be found.");
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input type!");
				System.exit(0);
			}
		}

	}

	private static void getCoordinates() {
		while (userLatitude == null) {
			try {
				System.out.println("What is the latitude?");

				userLatitude = Double.parseDouble(s.nextLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a double.");
			}
		}
		while (userLongitude == null) {
			try {
				System.out.println("What is the longitude?");

				userLongitude = Double.parseDouble(s.nextLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a double.");
			}
		}
	}

}
