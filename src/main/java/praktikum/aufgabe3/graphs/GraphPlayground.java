package praktikum.aufgabe3.graphs;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphPlayground {
        public static void main(String[] args) {
                Graph<Integer> graph = new GraphImpl<>();
                int arr[] = new int[10];
                for (int i = 0; i < arr.length; i++) {
                        arr[i] = i;
                }
                for (int arrElement : arr
                ) {
                        graph.addElementAsNode(arrElement);
                }
                Iterator<Integer> graphIterator = graph.getElements();
                ;
                /*for (int i=0; i<arr.length; i++) { TODO: Test iterator if it contains all elements.
                    assertThat(graphIterator.hasNext());
                }*/


                //debug add edge, turns out I didnt update my returns.
                float edge = 1.0f;
                int int1 = arr[1];
                int int2= arr[0];
                for (int i=0; i<arr.length; i++){
                graph.addEdge(int2, i, edge, false);}
                if (edge == graph.getEdgeWeight(int2,int1)){
                        System.out.println("Success!");
                }
        }
}