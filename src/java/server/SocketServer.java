package server;

import javafx.scene.image.ImageView;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by MyPC on 2/7/2017.
 */
public class SocketServer {
    private static Socket socket;

    public void acceptSocket() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final ServerSocket server;
                try {
                    server = new ServerSocket(8081);

                    while (true) {
                        try {
                            System.out.print("Server Ready ...");
                            socket = server.accept();
                            new SocketThread(socket).start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    public void pushXYFish(ImageView fish, String imageSource) {
        if (socket != null && socket.isConnected()) {
            PrintStream pr = null;
            try {
                pr = new PrintStream(socket.getOutputStream());
                // x - fish.getX
                //y- fish.getY
                //image
                String jsonFish = getFishJson(fish, imageSource).toJson();
                System.out.println("push to client : " + jsonFish);
                pr.println(jsonFish);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Document getFishJson(ImageView fish, String source) {
        return new Document()
                .append("x", fish.getX())
                .append("y", fish.getY())
                .append("source", source);

    }

    public static void main(String[] a) {
        SocketServer server = new SocketServer();
        server.acceptSocket();
    }
}
