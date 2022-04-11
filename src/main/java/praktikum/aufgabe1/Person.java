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
    try {
      movement(simulationArea);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * A simulation step for the movement of the person.
   * This slightly changes the direction the person is moving for each step.
   * If the person reaches an edge of the room, he does a 180° turn and moves toward that direction.
   * @param simulationArea The room as a Vector2i where the person is currently in
   */
  private void movement (Vector2i simulationArea){
    int currentXPos = this.pos.getX();
    int currentYPos = this.pos.getY();
    if (currentYPos >= simulationArea.getY() || currentXPos >= simulationArea.getX() ||
            currentYPos <= 0 ||currentXPos <= 0 ){
      this.angle += 180;
      //Just to be sure my angle never reaches max int.
      if (this.angle > 360){
        this.angle = this.angle % 360;
      }
    }
    this.angle += (int) (Math.random() * 90);
    try {
      Vector2i dirVector = angleToDirVector();
      dirVector.setX((dirVector.getX() * Constants.MOVE_DISTANCE) / Constants.COMMA_FACTOR);
      dirVector.setY((dirVector.getY() * Constants.MOVE_DISTANCE) / Constants.COMMA_FACTOR);
      this.pos.setX(currentXPos+dirVector.getX());
      this.pos.setY(currentYPos+dirVector.getY());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.pos.getX() > simulationArea.getX()){
      this.pos.setX(simulationArea.getX());
    }
    else if(this.pos.getX() < 0){
      this.pos.setX(0);
    }
    if (this.pos.getY() > simulationArea.getY()){
      this.pos.setY(simulationArea.getY());
    }
    else if (this.pos.getY() < 0){
      this.pos.setY(0);
    }
  }

  /**
   * A helper function for converting angles into vectors.
   * Throws an exception if the current angle is negative or greater than 360.
   * @return Vector2i
   * @throws Exception When angle is greater than 360 or negative
   */
  private Vector2i angleToDirVector () throws Exception {
    if (this.angle < 0){
      throw new Exception("Ungültiger Winkel");
    }
    return new Vector2i((int) (Constants.COMMA_FACTOR * Math.cos(this.angle)),
            (int) (Constants.COMMA_FACTOR * Math.sin(this.angle)));
    }

  /**
   * If the person is infected and the person
   */
  public void infect (Person neighbour){
    if (this.healthState == HealthState.SICK && neighbour.healthState == HealthState.HEALTHY){
      neighbour.healthState = HealthState.SICK;
    }
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
