package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by MyPC on 2/7/2017.
 */
public class DeviceConnection {

    private Socket socket;
    private Integer clientScreenWidth;
    private Integer clientScreenHeight;
    private BufferedWriter writer;

    public DeviceConnection() {
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
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

}
