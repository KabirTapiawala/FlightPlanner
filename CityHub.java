import java.util.LinkedList; // Import LinkedList from java.util package

// A class representing a city hub
public class CityHub {
    private String cityName; // Name of the city hub
    private LinkedList<RouteNode> routes; // List of routes from the city hub

    // Constructor to initialize the city hub
    public CityHub(String cityName) {
        this.cityName = cityName;
        this.routes = new LinkedList<>(); // Initialize the LinkedList to store RouteNode objects
    }

    // Method to attach a route to the city hub
    public void attachRoute(RouteNode routeNode) {
        this.routes.addFirst(routeNode); // Add a route to the beginning of the list
    }

    // Getter method to retrieve the list of routes from the city hub
    public LinkedList<RouteNode> getRoutes() {
        return routes; // Return the list of routes
    }

    // Getter method to retrieve the name of the city hub
    public String getName() {
        return cityName; // Return the name of the city
    }
}
