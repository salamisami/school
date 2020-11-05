/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.base;

/**
 * An observer observes an observable object and gets informed about changes
 */
public interface Observer {

  /**
   * This method is called when the observable informs its observers about some change.
   *
   * @param payload Additional (optional) information about the change.
   */
  void update(Object payload);
}
