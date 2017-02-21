package server;

import java.net.Socket;

/**
 * Created by MyPC on 2/7/2017.
 */
public class DeviceConnection {

    private Socket socket;
    private Integer clientScreenWidth;
    private Integer clientScreenHeight;

    public DeviceConnection() {
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Integer getClientScreenWidth() {
        if (clientScreenWidth == null) {
            clientScreenWidth = 0;
        }
        return clientScreenWidth;
    }

    public void setClientScreenWidth(Integer clientScreenWidth) {
        this.clientScreenWidth = clientScreenWidth;
    }

    public Integer getClientScreenHeight() {
        if (clientScreenHeight == null) {
            clientScreenHeight = 0;
        }
        return clientScreenHeight;
    }

    public void setClientScreenHeight(Integer clientScreenHeight) {
        this.clientScreenHeight = clientScreenHeight;
    }

    //    public void pushXYFish(ImageView fish, String imageSource) {
//        if (socket != null && socket.isConnected()) {
//            PrintStream pr = null;
//            try {
//                pr = new PrintStream(socket.getOutputStream());
//                // x - fish.getX
//                //y- fish.getY
//                //image
//                String jsonFish = getFishJson(fish, imageSource).toJson();
//                System.out.println("push to client : " + jsonFish);
//                pr.println(jsonFish);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Document getFishJson(ImageView fish, String source) {
//        return new Document()
//                .append("x", fish.getX())
//                .append("y", fish.getY())
//                .append("source", source);
//
//    }
//
//    public static void main(String[] a) {
//        SocketServer server = new SocketServer();
//        server.acceptSocket();
//    }
}
