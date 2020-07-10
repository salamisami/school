/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3.map;

import praktikum.aufgabe3.Constants;

import java.awt.*;

/**
 * A hexagonal cell
 */
public class Cell {
    /**
     * Array with links to neighboring cells
     */
    private Link[] neighbor;

    /**
     * Cell index in a grid
     */
    private Point index;

    /**
     * This flag indicates if the cell is occupied
     */
    private boolean isOccupied;

    /**
     * Smell of the cell
     */
    private float smellDistanceEstimate;

    public Cell(Point index, boolean isOccupied) {
        this.index = new Point(index);
        this.neighbor = new Link[6];
        this.isOccupied = isOccupied;
        this.smellDistanceEstimate = 0.5f;
    }

    /**
     * Get the neighboring cell in the given direction. Returns null if the cell has no neighbor in the given direction.
     */
    public Cell getNeighborCell(Constants.Direction direction) {
        if (neighbor[direction.ordinal()] == null) {
            return null;
        }
        return neighbor[direction.ordinal()].getOther(this);
    }

    /**
     * Set other cell as neighbor (only used at map generation).
     */
    public void setNeigghbor(Constants.Direction direction, Cell neighbor) {
        Link link = new Link(this, neighbor);
        this.neighbor[direction.ordinal()] = link;
        neighbor.setLink(direction.getOpposite(), link);
    }

    /**
     * Set a link between cell, only use internally.
     */
    private void setLink(Constants.Direction direction, Link link) {
        neighbor[direction.ordinal()] = link;
    }

    /**
     * Returns true of the the cell is bounded by a wall in the given direction.
     */
    public boolean isWall(Constants.Direction direction) {
        return neighbor[direction.ordinal()] == null;
    }

    /**
     * Returns the distance to the other cell.
     */
    public float getDistanceTo(Cell other) {
        Point a = Constants.getWorldCoordinates(getIndex());
        Point b = Constants.getWorldCoordinates(other.getIndex());
        return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    @Override
    public String toString() {
        return String.format("(%d/%d/%.2f)", index.x, index.y, smellDistanceEstimate);
    }

    /// GETTER/SETTER

    public boolean isOccupied() {
        return isOccupied;
    }

    public Point getIndex() {
        return index;
    }

    public Link getLink(Constants.Direction richtung) {
        return neighbor[richtung.ordinal()];
    }

    public float getSmell() {
        return smellDistanceEstimate;
    }

    public void setSmell(float smell) {
        this.smellDistanceEstimate = smell;
    }
}
