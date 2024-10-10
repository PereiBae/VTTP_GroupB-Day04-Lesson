package Network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        // Set the default port to 3000
        int port = 3000;
        if (args.length > 0)
            port = Integer.parseInt(args[0]);

        // Create a server port, TCP
        ServerSocket server = new ServerSocket(port);

        while (true) {
            // Wait for an incoming connection
            System.out.println("Waiting for conneciton on port " + port + "\n");
            Socket sock = server.accept();

            System.out.println("Got a new connection");

            // Get the input Stream
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            // Get the Output Stream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // Read the result from the server
            String fromClient = br.readLine();

            System.out.println(">>> Client: " + fromClient + " \n");

            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

            // Write the message out
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();

            os.close();
            is.close();
            sock.close();
        }
    }

}
