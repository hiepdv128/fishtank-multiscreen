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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setUpBackground();
        this.random = new Random();
        new Thread(new FishRunner(fishs)).start();
    }

    public void setUpBackground() {
        Image image = new Image(getClass().getClassLoader().getResource("background.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pnRoot.setBackground(new Background(backgroundImage));
    }

    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) pnRoot.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddFish(ActionEvent event) {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        ImageView fishImage = new ImageView(new Image("fish-1.gif"));
        fishImage.setRotationAxis(new Point3D(0, 1, 0));
        fishImage.relocate(random.nextInt((int)screen.getWidth()), random.nextInt((int)screen.getHeight()));

        Fish newFish = new Fish(fishImage, new Linear());
        pnRoot.getChildren().add(fishImage);
        fishs.add(newFish);
    }
}
