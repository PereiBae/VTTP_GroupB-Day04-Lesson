package Network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServer {

    public static void main(String[] args) throws IOException {

        // Create a thread pool = Create the worker
        ExecutorService thrPool = Executors.newFixedThreadPool(2);

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

            // Create the work
            ClientHandler handler = new ClientHandler(sock);

            //Pass the work to the worker
            thrPool.submit(handler);

        }
    }

}
