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
public class Track {
    // TODO: Create a stub for the constructor
    private final List<Point> points;

    public Track() {
        this.points = new ArrayList<>();
    }

    // TODO: Create a stub for readFile()
    public void readFile(String filename) throws IOException {
        points.clear();

        List<String> lines = Files.readAllLines(Paths.get(filename));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        for (String line : lines) {
            if (line.contains("Time") || line.trim().isEmpty()) {
                continue;
            }

            String[] values = line.split(",");
            if (values.length < 4) {
                throw new GPSException("Data line has insufficient elements: " + line);
            }

            try {
                ZonedDateTime timestamp = ZonedDateTime.parse(values[0], formatter);
                double longitude = Double.parseDouble(values[1]);
                double latitude = Double.parseDouble(values[2]);
                double elevation = Double.parseDouble(values[3]);
                Point point = new Point(timestamp, longitude, latitude, elevation);
                points.add(point);
            } catch (DateTimeParseException e) {
                throw new GPSException("Invalid date-time format for timestamp in line: " + line);
            }
        }
    }

    // TODO: Create a stub for add()
    public void add(Point point) {
        points.add(point);
    }

    // TODO: Create a stub for get()
    public Point get(int index) {
        if (index < 0 || index >= points.size()) {
            throw new GPSException("Index out of bounds");
        }
        return points.get(index);
    }

    // TODO: Create a stub for size()
    public int size() {
        return points.size();
    }

    // TODO: Create a stub for lowestPoint()
    public Point lowestPoint() {
        if (points.isEmpty()) {
            throw new GPSException("No points in the track to determine the lowest point.");
        }
        return points.stream()
                .min(Comparator.comparing(Point::getElevation))
                .orElseThrow(() -> new GPSException("Cannot determine the lowest point."));
    }

    // TODO: Create a stub for highestPoint()
    public Point highestPoint() {
        if (points.isEmpty()) {
            throw new GPSException("No points in the track to determine the highest point.");
        }
        return points.stream()
                .max(Comparator.comparing(Point::getElevation))
                .orElseThrow(() -> new GPSException("Cannot determine the highest point."));
    }

    // TODO: Create a stub for totalDistance()
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

    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }
}