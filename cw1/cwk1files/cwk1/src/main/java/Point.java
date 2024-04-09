/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Lirong Guo
 */

import java.time.ZonedDateTime;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

public class Point {
  // Fields to represent the timestamp, longitude, latitude and elevation
  private final ZonedDateTime timestamp;
  private final double longitude;
  private final double latitude;
  private final double elevation;

  // Constants useful for bounds checking, etc
  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  // TODO: Create a stub for the constructor
  // Constructor initialises the fields
  public Point(ZonedDateTime ts, double lon, double lat, double elev) {
    // Avoiding parameter names from being the same as class property names
    timestamp = ts;
    longitude = lon;
    latitude = lat;
    elevation = elev;

    // Validate that longitude is within the valid range
    if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
      throw new GPSException("Longitude out of valid range: " + longitude);
    }

    // Validate that latitude is within the valid range
    if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
      throw new GPSException("Latitude out of valid range: " + latitude);
    }
  }

  // TODO: Create a stub for getTime()
  // Retrieves the timestamp
  public ZonedDateTime getTime() {
    return timestamp;
  }

  // TODO: Create a stub for getLatitude()
  // Retrieves the latitude, throws an exception if it's out of valid range
  public double getLatitude() {
    if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
      throw new GPSException("Invalid latitude: " + latitude);
    }
    return latitude;
  }

  // TODO: Create a stub for getLongitude()
  // Retrieves the longitude, throws an exception if it's out of valid range
  public double getLongitude() {
    if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
      throw new GPSException("Invalid longitude: " + longitude);
    }
    return longitude;
  }

  // TODO: Create a stub for getElevation()
  // Retrieves the elevation
  public double getElevation() {
    return elevation;
  }

  // TODO: Create a stub for toString()
  // Generates a string representation of the Points
  public String toString() {
    return String.format("(%.5f, %.5f), %.1f m", longitude, latitude, elevation);
  }

  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
