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
   * Current direction the person is moving towards.
   */
  protected int angle;
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
    //Direction of the person initialized as a random variable-
    this.angle = (int)(Math.random()*360);
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

  /**
   * A simulation step for the movement of the person.
   * This slightly changes the direction the person is moving for each step.
   * If the person reaches an edge of the room, he does a 180° turn and moves toward that direction.
   * @param simulationArea
   * @return void
   */
  private void movement (Vector2i simulationArea){
    int currentXPos = this.pos.getX();
    int currentYPos = this.pos.getY();
    if (currentYPos >= simulationArea.getY() ||currentXPos >= simulationArea.getX() ||
            currentYPos <= 0 ||currentXPos <= 0 ){
      this.angle += 180;
      if (angle > 360){
        angle = angle % 360;
      }
    }
  }

  /**
   * A helper function for converting angles into vectors.
   * @return Vector2i
   * @throws Exception
   */
  private Vector2i angleToDirVector () throws Exception {
    if (this.angle > 360 || this.angle < 0){
      throw new Exception("Ungültiger Winkel");
    }
    Vector2i dirVector = new Vector2i((int) Math.cos(this.angle), (int) Math.sin(this.angle));
    return dirVector;
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
