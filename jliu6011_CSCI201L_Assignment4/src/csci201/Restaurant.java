package csci201;

public class Restaurant {
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String image_url;
    private String url;
    private String id;
    

    public Restaurant(String name, double latitude, double longitude, String address, String image_url, String url, String id) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.image_url = image_url;
        this.url = url;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getImageUrl() {
    	return image_url;
    }
    
    public String getUrl() {
    	return url;
    }
    
    public String getID() {
    	return id;
    }
}
