package praktikum.aufgabe3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.aufgabe3.graphs.Graph;
import praktikum.aufgabe3.graphs.GraphImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
public class TestGraph {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    Graph<Integer> graph;
    @BeforeEach
    public void initInteger(){
        Graph<Integer> graph = new GraphImpl<>();
        int arr[] = new int[10];
        for (int i=0; i<arr.length; i++) {
            arr[i]=i;
        }
        for (int arrElement:arr
        ) {
            graph.addElementAsNode(arrElement);
        }
        Iterator<Integer> graphIterator = graph.getElements();;
        /*for (int i=0; i<arr.length; i++) { TODO: Test iterator if it contains all elements.
            assertThat(graphIterator.hasNext());
        }*/
        this.graph = graph;
    }
    @Test
    public void testAddEdgeNonExistant() {
        int a =1;
        int b=999;
        graph.addEdge(a,b, 1.0f, false);
        assertEquals("Either from or to is not in the list.", errContent.toString());

    }
    @Test
    public void testAddEdgeExistant() {
        int a =1;
        int b=2;
        int c=3;
        float edge = 2.0f;
        graph.addElementAsNode(a);
        graph.addElementAsNode(b);
        graph.addElementAsNode(c);
        graph.addEdge(a,b, edge, false);
        graph.addEdge(a,c, edge, false);
        graph.addEdge(b,c, edge, false);
        float weightAB =  graph.getEdgeWeight(a,b);
        float weightAC =  graph.getEdgeWeight(a,c);
        float weightBC =  graph.getEdgeWeight(b,c);
        System.out.printf("Edgeweight: %.2f ", weightAB);
        assertEquals(edge, weightAB);assertEquals(edge, weightAC);assertEquals(edge, weightBC);

    }
}
