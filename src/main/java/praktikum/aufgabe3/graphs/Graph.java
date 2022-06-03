package praktikum.aufgabe3.graphs;

import java.util.Iterator;
import java.util.Set;

/**
 * This is the minimal interface for the graph implementation.
 */
public interface Graph<T> {
  /**
   * Insert the given element as a new node into the graph
   */
  void addElementAsNode(T element);

  /**
   * Insert a weighted edge to the graph connecting the elements from and to.
   *
   * @param from             First element in the connection.
   * @param to               Second element int the connection.
   * @param weight           Weight of the edge.
   * @param isUnidirectional Indicates if the edge is unidirectional (true) or bidirectional (false).
   */
  void addEdge(T from, T to, float weight, boolean isUnidirectional);

  /**
   * Return the number of nodes in the graph.
   */
  int getNumNodes();

  /**
   * Return an iterator of all elements in the groph (elements in graph nodes).
   */
  Iterator<T> getElements();

  /**
   * Returns a set with all neighbors of the given element in the graph.
   */
  Set<T> getNeighbors(T element);

  /**
   * Returns the edge weight between from and to - only valid if edge exists.
   */
  float getEdgeWeight(T from, T to);
}
