/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3.map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import praktikum.aufgabe3.Constants;

/**
 * This is the character which follows a (computed) path towards a target cell.
 */
public class Character {

  /**
   * Possible animation/move/behaviour states
   */
  public enum State {
    IDLE,
    WALK_LEFT,
    WALK_RIGHT,
    WALK_UP,
    WALK_DOWN,
  }

  /**
   * Current path
   */
  private List<Cell> path;

  /**
   * Current cell
   */
  private Cell currentCell;

  /**
   * Parameter on path. Valid values from 0 = start to n = end = |path length|
   */
  private float tau;

  public Character(Cell currentCell) {
    path = new ArrayList<>();
    this.currentCell = currentCell;
  }

  /**
   * Set a new Path
   */
  public void setPath(List<Cell> path) {
    this.path.clear();
    this.path.addAll(path);
  }

  /**
   * Move the character
   */
  public void move() {
    tau += Constants.CHARACTER_SPEED;
    if (tau >= path.size() - 1) {
      path.clear();
      tau = 0;
    } else {
      if (path.size() > 1) {
        // Update current cell
        int a = (int) tau;
        float alpha = tau - a;
        currentCell = alpha < 0.5 ? path.get(a) : path.get(a + 1);
      }
    }
  }

  /**
   * Return the current move direction
   */
  public State getMoveDirection() {
    if (path.size() < 2) {
      return State.IDLE;
    }

    int a = (int) tau;
    Point pA = Constants.getWorldCoordinates(path.get(a).getIndex());
    Point pB = Constants.getWorldCoordinates(path.get(a + 1).getIndex());

    int deltaX = pB.x - pA.x;
    int deltaY = pB.y - pA.y;

    if (Math.abs(deltaX) > Math.abs(deltaY)) {
      if (deltaX > 0) {
        return State.WALK_RIGHT;
      } else {
        return State.WALK_LEFT;
      }
    } else {
      if (deltaY > 0) {
        return State.WALK_DOWN;
      } else {
        return State.WALK_UP;
      }
    }
  }

  /**
   * Return the current position of the character.
   */
  public Point getPos() {
    if (path.size() > 1) {
      int a = (int) tau;
      float alpha = tau - a;
      Point pA = Constants.getWorldCoordinates(path.get(a).getIndex());
      Point pB = Constants.getWorldCoordinates(path.get(a + 1).getIndex());
      return new Point(
        (int) (pA.x * (1 - alpha) + pB.x * alpha),
        (int) (pA.y * (1 - alpha) + pB.y * alpha)
      );
    }
    return Constants.getWorldCoordinates(currentCell.getIndex());
  }

  // GETTER/SETTER

  public Cell getCurrentCell() {
    return currentCell;
  }

  public List<Cell> getPath() {
    return path;
  }
}
