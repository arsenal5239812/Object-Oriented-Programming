/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author Lirong Guo
 */

import java.io.IOException;

public class ConvertToKML {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Error: Insufficient arguments provided.");
      System.exit(0);
    }

    String csvFilename = args[0];
    String kmlFilename = args[1];
    Track track = new Track();

    try {
      track.readFile(csvFilename);
      track.writeKML(kmlFilename);
      System.out.println("KML file created successfully.");
    } catch (IOException e) {
      System.out.println("Error reading from or writing to file: " + e.getMessage());
      System.exit(1);
    } catch (RuntimeException e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(1);
    }
  }
}
