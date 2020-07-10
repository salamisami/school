/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3.map;

/**
 * Helper structure to represent a connection between two cells.
 */
public class Link {
    /**
     * The two connected cells
     */
    private Cell[] cells = new Cell[2];

    public Link(Cell cell1, Cell cell2) {
        cells[0] = cell1;
        cells[1] = cell2;
    }

    /**
     * Return the opposite cell along the link
     */
    public Cell getOther(Cell cell) {
        if (cells[0] == cell) {
            return cells[1];
        } else {
            return cells[0];
        }
    }
}
