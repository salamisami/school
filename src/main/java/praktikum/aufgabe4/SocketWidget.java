package praktikum.aufgabe4;

import java.io.*;
import java.net.Socket;
import java.util.function.UnaryOperator;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This abstract class encapsulates a socket and automatically generates a JavaFX
 * user interface in order to communicate over the socket.
 */
public abstract class SocketWidget {

  /**
   * Connection port
   */
  protected final int port;

  /**
   * Connection socket.
   */
  protected Socket socket;

  /**
   * Data is received via this reader from the socket.
   */
  protected BufferedReader br;

  /**
   * Data is sent via this writer via the socket.
   */
  protected BufferedWriter bw;

  /**
   * Name of the socket widget (usually client or server)
   */
  protected final String name;

  /**
   * Logging is done in this text area.
   */
  private TextArea logTextArea;

  /**
   * Assembled log text.
   */
  private String logText;

  /**
   * This is the encode operation
   */
  private UnaryOperator<String> encode;

  /**
   * This is the decode operation
   */
  private UnaryOperator<String> decode;

  /**
   * This label shows the connections state.
   */
  private Label connectedStateLabel;

  /**
   * These image views hold the connected/disconnected icons
   */
  private ImageView connectedImageView, disconnectedImageView;

  /**
   * Constructor
   *
   * @param port   Port of the connection
   * @param name   Name of the socket entity (client or server)
   * @param encode Encoding operation before sending
   * @param decode Decoding operation after receiving
   */
  public SocketWidget(
    int port,
    String name,
    UnaryOperator<String> encode,
    UnaryOperator<String> decode
  ) {
    this.port = port;
    this.name = name;
    this.logText = "";
    this.encode = encode;
    this.decode = decode;

    // Load icon images
    InputStream inputConnected = null;
    InputStream inputDisconnected = null;
    String disconnectedIconFilename =
      "src/main/resources/icons/disconnected.png";
    String connectedIconFilename = "src/main/resources/icons/connected.png";
    try {
      inputDisconnected =
        new FileInputStream(new File(disconnectedIconFilename));
      inputConnected = new FileInputStream(new File(connectedIconFilename));
    } catch (FileNotFoundException e) {
      System.out.println("Could not find icon file.");
    }
    connectedImageView = new ImageView(new Image(inputConnected));
    disconnectedImageView = new ImageView(new Image(inputDisconnected));
  }

  /**
   * Generator for the socket.
   */
  public abstract Socket getSocket() throws IOException;

  /**
   * Establish connection via socket
   */
  public void connect() {
    Thread socketThread = new Thread(() -> {
      try {
        this.socket = getSocket();

        debugMessage("Connected with socket on port " + port);

        OutputStream socketoutstr = socket.getOutputStream();
        OutputStreamWriter osr = new OutputStreamWriter(socketoutstr);
        bw = new BufferedWriter(osr);

        InputStream socketinstr = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(socketinstr);
        br = new BufferedReader(isr);

        setConnectionIcon(true);

        while (true) {
          String message = br.readLine();
          debugMessage("Received: " + message);
          String decodedMessage = decode.apply(message);
          debugMessage("Decoded: " + decodedMessage);
        }
      } catch (IOException uhe) {
        debugMessage("Failed to connect to server");
      }
    });
    socketThread.start();
  }

  /**
   * Close the connection.
   */
  protected void disconnect() {
    if (!isConnected()) {
      return;
    }
    try {
      bw.close();
      br.close();
      socket.close();
      debugMessage("Connection to server closed.");
      setConnectionIcon(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generate the user interface
   */
  public Pane makeSceneGraph() {
    VBox root = new VBox();

    Button buttonConnectToServer = new Button("Connect");
    buttonConnectToServer.setOnAction(e -> connect());
    root.getChildren().add(buttonConnectToServer);

    connectedStateLabel = new Label("", disconnectedImageView);
    root.getChildren().add(connectedStateLabel);

    Button disconnectFromServer = new Button("Disconnect");
    disconnectFromServer.setOnAction(e -> disconnect());
    root.getChildren().add(disconnectFromServer);

    root.getChildren().add(new Label("Send message:"));

    TextField textField = new TextField();
    root.getChildren().add(textField);

    Button buttonSend = new Button("Send");
    root.getChildren().add(buttonSend);
    buttonSend.setOnAction(e -> {
      send(textField.getText());
    });

    root.getChildren().add(new Label("Log:"));
    logTextArea = new TextArea(logText);
    root.getChildren().add(logTextArea);

    return root;
  }

  /**
   * Send a text via the socket.
   */
  private void send(String text) {
    if (!isConnected()) {
      debugMessage("Must connect first.");
      return;
    }
    try {
      String encodedText = encode.apply(text);
      bw.write(encodedText);
      bw.newLine();
      bw.flush();
      debugMessage("Encoded message '" + text + "' to + '" + encodedText + "'");
      debugMessage("Sent text '" + encodedText + "' via socket.");
    } catch (IOException e) {
      debugMessage("Failed to send message: " + e.getMessage());
    }
  }

  /**
   * Returns true if the connection is established.
   */
  public boolean isConnected() {
    return !(socket == null || br == null || bw == null);
  }

  /**
   * Print a debug message.
   */
  protected void debugMessage(String message) {
    //System.out.println(message);
    logText = "[" + name + "] " + message + "\n" + logText;
    if (logTextArea != null) {
      Platform.runLater(() -> logTextArea.setText(logText));
    }
  }

  /**
   * Show the connection state as an icon.
   */
  private void setConnectionIcon(boolean isConnected) {
    Platform.runLater(() -> {
      if (isConnected) {
        connectedStateLabel.setGraphic(connectedImageView);
      } else {
        connectedStateLabel.setGraphic(disconnectedImageView);
      }
    });
  }
}
