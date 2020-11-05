package praktikum.aufgabe4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.UnaryOperator;

/**
 * A server in a client-server connection.
 */
public class Server extends SocketWidget {

  private ServerSocket serverSocket;

  public Server(int port, UnaryOperator<String> encode, UnaryOperator<String> decode) {
    super(port, "Server", encode, decode);
    try {
      serverSocket = new ServerSocket(port);
      debugMessage("Running on port " + port);
    } catch (IOException e) {
      debugMessage("Failed to start server on port " + port + "( " + e.getMessage() + ")");
    }
  }

  @Override
  public Socket getSocket() throws IOException {
    return serverSocket.accept();
  }

  @Override
  protected void disconnect() {
    try {
      super.disconnect();
      serverSocket.close();
    } catch (IOException e) {
      debugMessage("Failed to shutdown server");
    }
  }
}
