package praktikum.aufgabe3;

import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import praktikum.aufgabe3.AStar.AStar;
import praktikum.aufgabe3.AStar.Heuristik;
import praktikum.aufgabe3.graphs.Graph;
import praktikum.aufgabe3.graphs.GraphImpl;
import praktikum.aufgabe3.graphs.GraphMapMaker;
import praktikum.aufgabe3.map.Cell;
import praktikum.aufgabe3.map.Character;
import praktikum.aufgabe3.map.Map;
import praktikum.aufgabe3.vis.MapRenderer;

import static javafx.scene.input.KeyCode.T;

/**
 * Benutzerinterface (Fenter für das KuerzesterPfadVisualisierung):
 *
 * @author Philipp Jenke
 */
public class Smelloscope extends Application {

  private MapRenderer spielRenderer;

  private Thread gameLoopThread;

  protected Map map;

  @Override
  public void start(Stage buehne) throws Exception {
    BorderPane wurzel = new BorderPane();
    Scene szene = new Scene(
      wurzel,
      Constants.WINDOW_WIDTH,
      Constants.WINDOW_HEIGHT
    );
    buehne.setTitle("Smelloscope");
    buehne.setScene(szene);
    map = new Map(10, 8);
    spielRenderer = new MapRenderer(map);
    wurzel.setCenter(spielRenderer);
    buehne.show();
    gameLoop(map);
  }

  @Override
  public void stop() {
    gameLoopThread.interrupt();
  }

  private void gameLoop(Map map) {
    gameLoopThread =
      new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
          map.getCharacter().move();
          if (map.getCharacter().getMoveDirection() == Character.State.IDLE) {
            map.newTargetCell();
            List<Cell> path = getPath(
              map.getCharacter().getCurrentCell(),
              map.getTargetCell()
            );
            map.getCharacter().setPath(path);
          }
          spielRenderer.redraw();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      });
    gameLoopThread.start();
  }

  /**
   * This is the callback method which delivers the path from start to target using graph shortest path search
   */
  protected List<Cell> getPath(Cell start, Cell target) {
    // Dummy solution: uses neighbor-cell instead of target cell - replace with A*-path
    // Insert your solution here
   /* myAstarAlgo(Graph myGraph, startWerte, targetWerte, LinkedList results);*/
    Graph graph = new GraphImpl<Cell>();
    for (Iterator<Cell> mapCellIterator = map.getCellIterator(); mapCellIterator.hasNext(); ) {
      Cell cell = mapCellIterator.next();
      if (!cell.isOccupied()) {
        graph.addElementAsNode(cell);
      }
    }
    Iterator<Cell> cellIterator=graph.getElements();
    while(cellIterator.hasNext()){
      Cell cell = cellIterator.next();
      for (Constants.Direction direction : Constants.Direction.values()) {
        Cell neighborCell = cell.getNeighborCell(direction);
        if (!(neighborCell == null || neighborCell.isOccupied())) {
          graph.addEdge(cell, neighborCell,cell.getDistanceTo(neighborCell), false);
        }
      }
    }
    /*GraphMapMaker graphMapMaker = new GraphMapMaker(this.map, graph);
    Graph graphMap = graphMapMaker.transferMapGraph();*/
    AStar<Cell> aStar = new AStar<>();
    Heuristik<Cell> heuristik = new Heuristik<Cell>() {
      @Override
      public Float getHeur(Cell node) {
        return node.getSmell();
      }
    };
    return aStar.aStarCalc(graph,start,target,heuristik);
  }

  public static void main(String[] args) {
    Application.launch(args);

  }
}
