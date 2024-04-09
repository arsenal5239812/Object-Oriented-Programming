/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Lirong Guo
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.PrintWriter;

public class Track {
    // TODO: Create a stub for the constructor
    private final List<Point> points;

    // Constructor initializes the list
    public Track() {
        this.points = new ArrayList<>();
    }

    // TODO: Create a stub for readFile()
    public void readFile(String filename) throws IOException {
        // Clear existing points to avoid the redundant
        points.clear();

        List<String> lines = Files.readAllLines(Paths.get(filename));
        // Define the expected ISO date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        for (String line : lines) {
            // Skip header or empty lines
            if (line.contains("Time") || line.trim().isEmpty()) {
                continue;
            }

            String[] values = line.split(",");
            if (values.length < 4) {
                throw new GPSException("Data line has insufficient elements: " + line);
            }

            try {
                // Parse and add the point to the list
                ZonedDateTime timestamp = ZonedDateTime.parse(values[0], formatter);
                double longitude = Double.parseDouble(values[1]);
                double latitude = Double.parseDouble(values[2]);
                double elevation = Double.parseDouble(values[3]);
                Point point = new Point(timestamp, longitude, latitude, elevation);
                points.add(point);
            }
            // Handle invalid date-time format
            catch (DateTimeParseException e) {
                throw new GPSException("Invalid date-time format for timestamp in line: " + line);
            }
        }
    }

    // TODO: Create a stub for add()
    /**
     * Adds a new point to the track.
     *
     * @param point The point to add.
     */
    public void add(Point point) {
        points.add(point);
    }

    // TODO: Create a stub for get()
    /**
     * Retrieves a point from the track by its index.
     *
     * @param index The index of the point to retrieve.
     * @return The requested point.
     */
    public Point get(int index) {
        if (index < 0 || index >= points.size()) {
            throw new GPSException("Index out of bounds");
        }
        return points.get(index);
    }

    // TODO: Create a stub for size()
    /**
     * Returns the number of points in the track.
     *
     * @return The size of the track.
     */
    public int size() {
        return points.size();
    }

    // TODO: Create a stub for lowestPoint()
    /**
     * Finds and returns the lowest point in the track based on elevation.
     *
     * @return The lowest point.
     */
    public Point lowestPoint() {
        if (points.isEmpty()) {
            throw new GPSException("No points in the track to determine the lowest point.");
        }
        return points.stream()
                .min(Comparator.comparing(Point::getElevation))
                .orElseThrow(() -> new GPSException("Cannot determine the lowest point."));
    }

    // TODO: Create a stub for highestPoint()
    /**
     * Finds and returns the highest point in the track based on elevation.
     *
     * @return The highest point.
     */
    public Point highestPoint() {
        if (points.isEmpty()) {
            throw new GPSException("No points in the track to determine the highest point.");
        }
        return points.stream()
                .max(Comparator.comparing(Point::getElevation))
                .orElseThrow(() -> new GPSException("Cannot determine the highest point."));
    }

    // TODO: Create a stub for totalDistance()
    /**
     * Calculates the total distance traveled in the track.
     *
     * @return The total distance in meters.
     */
    public double totalDistance() {
        if (points.size() < 2) {
            throw new GPSException("Not enough points to calculate total distance.");
        }

        double distance = 0.0;
        for (int i = 1; i < points.size(); i++) {
            distance += Point.greatCircleDistance(points.get(i - 1), points.get(i));
        }
        return distance;
    }

    // TODO: Create a stub for averageSpeed()
    /**
     * Calculates the average speed over the track.
     *
     * @return The average speed in meters per second.
     */
    public double averageSpeed() {
        if (points.size() < 2) {
            throw new GPSException("Not enough points to calculate average speed.");
        }
        long seconds = ChronoUnit.SECONDS.between(points.get(0).getTime(),
                points.get(points.size() - 1).getTime());
        if (seconds == 0) {
            throw new GPSException("Time interval is too short to calculate average speed.");
        }
        return totalDistance() / seconds;
    }

    /**
     * Returns a copy of the list of points in the track.
     *
     * @return A new list containing all points in the track.
     */
    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }

    /**
     * Writes the track data to a KML file.
     *
     * @param filename The path to the output file.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void writeKML(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(filename)) {

            // KML header
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            // HTTP link is not secure,
            // but the safer HTTPS link will make parsing aborted error and I don't know the reason
            writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
            writer.println("<Document>");
            writer.println("<name>Your Track</name>");

            // Write each point as a Placemark
            for (Point point : points) {
                writer.println("<Placemark>");
                writer.println("<Point>");
                writer.printf("<coordinates>%.6f,%.6f,%.1f</coordinates>%n",
                        point.getLongitude(), point.getLatitude(), point.getElevation());
                writer.println("</Point>");
                writer.println("</Placemark>");
            }

            // KML footer
            writer.println("</Document>");
            writer.println("</kml>");
        }
    }
}