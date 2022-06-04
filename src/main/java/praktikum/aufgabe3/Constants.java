/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3;

import java.awt.*;

/**
 * Collection of all the magic numbers and helper methods for the application.
 */
public abstract class Constants {

  /**
   * Directions from a cell towards its neighboring cells.
   */
  public enum Direction {
    HOUR_0,
    HOUR_2,
    HOUR_4,
    HOUR_6,
    HOUR_8,
    HOUR_10;

    /**
     * Opposite direction of a given direction.
     */
    public Direction getOpposite() {
      return values()[(ordinal() + 3) % 6];
    }
  }

  /**
   * White border around the map rendering in pixels
   */
  public static final int CANVAS_BORDER = 5;

  /**
   * Window width in pixels
   */
  public static final double WINDOW_WIDTH = 500;

  /**
   * Window height in pixels
   */
  public static final double WINDOW_HEIGHT = 500;

  /**
   * Side length of the cell hexagon during rendering
   */
  public static final int CELL_RENDER_SIDELENGTH = 30;

  /**
   * Hexagon height for a cell during rendering
   */
  public static final double ZELL_RENDER_HEIGHT =
    Math.sqrt(3) / 2.0 * Constants.CELL_RENDER_SIDELENGTH;

  /**
   * Moving speed of the character
   */
  public static final float CHARACTER_SPEED = 0.1f;

  /**
   * Probability of a cell to be occupied (during map generation).
   */
  public static final float CELL_IS_OCCUPIED_PROBABILITY = 0.2f;

  /**
   * Speed of the animation, smaller value = faster
   */
  public static final int SPRITE_ANIMATION_SPEED = 1;

  /**
   * Compute image coordinates from world coordinates
   */
  public static Point getImageCoordinates(Point weltkoordinaten) {
    Point bildKoordinaten = new Point(weltkoordinaten);
    bildKoordinaten.x += Constants.CANVAS_BORDER;
    bildKoordinaten.y += Constants.CANVAS_BORDER;
    return bildKoordinaten;
  }

  /**
   * Compute the world coordinates for a given (cell) index.
   */
  private static Point getWorldCoordinates(int i, int j) {
    int x = (int) (
      i *
      Constants.CELL_RENDER_SIDELENGTH *
      1.5 +
      Constants.CELL_RENDER_SIDELENGTH
    );
    int y = (int) (
      j *
      Constants.ZELL_RENDER_HEIGHT *
      2 +
      ((i % 2 == 1) ? Constants.ZELL_RENDER_HEIGHT : 0) +
      Constants.CELL_RENDER_SIDELENGTH
    );
    return new Point(x, y);
  }

  /**
   * Compute the world coordinates for a given (cell) index.
   */
  public static Point getWorldCoordinates(Point index) {
    return getWorldCoordinates(index.x, index.y);
  }
}
