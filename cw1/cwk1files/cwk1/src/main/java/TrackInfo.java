import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Lirong Guo
 */
public class TrackInfo {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Error: No filename provided.");
      System.exit(0);
    }

    String filename = args[0];
    Track track = new Track();

    try {
      track.readFile(filename);

      System.out.println(track.size() + " points in track");
      System.out.println("Lowest point is " + formatPoint(track.lowestPoint()));
      System.out.println("Highest point is " + formatPoint(track.highestPoint()));
      System.out.printf("Total distance = %.3f km%n", track.totalDistance() / 1000);
      System.out.printf("Average speed = %.3f m/s%n", track.averageSpeed());
    } catch (IOException e) {
      System.out.println("Error reading from file: " + e.getMessage());
      System.exit(1);
    } catch (RuntimeException e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(1);
    }
  }

  private static String formatPoint(Point point) {
    return String.format("(%.5f, %.5f), %.1f m", point.getLongitude(),
            point.getLatitude(), point.getElevation());
  }
}
