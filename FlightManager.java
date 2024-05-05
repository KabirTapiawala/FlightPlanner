import java.io.*;
import java.util.*;

public class FlightManager {
    // List to store all city hubs
    private LinkedList<CityHub> hubs = new LinkedList<>();

    // Main method to start the flight manager
    public static void main(String[] args) {
        try {
            // Create an instance of FlightManager
            FlightManager manager = new FlightManager();
            // Initialize flights from FlightData.txt
            manager.initiateFlights("FlightData.txt");
            // Handle flight requests from RequestedFlightFile.txt
            manager.handleRequests("RequestedFlightFile.txt");
        } catch (IOException e) {
            // Catch any IO exceptions and print error message
            System.out.println("Oops! Something went wrong while processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to read flight data from a file and initialize routes
    private void initiateFlights(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read the number of routes
            int routeCount = Integer.parseInt(br.readLine().trim());
            // Iterate through each route
            for (int i = 0; i < routeCount; i++) {
                // Split the line into details: origin, destination, fare, duration
                String[] details = br.readLine().trim().split("\\|");
                // Update city hubs with the route information
                updateCityHubs(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]));
                // Add a reverse route for bidirectional flights
                updateCityHubs(details[1], details[0], Integer.parseInt(details[2]), Integer.parseInt(details[3]));
            }
        }
    }

    // Method to update city hubs with route information
    private void updateCityHubs(String origin, String dest, int fare, int duration) {
        // Find or create city hubs for origin and destination
        CityHub originHub = findOrCreateHub(origin);
        CityHub destHub = findOrCreateHub(dest);
        // Attach the route to the origin city hub
        originHub.attachRoute(new RouteNode(destHub, fare, duration));
    }

    // Method to find or create a city hub
    private CityHub findOrCreateHub(String cityName) {
        // Iterate through existing city hubs
        for (CityHub hub : hubs) {
            // If the hub already exists, return it
            if (hub.getName().equals(cityName)) {
                return hub;
            }
        }
        // If the hub does not exist, create a new one and add it to the list
        CityHub newHub = new CityHub(cityName);
        hubs.add(newHub);
        return newHub;
    }

    // Method to handle flight requests
    private void handleRequests(String file) throws IOException {
        List<String> output = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read the number of requests
            int requestCount = Integer.parseInt(br.readLine().trim());
            // Iterate through each request
            for (int i = 0; i < requestCount; i++) {
                // Split the request into parts: origin, destination, sort type
                String[] parts = br.readLine().trim().split("\\|");
                // Check if the request is malformed
                if (parts.length != 3) {
                    output.add("Oops! Looks like there's a problem with request number " + (i + 1));
                    continue;
                }
                // Extract origin, destination, and sort type
                String origin = parts[0];
                String destination = parts[1];
                char sortBy = Character.toUpperCase(parts[2].charAt(0));
                // Search for routes from origin to destination
                List<TravelPath> paths = searchRoutes(origin, destination);
                // If no routes found, add message to output
                if (paths.isEmpty()) {
                    output.add("Sorry, we couldn't find any available routes from " + origin + " to " + destination);
                } else {
                    // Sort paths based on sort type (cost or time)
                    paths.sort((p1, p2) -> sortBy == 'C' ? Integer.compare(p1.getCost(), p2.getCost()) :
                            Integer.compare(p1.getTime(), p2.getTime()));
                    // Build output message with optimal paths
                    StringBuilder sb = new StringBuilder("Here are the best routes from " + origin + " to " + destination +
                            " based on " + (sortBy == 'C' ? "cost" : "time") + ":\n");
                    // Add up to 3 optimal paths to the output
                    for (int j = 0; j < Math.min(paths.size(), 3); j++) {
                        TravelPath path = paths.get(j);
                        sb.append("Route ").append(j + 1).append(": ").append(path).append(" Cost: ")
                                .append(path.getCost()).append(", Time: ").append(path.getTime()).append("\n");
                    }
                    output.add(sb.toString());
                }
            }
        }
        // Write output to the file
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (String result : output) {
                pw.println(result);
            }
        }
    }

    // Method to search routes from start to finish
    private List<TravelPath> searchRoutes(String start, String finish) {
        // List to store found paths
        List<TravelPath> paths = new ArrayList<>();
        // Stack to perform depth-first search
        Stack<TravelPath> stack = new Stack<>();
        // Find or create city hubs for start and finish
        CityHub startHub = findOrCreateHub(start);
        CityHub endHub = findOrCreateHub(finish);

        // If start or end hub is not found, return empty paths
        if (startHub == null || endHub == null) {
            return paths;
        }

        // Start the search with the initial path containing only the start hub
        stack.push(new TravelPath(startHub));

        // Perform depth-first search until stack is empty
        while (!stack.isEmpty()) {
            // Pop the current path from the stack
            TravelPath currentPath = stack.pop();
            // Get the last hub in the current path
            CityHub lastHub = currentPath.getLastHub();
            // If the last hub is the destination, add the path to the list of found paths
            if (lastHub == endHub) {
                paths.add(new TravelPath(currentPath));
                continue;
            }
            // Iterate through routes from the last hub
            for (RouteNode currentNode : lastHub.getRoutes()) {
                // If the current path does not contain the destination of the route, create a new path
                if (!currentPath.containsHub(currentNode.getDestination())) {
                    // Extend the current path with the destination of the route
                    TravelPath newPath = new TravelPath(currentPath, currentNode.getDestination(),
                            currentNode.getCost(), currentNode.getTime());
                    // Push the new path to the stack for further exploration
                    stack.push(newPath);
                }
            }
        }

        return paths;
    }
}
