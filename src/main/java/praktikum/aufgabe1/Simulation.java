/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe1;

import praktikum.base.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Simulation of persons health.
 */
public class Simulation extends Observable implements Runnable {

    /**
     * List of persons in the simulation
     */
    protected List<Person> persons;

    /**
     * Width an height of the simulation area
     */
    protected Vector2i simulationArea;

    public Simulation(int width, int height, int numPersons) {
        simulationArea = new Vector2i(width, height);
        persons = new ArrayList<>();
        generatePersons(numPersons);
    }

    /**
     * Generate persons at random position with random health state.
     */
    private void generatePersons(int number) {
        for (int i = 0; i < number; i++) {
            Person.HealthState healthState = Math.random() < Constants.INITIAL_FRACTION_SICK ?
                    Person.HealthState.SICK : Person.HealthState.HEALTHY;
            persons.add(makePerson((int) (Math.random() * simulationArea.getX()),
                    (int) (Math.random() * simulationArea.getY()),
                    healthState));
        }
    }

    /**
     * Generate a person object.
     */
    protected Person makePerson(int x, int y, Person.HealthState healthState) {
        return new Person(x, y, healthState);
    }

    /**
     * Return an iterator for the persons in the scene.
     */
    public Iterator<Person> personIterator() {
        return persons.iterator();
    }

    /**
     * Return the number of persons with the given health state.
     */
    public int getNumPersonsWith(Person.HealthState healthState) {
        int numPersons = 0;
        for (Iterator<Person> personIterator = personIterator(); personIterator.hasNext(); ) {
            Person person = personIterator.next();
            if (person.getHealthState() == healthState) {
                numPersons++;
            }
        }
        return numPersons;
    }

    /**
     * This is the actual simulation loop
     */
    @Override
    public void run() {
        int time = 0;
        // End the simulation if the running thread is interrupted.
        while (!Thread.currentThread().isInterrupted()) {
            time++;
            // wait a little.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // End of simulation
                return;
            }
            // Simulate person behavior
            simulatePersons();
            notifyObservers(time);
        }
    }

    /**
     * Simulate movement and infection of all persons
     */
    protected void simulatePersons() {
        // Move
        for (Iterator<Person> it = personIterator(); it.hasNext(); ) {
            Person person = it.next();
            person.simulate(simulationArea);
        }
    }
}
