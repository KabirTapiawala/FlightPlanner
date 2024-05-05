import java.util.ArrayList;
import java.util.List;

// A class representing a travel path
public class TravelPath {
    private List<CityHub> hubs; // List of city hubs in the path
    private int totalCost; // Total cost of the path
    private int totalTime; // Total time of the path

    // Constructor to initialize a travel path starting from a given city hub
    public TravelPath(CityHub start) {
        this.hubs = new ArrayList<>();
        this.hubs.add(start);
        this.totalCost = 0;
        this.totalTime = 0;
    }

    // Copy constructor to create a new travel path from an existing one
    public TravelPath(TravelPath other) {
        this.hubs = new ArrayList<>(other.hubs);
        this.totalCost = other.totalCost;
        this.totalTime = other.totalTime;
    }

    // Constructor to extend an existing travel path with a new city hub and route information
    public TravelPath(TravelPath other, CityHub hub, int cost, int time) {
        this(other);
        this.hubs.add(hub);
        this.totalCost += cost;
        this.totalTime += time;
    }

    // Method to check if the travel path contains a specific city hub
    public boolean containsHub(CityHub hub) {
        return this.hubs.contains(hub);
    }

    // Method to get the last city hub in the travel path
    public CityHub getLastHub() {
        return this.hubs.get(this.hubs.size() - 1);
    }

    // Getter method for total cost
    public int getCost() {
        return totalCost;
    }

    // Getter method for total time
    public int getTime() {
        return totalTime;
    }

    // Override toString method to represent the travel path as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CityHub hub : this.hubs) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(hub.getName());
        }
        return sb.toString();
    }
}
