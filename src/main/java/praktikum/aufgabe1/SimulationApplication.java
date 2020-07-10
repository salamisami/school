/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import praktikum.base.Observer;

/**
 * Generix Java-FX class. Extend from this class for the simulation
 */
public class SimulationApplication extends Application implements Observer {

    /**
     * The simulation
     */
    protected Simulation simulation;

    /**
     * Data series.
     */
    private XYChart.Series seriesHealthy, seriesSick, seriesImmune;

    /**
     * This thread is used for the simulation.
     */
    private Thread simulationThread;

    public SimulationApplication() {
        // Simulation
        simulation = new Simulation(400, 300, Constants.NUMBER_OF_PERSONS);
        simulation.addObserver(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Layout
        BorderPane root = new BorderPane();
        root.getChildren().add(new SimulationCanvas(simulation));
        root.setRight(initStatistics());
        primaryStage.setScene(new Scene(root, 1000, 330));
        primaryStage.show();

        // Run simulation
        simulationThread = new Thread(simulation);
        simulationThread.start();

        // Exit handling
        primaryStage.setOnCloseRequest(event -> {
            onQuit();
        });
    }

    /**
     * Quit application
     */
    protected void onQuit() {
        simulationThread.interrupt();
    }

    /**
     * Setup chart.
     */
    private LineChart initStatistics() {

        // Chart
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);

        // Data series
        seriesHealthy = new XYChart.Series();
        seriesSick = new XYChart.Series();
        seriesImmune = new XYChart.Series();
        lineChart.getData().addAll(seriesHealthy, seriesSick, seriesImmune);
        setupSeries(seriesHealthy, Color.DARKGREEN);
        setupSeries(seriesSick, Color.RED);
        setupSeries(seriesImmune, Color.ORANGE);

        return lineChart;
    }

    /**
     * Setup a series (color).
     */
    private void setupSeries(XYChart.Series series, Color color) {
        final String colorString = String.format("rgba(%d, %d, %d, 1.0)",
                (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
        final String lineStyle = String.format("-fx-stroke: %s;", colorString);
        series.getNode().lookup(".chart-series-line").setStyle(lineStyle);
    }

    @Override
    public void update(Object payload) {
        int time = (Integer) payload;
        Platform.runLater(() -> {
                    seriesHealthy.getData().add(new XYChart.Data(time, simulation.getNumPersonsWith(Person.HealthState.HEALTHY)));
                    seriesSick.getData().add(new XYChart.Data(time, simulation.getNumPersonsWith(Person.HealthState.SICK)));
                    seriesImmune.getData().add(new XYChart.Data(time, simulation.getNumPersonsWith(Person.HealthState.IMMUNE)));
                }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
