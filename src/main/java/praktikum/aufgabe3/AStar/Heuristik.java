package praktikum.aufgabe3.AStar;

import praktikum.aufgabe3.graphs.Graph;

import java.util.Comparator;
import java.util.HashMap;

public interface Heuristik<T> {
        Float getHeur(T node);
}
