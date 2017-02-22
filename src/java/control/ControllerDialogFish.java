package control;

import interfaces.Listener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDialogFish implements Initializable {
    private Listener listenerClickFish;

    public void setListenerClickFish(Listener listenerClickFish) {
        this.listenerClickFish = listenerClickFish;
    }

    @FXML
    private Pane panelFish;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        panelFish.setBackground(new Background(backgroundImage));


    }



    public void onClickFish(MouseEvent mouseEvent) {
        String resourceFish = (((ImageView) ((MouseEvent) mouseEvent).getTarget()).getImage().impl_getUrl());        //chua tim duoc cach get url tu image
        listenerClickFish.onClickFish(resourceFish);

    }


}
