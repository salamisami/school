/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe1;

/**
 * A 2D integer vector.
 */
public class Vector2i {
  /**
   * x coordinate.
   */
  private int x;

  /**
   * y coordinate.
   */
  private int y;

  public Vector2i(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Add other vector, return result (self).
   */
  public Vector2i add(Vector2i p) {
    this.x += p.x;
    this.y += p.y;
    return this;
  }

  /**
   * Set the coordinates.
   */
  public void set(Vector2i p) {
    this.x = p.x;
    this.y = p.y;
  }

  /**
   * Euklidian distance between this and other vector.
   */
  public float distanceTo(Vector2i p) {
    return (float) (Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2)));
  }

  /// Getter/Setter

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
}
