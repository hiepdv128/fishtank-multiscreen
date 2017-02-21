package server;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 * Created by MyPC on 2/21/2017.
 */
public class ServerHandler extends Thread {
    DeviceConnection deviceConnection;
    ServerSocket server;

    public ServerHandler(DeviceConnection deviceConnection, ServerSocket server) {
        this.deviceConnection = deviceConnection;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            deviceConnection.setSocket(server.accept());
            setupOndeviceConnected(deviceConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupOndeviceConnected(DeviceConnection deviceConnection) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(deviceConnection.getSocket().getInputStream()));
        Document deviceInfo = Document.parse(inputStream.readLine());
        deviceConnection.setClientScreenHeight(deviceInfo.getInteger("height"));
        deviceConnection.setClientScreenWidth(deviceInfo.getInteger("width"));
    }
}
