package csci201;

public class Restaurant {
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String image_url;
    private String url;
    

    public Restaurant(String name, double latitude, double longitude, String address, String image_url, String url) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.image_url = image_url;
        this.url = url;
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
}
