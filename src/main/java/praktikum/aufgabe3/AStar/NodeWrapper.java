package praktikum.aufgabe3.AStar;

import java.util.HashMap;

public class NodeWrapper<T> {


        // Overriding compare()method of Comparator
        // for descending order of cgpa
        float calculatedCost;
        T node;
        public NodeWrapper(T node, float edge, HashMap<T, Float > heuristik){
            this.node = node;
            calculatedCost = edge + heuristik.get(node);
        }
        public float getCalculatedCost(){
            return  calculatedCost;
        }
        public Object getNode(){
            return node;
        }

}
