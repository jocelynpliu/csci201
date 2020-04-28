package csci201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YelpWrapper {
    private static final String API_KEY = "Ew_R71O_zEkisCkeEy0oGsBcGxdVHioakJJvMM0N3RmrK4TPs-ia9JIHe94ROJnsH37YjL6MUKNVeWtT2281xKDtl0V-gQVdar10S8k4kcpY9WAtKYyWf07x5oSbXnYx";

    private static String buildURL(String name, double latitude, double longitude, String sortingoption) {

    	// Handling special cases for the Yelp API
    	name = name.replaceAll("\\s+", "-");
		name = name.replaceAll(" ", "-");
		name = name.replaceAll("’", "'");

		String url = "https://api.yelp.com/v3/businesses/search?" +
                "term=" + name +
                "&latitude=" + latitude +
                "&longitude=" + longitude;
        
        if (sortingoption != null && !sortingoption.equals("")) {
            return url + "&sort_by=" + sortingoption; 
        }
        
        return url;
//                + 
    }
    
    private static String buildDetailURL(String ID) {
		return "https://api.yelp.com/v3/businesses/" + ID;
          
    }

    private static String getRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization","Bearer " + API_KEY);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return in.readLine();
    }
    
    public static Vector<Restaurant> getRestaurants(String name, double latitude, double longitude, String sortingoption) throws IOException, ParseException {
        // Get JSON string
        String jsonString = getRequest(buildURL(name, latitude, longitude, sortingoption));

        // Parse the JSON using JSON.Simple
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) ((JSONObject) parser.parse(jsonString)).get("businesses");
        
        Vector<Restaurant> restaurants= new Vector<Restaurant>();
        
        for (int i = 0; i < 10; i++) {
        	Object restaurant = data.get(i);
            String restaurantName = (String) ((JSONObject) restaurant).get("name");
            
            JSONObject coordinates = (JSONObject) ((JSONObject) restaurant).get("coordinates");
            double restaurantLat = (double) coordinates.get("latitude");
            double restaurantLong = (double) coordinates.get("longitude");
            
            JSONObject location = (JSONObject) ((JSONObject) restaurant).get("location");
            String address1 = (String) location.get("address1");
            String address2 = (String) location.get("address1");
            String city = (String) location.get("city");
            String state = (String) location.get("state");
            String zip_code = (String) location.get("zip_code");
            String restaurantAddress = address1 + " " + city + ", " + state + " " + zip_code;
            String id = (String) ((JSONObject) restaurant).get("id");
            
            String image_url = (String) ((JSONObject) restaurant).get("image_url");
            String url = (String) ((JSONObject) restaurant).get("url");
            
            Restaurant r = new Restaurant(restaurantName, restaurantLat, restaurantLong, restaurantAddress, image_url, url, id);
            restaurants.add(r);
        }
        
        return restaurants;
        // Return null if restaurant not found
    }
    
    
    // need Address, Phone #, Cuisine, Price Range, and Rating
    public static DetailedRestaurant getDetailedRestaurant(String ID) throws IOException, ParseException {
        // Get JSON string
        String jsonString = getRequest(buildDetailURL(ID));

        // Parse the JSON using JSON.Simple
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) (parser.parse(jsonString));

        String restaurantName = (String) (data.get("name"));
        
        DetailedRestaurant d = new DetailedRestaurant(restaurantName);
        return d;
        // Return null if restaurant not found
    }
}