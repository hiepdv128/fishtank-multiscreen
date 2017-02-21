package mover;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import server.DeviceConnection;
import server.SocketServer;

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

    }

    public void moveWithClientScreen(ImageView imageFish, DeviceConnection deviceConnection) {
        Bounds imageBounds = imageFish.localToScreen(imageFish.getBoundsInLocal());

        if ((imageBounds.getMinY() < 0 || imageBounds.getMaxY() > MAX_HEIGHT
                || (imageBounds.getMaxX() >= MAX_WIDTH) && imageBounds.getMaxY() >= MAX_HEIGHT)) {
            yAdd = -yAdd;
        }

        if (imageBounds.getMaxX() > MAX_HEIGHT || imageBounds.getMinX() < 0) {
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

        if (imageBounds.getMaxX() > MAX_HEIGHT || imageBounds.getMinX() < 0) {
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



    public void verifyDeviceConnection(DeviceConnection connection) {
        if (connection == null
                || connection.getClientScreenHeight() == null
                || connection.getClientScreenWidth() == null
                || connection.getClientScreenWidth() < ) {
            return;
        }



        MAX_WIDTH += connection.getClientScreenWidth();
        MAX_HEIGHT += connection.getClientScreenHeight();
    }
}
