
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;

    public Server(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted: " + socket);
            open();
            boolean done = false;
            while (!done) {
                try {
                    System.out.println("escuchando desde el servidor");
                    String line = streamIn.readUTF();
                    String[] serverMsgSplitted = line.split(" ");//the split method can be changed depending on the received command

                    if ("CONNECT".equals(serverMsgSplitted[0])) {
                        System.out.println("entro en connect en el servidor");
                        streamOut.writeUTF("OK");
                        
                    }
                } catch (IOException ioe) {
                    done = true;
                }
            }
            close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (streamIn != null) {
            streamIn.close();
        }
    }

    public static void main(String args[]) {
        Server server = null;
        server = new Server(Integer.parseInt("6066"));
    }
}
