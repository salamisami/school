package praktikum.aufgabe3.graphs;

import java.util.*;

public class GraphImpl<T> implements Graph<T> {

  private Map<T, HashMap<T, Float>> adjacencyList;

  public GraphImpl() {
    adjacencyList = new HashMap<>();
  }

  @Override
  public void addElementAsNode(T  element) {
    if (element == null) {
      System.out.println("Can't add an object that is null!");
      return;
    }
    try {
      adjacencyList.put((T) element, new HashMap<>());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void addEdge(
    Object from,
    Object to,
    float weight,
    boolean isUnidirectional
  ) {
    if (this.adjacencyList.get(from) == null || this.adjacencyList.get(to)==null){ //Do the objects even exist in our graph?
      System.err.printf("Either from or to is not in the list.");
      return;
    }
      //try {
        this.adjacencyList.get(from).put((T) to, weight); //In case it can't be cast into T
        if (!isUnidirectional){
          this.adjacencyList.get(to).put((T) from, weight);
        }
      //} catch (Exception e) {
      // throw new RuntimeException(e);
      //}

  }

  @Override
  public int getNumNodes() {
    return adjacencyList.size();
  }

  @Override
  public Iterator getElements() {
    return adjacencyList.keySet().iterator();
  }

  @Override
  public Set getNeighbors(T element) {
    HashMap<T, Float> neighbors = adjacencyList.get(element);
    if (neighbors != null) {
      Set<T> neighborSet = new HashSet<T>();
      Iterator<T> it = neighbors.keySet().iterator();
      while (it.hasNext()) {
        neighborSet.add(it.next());
      }
      return neighborSet;
    }
    return null;
  }

  @Override
  public float getEdgeWeight(T from, T to) {
    Set neighbors = this.getNeighbors(from);
    float result;
    if (neighbors.contains(to)) {

      result = this.adjacencyList.get(from).get(to);
    } else {
      System.err.printf(
        "No edge between %s and %to", from.toString(), to.toString()
      );
      return 0;
    }
    return result;
  }
}
