/**
 * JavaFX's application to plot elevations of a GPS track, for the
 * Advanced task of COMP1721 Coursework 1.
 *
 * @author Lirong Guo
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.IOException;

public class PlotApplication extends Application {
  // If attempting the Advanced task, implement your plotting code here.
  // You will need to modify this class definition so that it extends
  // javafx.application.Application
  @Override
  public void start(Stage stage) {
    NumberAxis xAxis = new NumberAxis();
    xAxis.setLabel("Distance (m)");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Elevation (m)");

    LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setTitle("Elevation Plot");

    // Disable symbols on data points
    lineChart.setCreateSymbols(false);

    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    series.setName("walk.csv");

    String filename = getParameters().getRaw().get(0);

    Track track = new Track();
    try {
      track.readFile(filename);
    }
    catch (IOException e) {
      // Handle IOException if file reading fails.
      System.err.println("Failed to read file: " + e.getMessage());
      System.exit(1);
    }

    double totalDistance = 0.0;
    Point lastPoint = null;

    for (Point point : track.getPoints()) {
      if (lastPoint != null) {
        double distance = Point.greatCircleDistance(lastPoint, point);
        totalDistance += distance;
      }
      series.getData().add(new XYChart.Data<>(totalDistance, point.getElevation()));

      lastPoint = point;
    }

    lineChart.getData().add(series);

    Scene scene = new Scene(lineChart, 800, 600);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    // If attempting the Advanced task, uncomment the line below
    launch(args);
  }
}
