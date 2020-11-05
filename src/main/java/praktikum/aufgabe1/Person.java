/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe1;

/**
 * A person in the simulation that may become sick.
 */
public class Person {
  /**
   * Persons can have one of the following health states.
   */
  public enum HealthState {HEALTHY, SICK, IMMUNE}

  /**
   * Current position.
   */
  protected Vector2i pos;

  /**
   * Health state of the person.
   */
  private HealthState healthState;

  /**
   * Person has been sick for this many time steps.
   */
  private int timeSinceBecomingSick;

  public Person(int x, int y, HealthState healthState) {
    this.pos = new Vector2i(x, y);
    setHealthState(healthState);
    if (healthState == HealthState.SICK) {
      timeSinceBecomingSick = (int) (Math.random() * Constants.SICKNESS_TIME);
    } else {
      timeSinceBecomingSick = 0;
    }
  }

  /**
   * A simulation step for the person.
   */
  public void simulate(Vector2i simulationArea) {
    // Manage sickness
    if (healthState == HealthState.SICK) {
      timeSinceBecomingSick++;
    }
    if (timeSinceBecomingSick > Constants.SICKNESS_TIME) {
      timeSinceBecomingSick = 0;
      healthState = HealthState.IMMUNE;
    }

    // TODO: simulate movement
  }

  /// GETTER/SETTER

  public Vector2i getPos() {
    return pos;
  }

  public HealthState getHealthState() {
    return healthState;
  }

  public void setHealthState(HealthState healthState) {
    if (this.healthState == HealthState.IMMUNE) {
      return;
    }
    if (this.healthState == HealthState.HEALTHY && healthState == HealthState.SICK) {
      timeSinceBecomingSick = 0;
    }
    this.healthState = healthState;
  }
}
