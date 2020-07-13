package praktikum.aufgabe4;

import java.io.*;
import java.net.*;
import java.util.function.UnaryOperator;

/**
 * A client in a client-server connection.
 */
public class Client extends SocketWidget {

    private final String hostname;

    public Client(int port, String hostname, UnaryOperator<String> encode, UnaryOperator<String> decode) {
        super(port, "Client", encode, decode);
        this.hostname = hostname;
    }

    @Override
    public Socket getSocket() throws IOException {
        return new Socket(hostname, port);
    }
}
