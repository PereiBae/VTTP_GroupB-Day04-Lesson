package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.*;
import java.util.*;

// Work for the Thread to perform
public class ClientHandler implements Runnable {

    private final Socket sock;

    public ClientHandler(Socket s) {
        sock = s;
    }

    // Entry point for the thread
    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();

        try {
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

            System.out.println(">>> [" + threadName + "] Client: " + fromClient + " \n");

            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

            // Write the message out
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();

            os.close();
            is.close();
            sock.close();

        } catch (IOException ex) {
            // Exception Handler
            ex.printStackTrace();
        }
    }

}
