package praktikum.aufgabe3.AStar;

import praktikum.aufgabe3.graphs.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar<NodeWrapper> {
    PriorityQueue <NodeWrapper> closedList;
    PriorityQueue <NodeWrapper> openList;
    NodeWrapper target;
    NodeWrapper start;
    HashMap<NodeWrapper, Float> heuristik;
    GetHeuristik heurMaker;
    Graph graph;


    public AStar(NodeWrapper start, NodeWrapper target, Graph graph, Comparator<NodeWrapper> comp){
        closedList=new PriorityQueue<NodeWrapper>(comp);
        openList=new PriorityQueue<NodeWrapper>(comp);
        openList.add(start);
        this.target=target;
        this.start = start;
        heurMaker = new GetHeuristikImplMap();
        heuristik = heurMaker.getHeuristik(graph);
        this.graph = graph;

    }
    public void updateHeuristik(Graph graph){
        heuristik = heurMaker.getHeuristik(graph);
    }
    public void setTargetStart(NodeWrapper start, NodeWrapper target){
        this.start = start;
        this.target = target;
    }
    public NodeWrapper aStarCalc(){
        while(!openList.isEmpty()){
            NodeWrapper n =(NodeWrapper) openList.peek();

            if(n==target){
                return n;
            }
            graph.getNeighbors(n.getNode())
        }
    }

}




