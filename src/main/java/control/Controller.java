package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Fish;
import mover.Linear;
import org.bson.Document;
import server.DeviceConnection;
import utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane pnRoot;

    private List<Fish> fishs = new ArrayList<>();

    private Random random;

    private ServerSocket server;

    private DeviceConnection deviceConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pnRoot.setBackground(new Background(backgroundImage));

        try {
            this.random = new Random();
            this.server = new ServerSocket(2509);
            this.deviceConnection = new DeviceConnection();

            this.deviceConnection.setSocket(server.accept());
            this.setupOndeviceConnected(deviceConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) pnRoot.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddFish(ActionEvent event) {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        // Không ổn
        ImageView fishImage = new ImageView(new Image("fish-2.gif"));
        fishImage.setRotationAxis(new Point3D(0, 1, 0));
        fishImage.relocate(random.nextInt((int) Constants.WIDTH_SCREEN), random.nextInt(Constants.HEIGHT_SCREEN));

        Fish newFish = new Fish(deviceConnection, new Linear(), "fish-2.gif");
        newFish.start();
        pnRoot.getChildren().add(newFish.getImage());
        fishs.add(newFish);
    }

    public void setupOndeviceConnected(DeviceConnection deviceConnection) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(deviceConnection.getSocket().getInputStream()));

        Document deviceInfo = Document.parse(inputStream.readLine());
        deviceConnection.setClientScreenHeight(deviceInfo.getInteger("height"));
        deviceConnection.setClientScreenWidth(deviceInfo.getInteger("width"));
    }
}
