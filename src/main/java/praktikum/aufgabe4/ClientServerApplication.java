package praktikum.aufgabe4;

import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

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
    RSA clientK = new RSA();
    clientK.init();
    RSA serverK = new RSA();
    serverK.init();
    // These operations need to be replaced by the RSA encoding/decoding
    UnaryOperator<String> clientEncode = x -> encode(clientK.publicK, x);
    UnaryOperator<String> clientDecode = x -> decode(clientK, x);
    UnaryOperator<String> serverEncode = x -> encode(serverK.publicK, x);
    UnaryOperator<String> serverDecode = x -> decode(serverK, x);

    client = new Client(port, hostname, clientEncode, clientDecode);
    server = new Server(port, serverEncode, serverDecode);
  }
  public String encode (Pair<Integer, Integer> publicKey, String toEncode){
    StringBuilder result = new StringBuilder();
    RSA rsa = new RSA();
    rsa.init();
    for (int i=0; i<toEncode.length();i++){
      result.append(rsa.encode(toEncode.charAt(i),publicKey));
      result.append(" ");
    }
    return result.toString();
  }
  public String decode (RSA rsa, String toDecode){
    StringBuilder result = new StringBuilder();
    for (int i=0; i<toDecode.length(); i++){
      if (toDecode.charAt(i) != ' '){//32 ist der Wert für ein Leerzeichen
        char c = toDecode.charAt(i);
        result.append(rsa.decode(c));
      }
    }
    return result.toString();
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
