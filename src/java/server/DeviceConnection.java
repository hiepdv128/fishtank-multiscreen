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


//
//    public static void main(String[] a) {
//        SocketServer server = new SocketServer();
//        server.acceptSocket();
//    }
}
