// A class representing an aerial route between two cities
public class AerialRoute {
    private String origin; // Origin city of the route
    private String destination; // Destination city of the route
    private int cost; // Cost of the route
    private int duration; // Duration of the route

    // Constructor to initialize the aerial route
    public AerialRoute(String origin, String destination, int cost, int duration) {
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.duration = duration;
    }

    // Getter method for origin city
    public String getOrigin() {
        return origin;
    }

    // Getter method for destination city
    public String getDestination() {
        return destination;
    }

    // Getter method for cost
    public int getCost() {
        return cost;
    }

    // Getter method for duration
    public int getTime() {
        return duration;
    }
}
