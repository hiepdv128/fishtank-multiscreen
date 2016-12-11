package mover;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

import java.util.Random;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Linear implements Mover {

    private double maxWidth;
    private double maxHeight;
    private double yAdd = 1;
    private double xAdd = 1;

    private Random random;

    public Linear() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        this.maxHeight = screen.getHeight();
        this.maxWidth = screen.getWidth();

        random = new Random();
    }

    @Override
    public void move(ImageView imageFish) {
        if (imageFish.getY() >= maxHeight || imageFish.getY() <= 0) {
            yAdd = -yAdd * (1 + random.nextInt(1));
        }

        if (imageFish.getX() >= maxWidth || imageFish.getX() <= 0) {
            xAdd = -xAdd + (1 + random.nextInt(2));
        }

        if (xAdd > 0) {
            imageFish.setRotate(180);
        }

        imageFish.setX(imageFish.getX() + xAdd);
        imageFish.setY(imageFish.getY() + yAdd);
    }
}
