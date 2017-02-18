package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by MyPC on 2/7/2017.
 */
public class SocketThread extends Thread {
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = br.readLine();
            if (msg != null)
                System.out.println("\nMessage from client : " + msg);
            try {
                Thread.sleep(2000);
                PrintStream pr = new PrintStream(socket.getOutputStream());
                pr.println("we reiceived!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
