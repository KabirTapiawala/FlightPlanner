// A class representing a node in a route
public class RouteNode {
    private CityHub destination; // Destination city hub
    private int cost; // Cost of the route
    private int duration; // Duration of the route
    private RouteNode next; // Next route node

    // Constructor to initialize the route node
    public RouteNode(CityHub destination, int cost, int duration) {
        this.destination = destination;
        this.cost = cost;
        this.duration = duration;
        this.next = null;
    }

    // Getter method for destination city hub
    public CityHub getDestination() {
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

    // Getter method for next route node
    public RouteNode getNext() {
        return next;
    }

    // Setter method for next route node
    public void setNext(RouteNode next) {
        this.next = next;
    }
}
