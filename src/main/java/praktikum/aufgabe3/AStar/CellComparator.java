package praktikum.aufgabe3.AStar;

import java.util.Comparator;

class CellComparator implements Comparator<NodeWrapper> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(NodeWrapper s1, NodeWrapper s2) {
        if (s1.getCalculatedCost() < s2.getCalculatedCost())
            return 1;
        else if (s1.getCalculatedCost() > s2.getCalculatedCost())
            return -1;
        return 0;
    }
}