package mover;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import server.DeviceConnection;

import java.util.Random;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Linear implements Mover {

    private static double MAX_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    private static double MAX_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private double yAdd = 1;
    private double xAdd = 1;

    private Random random;

    public Linear() {
        this.random = new Random();
    }

    @Override
    public void move(ImageView imageFish, DeviceConnection deviceConnection) {
        if (deviceConnection.getSocket() == null || !deviceConnection.getSocket().isConnected()) {
            moveInRootScreen(imageFish);
            return;
        }
        moveWithClientScreen(imageFish, deviceConnection);

    }

    public void moveWithClientScreen(ImageView imageFish, DeviceConnection deviceConnection) {
        Bounds imageBounds = imageFish.localToScreen(imageFish.getBoundsInLocal());

        if (imageBounds.getMaxY() > MAX_HEIGHT || imageBounds.getMinY() < 0) {
            yAdd = -yAdd;
        }

        if (imageBounds.getMaxX() > MAX_WIDTH + deviceConnection.getClientScreenWidth() || imageBounds.getMinX() < 0) {
            xAdd = -xAdd;
        }

        if (xAdd < 0) {
            imageFish.setRotate(0);
        }

        if (xAdd > 0) {
            imageFish.setRotate(180);
        }

        double yNext = imageFish.getY() + yAdd * (1 + random.nextInt(1));
        double xNext = imageFish.getX() + xAdd * (1 + random.nextInt(2));

        imageFish.setX(xNext);
        imageFish.setY(yNext);
    }

    public void moveInRootScreen(ImageView imageFish) {
        Bounds imageBounds = imageFish.localToScreen(imageFish.getBoundsInLocal());

        if (imageBounds.getMaxY() > MAX_HEIGHT || imageBounds.getMinY() < 0) {
            yAdd = -yAdd;
        }

        if (imageBounds.getMaxX() > MAX_WIDTH || imageBounds.getMinX() < 0) {
            xAdd = -xAdd;
        }

        if (xAdd < 0) {
            imageFish.setRotate(0);
        }

        if (xAdd > 0) {
            imageFish.setRotate(180);
        }

        double yNext = imageFish.getY() + yAdd * (1 + random.nextInt(1));
        double xNext = imageFish.getX() + xAdd * (1 + random.nextInt(2));

        imageFish.setX(xNext);
        imageFish.setY(yNext);
    }


}
