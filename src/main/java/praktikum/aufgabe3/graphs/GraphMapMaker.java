package praktikum.aufgabe3.graphs;

import praktikum.aufgabe3.map.Cell;
import praktikum.aufgabe3.map.Map;

import java.util.Iterator;

public class GraphMapMaker {
    private Graph<Cell> graphMap;
    private Map myMap;
    void GraphMapMaker (Map myMap, Graph graphMap){
        this.graphMap = graphMap;
        this.myMap = myMap;
    }
    public Graph transferMapGraph (){
        Iterator cellIterator = myMap.getCellIterator();
        while (cellIterator.hasNext()){
            Cell currentCell = (Cell) cellIterator.next();
            if (!currentCell.isOccupied()){
                graphMap.addElementAsNode(currentCell);
                //currentCell.getLink(Const);
            }
        }
        return null;
    }
}
