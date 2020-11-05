/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.base;

import java.util.HashSet;
import java.util.Set;

/**
 * An observer can be observed by an arbitrary number of observers.
 */
public abstract class Observable {

  /**
   * Set of observers.
   */
  private Set<Observer> observer;

  public Observable() {
    observer = new HashSet<>();
  }

  /**
   * Register additional observer.
   */
  public void addObserver(Observer observer) {
    this.observer.add(observer);
  }

  /**
   * Inform all registered observers that somethings has changed. Provide information about the change.
   */
  public void notifyObservers(Object payload) {
    observer.forEach(b -> b.update(payload));
  }

  /**
   * Inform all registered observers that somethings has changed.
   */
  public void notifyObservers() {
    notifyObservers(null);
  }

}
