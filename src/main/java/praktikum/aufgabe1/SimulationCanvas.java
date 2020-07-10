/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe1;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.*;
import praktikum.base.Observer;

import java.util.Iterator;

/**
 * This cavas displays the simulation content.
 */
public class SimulationCanvas extends Canvas implements Observer {

    /**
     * Reference to the simulation.
     */
    private Simulation simulation;

    public SimulationCanvas(Simulation simulation) {
        super(600, 400);
        this.simulation = simulation;
        simulation.addObserver(this);
        redraw();
    }

    /**
     * Render the content
     */
    public void redraw() {
        Platform.runLater(() -> {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());
            drawPersons(gc);
        });
    }

    /**
     * Render all persons an their respective states.
     */
    private void drawPersons(GraphicsContext gc) {
        for (Iterator<Person> it = simulation.personIterator(); it.hasNext(); ) {
            Person person = it.next();
            drawPerson(gc, person);
        }
    }

    /**
     * Draw a person and its state.
     */
    private void drawPerson(GraphicsContext gc, Person person) {
        gc.setFill(getColor(person));
        gc.fillArc(person.getPos().getX() - Constants.RENDER_PERSON_CIRCLE_RADIUS / 2,
                person.getPos().getY() - Constants.RENDER_PERSON_CIRCLE_RADIUS / 2,
                Constants.RENDER_PERSON_CIRCLE_RADIUS,
                Constants.RENDER_PERSON_CIRCLE_RADIUS,
                0, 360, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(person.getPos().getX() - Constants.RENDER_PERSON_CIRCLE_RADIUS / 2,
                person.getPos().getY() - Constants.RENDER_PERSON_CIRCLE_RADIUS / 2,
                Constants.RENDER_PERSON_CIRCLE_RADIUS,
                Constants.RENDER_PERSON_CIRCLE_RADIUS,
                0, 360, ArcType.OPEN);
    }

    /**
     * Return the color based on the health state.
     */
    private Paint getColor(Person person) {
        switch (person.getHealthState()) {
            case HEALTHY:
                return Color.DARKGREEN;
            case SICK:
                return Color.RED;
            default:
                return Color.ORANGE;
        }
    }

    @Override
    public void update(Object payload) {
        redraw();
    }
}
