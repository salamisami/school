package praktikum.aufgabe4;

import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX-Application to run a client and a server which exchange messages.
 */
public class ClientServerApplication extends Application {

  /**
   * Reference to the client.
   */
  protected Client client;

  /**
   * Reference to the server
   */
  protected Server server;

  public ClientServerApplication() {
    int port = 1234;
    String hostname = "localhost";

    // These operations need to be replaced by the RSA encoding/decoding
    UnaryOperator<String> clientEncode = x -> x;
    UnaryOperator<String> clientDecode = x -> x;
    UnaryOperator<String> serverEncode = x -> x;
    UnaryOperator<String> serverDecode = x -> x;

    client = new Client(port, hostname, clientEncode, clientDecode);
    server = new Server(port, serverEncode, serverDecode);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setScene(new Scene(client.makeSceneGraph(), 400, 300));
    primaryStage.setTitle("Client");
    primaryStage.setX(550);
    primaryStage.setY(100);
    primaryStage.show();

    Stage serverStage = new Stage();
    serverStage.setScene(new Scene(server.makeSceneGraph(), 400, 300));
    serverStage.setTitle("Server");
    serverStage.show();
    serverStage.setX(100);
    serverStage.setY(100);

    primaryStage.setOnCloseRequest(event -> {
      System.out.println("Automatically disconnected client and server.");
      client.disconnect();
      server.disconnect();
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
}
