/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3.vis;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import praktikum.aufgabe3.Constants;
import praktikum.aufgabe3.map.Cell;
import praktikum.aufgabe3.map.Character;
import praktikum.aufgabe3.map.Map;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Rendering of a map
 */
public class MapRenderer extends Canvas {

  /**
   * Renders this map
   */
  private Map map;

  /**
   * Animated sprite objects in the map
   */
  private AnimatedSprite characterSprite;
  private AnimatedSprite targetSprite;
  private AnimatedSprite treeSprite;

  public MapRenderer(Map map) {
    super(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    this.map = map;

    characterSprite = new AnimatedSprite(100, 100);
    characterSprite.addAnimationState(Character.State.IDLE,
            "src/main/resources/sprites/idle.png");
    characterSprite.addAnimationState(Character.State.WALK_LEFT,
            "src/main/resources/sprites/walk_left.png");
    characterSprite.addAnimationState(Character.State.WALK_RIGHT,
            "src/main/resources/sprites/walk_right.png");
    characterSprite.addAnimationState(Character.State.WALK_UP,
            "src/main/resources/sprites/walk_up.png");
    characterSprite.addAnimationState(Character.State.WALK_DOWN,
            "src/main/resources/sprites/walk_down.png");

    targetSprite = new AnimatedSprite(128, 128);
    targetSprite.addAnimationState(Character.State.IDLE, "src/main/resources/sprites/target.png");

    treeSprite = new AnimatedSprite(128, 128);
    treeSprite.addAnimationState(Character.State.IDLE, "src/main/resources/sprites/tree.png");
  }

  /**
   * Redraw everything
   */
  public void redraw() {
    GraphicsContext gc = getGraphicsContext2D();
    gc.clearRect(0, 0, getWidth(), getHeight());
    gc.setFill(Color.WHITE);
    drawAllCells(gc);
    drawPath(gc);
    drawTarget(gc);
    drawCharacter(gc);
  }

  /**
   * Draws the path of the character
   */
  private void drawPath(GraphicsContext gc) {
    drawPath(map.getCharacter().getPath(), new Color(0.3f, 0.3f, 0.3f, 1));
  }

  /**
   * Draws the target icon.
   */
  private void drawTarget(GraphicsContext gc) {
    targetSprite.draw(gc, Constants.getWorldCoordinates(map.getTargetCell().getIndex()),
            Character.State.IDLE);
  }

  /**
   * Draws the character animation.
   */
  private void drawCharacter(GraphicsContext gc) {
    characterSprite.draw(gc, map.getCharacter().getPos(), map.getCharacter().getMoveDirection());
  }

  /**
   * Draws all cells in the map
   */
  private void drawAllCells(GraphicsContext gc) {

    float maxSmell = 0;
    for (Iterator<Cell> it = map.getCellIterator(); it.hasNext(); ) {
      Cell cell = it.next();
      maxSmell = Math.max(maxSmell, cell.getSmell());
    }

    for (Iterator<Cell> it = map.getCellIterator(); it.hasNext(); ) {
      Cell cell = it.next();
      drawCell(gc, cell, maxSmell);
    }
  }

  /**
   * Draws the path
   */
  private void drawPath(List<Cell> path, Color color) {
    GraphicsContext gc = getGraphicsContext2D();

    double[] xPoints = new double[path.size()];
    double[] yPoints = new double[path.size()];
    gc.setFill(color);
    gc.setStroke(color);
    for (int i = 0; i < path.size(); i++) {
      Cell cell1 = path.get(i);
      Point center = Constants.getImageCoordinates(Constants
              .getWorldCoordinates(cell1.getIndex()));
      xPoints[i] = center.x;
      yPoints[i] = center.y;
      double pointRadius = Constants.ZELL_RENDER_HEIGHT / 4;
      gc.fillArc(center.x - pointRadius, center.y - pointRadius,
              pointRadius * 2, pointRadius * 2, 0, 360, ArcType.ROUND);
    }
    gc.strokePolyline(xPoints, yPoints, path.size());
  }

  /**
   * Draws a single cell
   */
  private void drawCell(GraphicsContext gc, Cell cell, float maxSmell, Point center) {
    if (cell == null) {
      return;
    }
    Point[] point = new Point[6];
    double[] xPoints = new double[6];
    double[] yPoints = new double[6];
    point[0] = Constants.getImageCoordinates(
            new Point((int) (center.x - 0.5 * Constants.CELL_RENDER_SIDELENGTH),
                    (int) (center.y - Constants.ZELL_RENDER_HEIGHT)));
    point[1] = Constants.getImageCoordinates(
            new Point((int) (center.x + 0.5 * Constants.CELL_RENDER_SIDELENGTH),
                    (int) (center.y - Constants.ZELL_RENDER_HEIGHT)));
    point[2] = Constants.getImageCoordinates(
            new Point((int) (center.x + Constants.CELL_RENDER_SIDELENGTH),
                    (int) (center.y)));
    point[3] = Constants.getImageCoordinates(
            new Point((int) (center.x + 0.5 * Constants.CELL_RENDER_SIDELENGTH),
                    (int) (center.y + Constants.ZELL_RENDER_HEIGHT)));
    point[4] = Constants.getImageCoordinates(
            new Point((int) (center.x - 0.5 * Constants.CELL_RENDER_SIDELENGTH),
                    (int) (center.y + Constants.ZELL_RENDER_HEIGHT)));
    point[5] = Constants.getImageCoordinates(
            new Point((int) (center.x - Constants.CELL_RENDER_SIDELENGTH),
                    (int) (center.y)));
    for (int i = 0; i < 6; i++) {
      xPoints[i] = point[i].x;
      yPoints[i] = point[i].y;
    }

    // cell area
    Color cellSmellColor =
            new Color(0.75 - cell.getSmell() / maxSmell * 0.5, 0.75 - cell.getSmell() / maxSmell * 0.5, 0.2, 1);
    gc.beginPath();
    gc.setFill(cellSmellColor);
    gc.fillPolygon(xPoints, yPoints, 6);

    // cell border
    for (int i = 0; i < 6; i++) {
      gc.beginPath();
      if (cell.isWall(Constants.Direction.values()[i])) {
        // wall
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
      } else {
        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(1);
      }
      gc.moveTo(point[i].x, point[i].y);
      gc.lineTo(point[(i + 1) % 6].x, point[(i + 1) % 6].y);
      gc.stroke();
    }
  }

  /**
   * Draw a single cell
   */
  private void drawCell(GraphicsContext gc, Cell zelle, float maxSmell) {
    if (zelle == null) {
      return;
    }
    Point mittelpunkt = Constants
            .getWorldCoordinates(zelle.getIndex());
    drawCell(gc, zelle, maxSmell, mittelpunkt);

    if (zelle.isOccupied()) {
      treeSprite.draw(gc, Constants.getWorldCoordinates(zelle.getIndex()),
              Character.State.IDLE);
    }
  }
}
