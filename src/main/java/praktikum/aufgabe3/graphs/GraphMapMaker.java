package praktikum.aufgabe3.graphs;

import praktikum.aufgabe3.Constants;
import praktikum.aufgabe3.map.Cell;
import praktikum.aufgabe3.map.Map;

import java.util.Iterator;
import java.util.Set;

public class GraphMapMaker {
    private Graph<Cell> graphMap;
    private Map myMap;
    public GraphMapMaker (Map myMap, Graph graphMap){
        this.graphMap = graphMap;
        this.myMap = myMap;
    }
    /**
     * Creates a graph equivalent to the map given in the constructor.
     */
    public Graph transferMapGraph (){
        Iterator<Cell> cellIterator = myMap.getCellIterator();
        while (cellIterator.hasNext()){
            Cell currentCell = cellIterator.next();
            if (!currentCell.isOccupied()){//If its not occupied or a wall add it to graph
                graphMap.addElementAsNode(currentCell);
            }
        }
        Iterator<Cell> cellIterator1 = graphMap.getElements();
        while (cellIterator1.hasNext()){
            Cell currentCell = cellIterator1.next();
            for(Constants.Direction direction : Constants.Direction.values()){
                    Cell neighbor = currentCell.getNeighborCell(direction);
                    if (!neighbor.isOccupied()||!currentCell.isWall(direction) || !(neighbor==null)){//Wenn der Nachbar nicht besetzt und keine Wand ist, dann fügen wir ihn unseren Graphen hinzu.
                        graphMap.addEdge(currentCell, neighbor, currentCell.getDistanceTo(neighbor), false);
                    }
                }
        }
        return graphMap;
    }
}
