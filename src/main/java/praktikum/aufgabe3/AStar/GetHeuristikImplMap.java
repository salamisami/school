package praktikum.aufgabe3.AStar;

import praktikum.aufgabe3.AStar.GetHeuristik;
import praktikum.aufgabe3.graphs.Graph;
import praktikum.aufgabe3.map.Cell;

import java.util.HashMap;
import java.util.Iterator;

public class GetHeuristikImplMap implements GetHeuristik {

    @Override
    public HashMap getHeuristik(Graph graph) {
        HashMap<Cell, Float> result = new HashMap<>();
        Iterator graphIterator;
        graphIterator = graph.getElements();
        while(graphIterator.hasNext()){
            Cell currentCell = ((Cell)(graphIterator.next()));
            float heur = currentCell.getSmell();
            result.put(currentCell,heur);

        }
        return result;
    }
}
