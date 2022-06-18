package praktikum.aufgabe3.AStar;

import praktikum.aufgabe3.graphs.Graph;

import java.util.Comparator;
import java.util.HashMap;

public interface GetHeuristik {
        Comparator heuristikComparator = null;
        public HashMap getHeuristik(Graph graph);
}
