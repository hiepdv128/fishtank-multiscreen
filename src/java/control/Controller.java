package control;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int COLUMNS  =   4;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  100;
    private static final int OFFSET_Y =  200;
    private static final int WIDTH    = 374;
    private static final int HEIGHT   = 243;
    @FXML
    private Pane pnRoot;
    @FXML
    private ImageView img;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setUpBackground();
    }

    public void setUpBackground() {
        Image image = new Image(getClass().getClassLoader().getResource("background.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pnRoot.setBackground(new Background(backgroundImage));
      img = new ImageView(new Image("fish2.gif"));
        //img.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        img.relocate(10, 10);
        pnRoot.getChildren().addAll(img);
//        final Animation animation = new SpriteAnimation(
//                img,
//                Duration.millis(400.0),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                WIDTH, HEIGHT
//        );
//
//        animation.setCycleCount(Animation.INDEFINITE);
//        animation.play();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <1366; i++) {
                    try {
                        Thread.sleep(10);
                        img.relocate(i, 10);
                        if(i==1365) i=0;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        a.start();
    }
}
