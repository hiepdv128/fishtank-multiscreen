package control;

import interfaces.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Fish;
import mover.Linear;
import server.DeviceConnection;
import server.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable , Listener {

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
            this.server = new ServerSocket(2509);
            this.deviceConnection = new DeviceConnection();

            new ServerHandler(this.deviceConnection, this.server).start();

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
    public void onClickFish1(ActionEvent event){
        System.out.println("aaaa");
    }
    @FXML
    public void handleAddFish(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout_fish.fxml"));
            Parent layoutFish = fxmlLoader.load();
            ControllerDialogFish dialogFish = fxmlLoader.<ControllerDialogFish>getController();
            dialogFish.setListenerClickFish(Controller.this);

            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(layoutFish, 800, 450));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    @Override
    public void onClickFish(String resourceFish) {
       Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Fish newFish = new Fish(deviceConnection, new Linear(), resourceFish);
        newFish.start();
        pnRoot.getChildren().add(newFish.getImage());
        fishs.add(newFish);
    }
}
