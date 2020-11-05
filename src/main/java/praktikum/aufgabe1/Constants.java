/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe1;

/**
 * Constants for the magic numbers in the simulation.
 */
public class Constants {

  /**
   * Move distance in each time step.
   */
  public static final int MOVE_DISTANCE = 5;

  /**
   * Other (healthy) people get infected within this radius.
   */
  public static final float INFECTION_RADIUS = 2.5f;

  /***
   * A person is sick for that many time steps.
   */
  public static final int SICKNESS_TIME = 100;

  /**
   * Initial percentage of sick persons.
   */
  public static final double INITIAL_FRACTION_SICK = 0.1;

  /**
   * Number of persons in the simulation.
   */
  public static final int NUMBER_OF_PERSONS = 200;

  /**
   * Radius of the circle for the persons.
   */
  public static final int RENDER_PERSON_CIRCLE_RADIUS = 6;
}
