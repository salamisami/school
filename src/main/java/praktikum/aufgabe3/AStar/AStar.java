package praktikum.aufgabe3.AStar;

import praktikum.aufgabe3.graphs.Graph;

import java.util.*;

public class AStar<T> {
    private PriorityQueue <T> openList;
    private Map<T, AstarNode<T>> astarNodeMap = new HashMap<>();
    private T start;
    private Graph<T> graph;

    private void update (Graph<T> graph, T start){
        Iterator<T> graphElements = graph.getElements();
        while (graphElements.hasNext()) {
            astarNodeMap.put(graphElements.next(), new AstarNode<>());
        }
        openList=new PriorityQueue<>((i1, i2) -> Float.compare(astarNodeMap.get(i1).ent,astarNodeMap.get(i2).ent));
        astarNodeMap.get(start).ent=0.0f;
        astarNodeMap.get(start).cost=0.0f;
        openList.add(start);
        this.start = start;
        this.graph = graph;


    }
    public List<T> aStarCalc(Graph<T> graph, T start, T target, Heuristik <T> heuristik){
        update(graph, start); //Updaten des Graphen und Startknotens
        List <T> path = new ArrayList<>();//init Result
        T node = start;//Init mit Startknoten
        while(openList.isEmpty() || (node!=null && target !=null &&!node.equals(target))){ //Wenn die Queue nicht leer ist und wir nicht am Ziel angekommen sind, !=null weil ich "random" ish null pointer exception bekommen hatte
            node = openList.poll();//Neuen Knoten schnappen
            astarNodeMap.get(node).visited=true; //Knoten markieren
            Set <T> neighbors = graph.getNeighbors(node);
            for (T neighbor: neighbors){
                if (!astarNodeMap.get(neighbor).visited){ // Wenn dieser Nachbar noch nicht besucht wurde schauen wir ob es sich "lohnt" den Nachbarn zu erwägen
                    AstarNode<T> parentNode = astarNodeMap.get(node);
                    AstarNode<T> currentNode = astarNodeMap.get(neighbor);
                    float cost = parentNode.cost + graph.getEdgeWeight(node, neighbor); //Kosten berechnen
                    if (cost < currentNode.cost) { //Wenn die jetzigen kosten höher sind updaten wir
                        openList.remove(neighbor);
                        currentNode.parent=node;
                        currentNode.cost=cost;
                        currentNode.ent=(cost + heuristik.getHeur(neighbor));
                        openList.add(neighbor);
                    }
                }
            }
        }
        while (astarNodeMap.get(node).parent !=null){ //Liste zusammenbasteln
            path.add(node);
            node = astarNodeMap.get(node).parent;
        }
        path.add(start); // Den Start einfügen
        Collections.reverse(path);
        return path;
    }

}




