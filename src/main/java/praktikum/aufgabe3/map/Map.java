/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3.map;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import praktikum.aufgabe3.Constants;
import praktikum.base.Observable;

/**
 * Represents the map consisting of cells, the controlled character and a target.
 */
public class Map extends Observable {

  /**
   * Menge aller Zellen
   */
  private Set<Cell> cells = new HashSet<Cell>();

  /**
   * Controlled character in the map
   */
  private Character character;

  /**
   * Target cell for the character
   */
  private Cell targetCell;

  public Map(int width, int height) {
    generateMap(width, height, Constants.CELL_IS_OCCUPIED_PROBABILITY);
    character = new Character(getRandomNonOccupedCell());
    targetCell = getRandomNonOccupedCell();
  }

  /**
   * Add a cell, only used during generation.
   */
  public void addCell(Cell zelle) {
    cells.add(zelle);
  }

  /**
   * Return a random unoccupied cell
   */
  public Cell getRandomNonOccupedCell() {
    Cell randomCell = null;
    while (randomCell == null) {
      randomCell =
        new ArrayList<>(cells).get((int) (Math.random() * cells.size()));
      if (randomCell.isOccupied()) {
        randomCell = null;
      }
    }
    return randomCell;
  }

  /**
   * Generate a random map with the given dimensions.
   *
   * @param isOccupiedProbability The occupied cells are sampled with this probability
   */
  public void generateMap(int width, int height, float isOccupiedProbability) {
    cells.clear();
    Cell[][] cellCache = new Cell[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Cell zelle = new Cell(
          new Point(i, j),
          Math.random() < isOccupiedProbability
        );
        addCell(zelle);
        cellCache[i][j] = zelle;
      }
    }

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (i < width - 1) {
          if (i % 2 == 0) {
            if (j > 0) {
              cellCache[i][j].setNeigghbor(
                  Constants.Direction.HOUR_2,
                  cellCache[i + 1][j - 1]
                );
            }
            cellCache[i][j].setNeigghbor(
                Constants.Direction.HOUR_4,
                cellCache[i + 1][j]
              );
          } else {
            cellCache[i][j].setNeigghbor(
                Constants.Direction.HOUR_2,
                cellCache[i + 1][j]
              );
            if (j < height - 1) {
              cellCache[i][j].setNeigghbor(
                  Constants.Direction.HOUR_4,
                  cellCache[i + 1][j + 1]
                );
            }
          }
        }
        if (j < height - 1) {
          cellCache[i][j].setNeigghbor(
              Constants.Direction.HOUR_6,
              cellCache[i][j + 1]
            );
        }
      }
    }
  }

  /**
   * Select a new target and update the smells of all cells
   */
  public void newTargetCell() {
    targetCell = getRandomNonOccupedCell();

    // Update smell for all cells
    float maxDist = 0;
    for (Iterator<Cell> it = getCellIterator(); it.hasNext();) {
      Cell cell = it.next();
      float dist = cell.getDistanceTo(targetCell);
      maxDist = Math.max(maxDist, dist);
      cell.setSmell(dist);
    }
  }

  // GETTER/SETTER

  public Character getCharacter() {
    return character;
  }

  public Cell getTargetCell() {
    return targetCell;
  }

  /**
   * Returns an iterator over all cells.
   */
  public Iterator<Cell> getCellIterator() {
    return cells.iterator();
  }
}
