package praktikum.aufgabe3.graphs;

import praktikum.aufgabe3.Constants;
import praktikum.aufgabe3.map.Cell;
import praktikum.aufgabe3.map.Map;

import java.util.Iterator;

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
        Iterator cellIterator = myMap.getCellIterator();
        while (cellIterator.hasNext()){
            Cell currentCell = (Cell) cellIterator.next();
            if (!currentCell.isOccupied()){//If its not occupied or a wall add it to graph
                graphMap.addElementAsNode(currentCell);
                for(Constants.Direction direction : Constants.Direction.values()){
                Cell neighbor = currentCell.getNeighborCell(direction);
                if (!neighbor.isOccupied()||!currentCell.isWall(direction)){//Wenn der Nachbar nicht besetzt und keine Wand ist, dann fügen wir ihn unseren Graphen hinzu.
                    graphMap.addEdge(currentCell, neighbor, currentCell.getDistanceTo(neighbor), false);
                    }
                }
                //currentCell.getLink(Const);
            }
        }
        return null;
    }
}
