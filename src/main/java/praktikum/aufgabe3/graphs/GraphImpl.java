package praktikum.aufgabe3.graphs;

import java.util.*;

public class GraphImpl<T> implements Graph{
    private Map<T, List<T>> adjacencyList;
    public void Graph (){
        adjacencyList = new HashMap<>();
    }
    @Override
    public void addElementAsNode(Object element) {
        adjacencyList.put((T) element, new ArrayList<>());
    }

    @Override
    public void addEdge(Object from, Object to, float weight, boolean isUnidirectional) {


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
    public Set getNeighbors(Object element) {
        List<T> neighbors =adjacencyList.get(element) ;
        if (neighbors != null){
            Set<T> neighborSet = new HashSet<T>();
            Iterator<T> it = neighbors.iterator();
            while(it.hasNext()){
                neighborSet.add(it.next());
            }
            return neighborSet;
        }
        return null;
    }
    @Override
    public float getEdgeWeight(Object from, Object to) {
        return 0;
    }
}
