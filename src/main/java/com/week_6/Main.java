package com.week_6;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    GeoHash geoHash = new GeoHash();

    // Adding some well-known locations
    System.out.println("=== Adding Coordinates ===");

    // Indian cities
    geoHash.addCoordinate(28.6139f, 77.2090f, "New Delhi");
    geoHash.addCoordinate(19.0760f, 72.8777f, "Mumbai");
    geoHash.addCoordinate(12.9716f, 77.5946f, "Bangalore");
    geoHash.addCoordinate(13.0827f, 80.2707f, "Chennai");
    geoHash.addCoordinate(22.5726f, 88.3639f, "Kolkata");

    // Nearby points to Delhi (within ~10-50 km)
    geoHash.addCoordinate(28.4595f, 77.0266f, "Gurgaon");
    geoHash.addCoordinate(28.5355f, 77.3910f, "Noida");
    geoHash.addCoordinate(28.6692f, 77.4538f, "Ghaziabad");

    // International cities
    geoHash.addCoordinate(40.7128f, -74.0060f, "New York");
    geoHash.addCoordinate(51.5074f, -0.1278f, "London");
    geoHash.addCoordinate(35.6762f, 139.6503f, "Tokyo");

    System.out.println("Added 11 locations to the GeoHash.\n");

    // Finding nearest locations to a query point
    System.out.println("=== Finding Nearest Locations ===\n");

    // Query near Delhi - should find Delhi and nearby places
    System.out.println("Query: Near New Delhi (28.61, 77.20)");
    List<String> results = geoHash.findNearest(28.61f, 77.20f);
    System.out.println("Results: " + results);
    System.out.println();

    // Query near Mumbai
    System.out.println("Query: Near Mumbai (19.08, 72.88)");
    results = geoHash.findNearest(19.08f, 72.88f);
    System.out.println("Results: " + results);
    System.out.println();

    // Query near Gurgaon - should find Gurgaon and possibly Delhi
    System.out.println("Query: Near Gurgaon (28.46, 77.03)");
    results = geoHash.findNearest(28.46f, 77.03f);
    System.out.println("Results: " + results);
    System.out.println();

    // Query a random point in India (Jaipur area)
    System.out.println("Query: Near Jaipur (26.92, 75.78) - not added, checking nearest");
    results = geoHash.findNearest(26.92f, 75.78f);
    System.out.println("Results: " + results);
    System.out.println();

    // Query near New York
    System.out.println("Query: Near New York (40.71, -74.00)");
    results = geoHash.findNearest(40.71f, -74.00f);
    System.out.println("Results: " + results);
    System.out.println();

    // Query near Tokyo
    System.out.println("Query: Near Tokyo (35.68, 139.65)");
    results = geoHash.findNearest(35.68f, 139.65f);
    System.out.println("Results: " + results);
    System.out.println();

    // Edge case: Exact same coordinate as stored
    System.out.println("Query: Exact Bangalore coordinates (12.9716, 77.5946)");
    results = geoHash.findNearest(12.9716f, 77.5946f);
    System.out.println("Results: " + results);
    System.out.println();

    // Edge case: Point at origin
    System.out.println("Query: Near origin (0.0, 0.0) - no nearby points stored");
    results = geoHash.findNearest(0.0f, 0.0f);
    System.out.println("Results: " + results);
  }
}
