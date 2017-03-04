package mover;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import org.bson.Document;
import server.DeviceConnection;

import java.io.IOException;
import java.util.Random;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Linear implements Mover {

    private static double MAX_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    private static double MAX_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private double yAdd = 1.5;
    private double xAdd = 1;

    private static Random random = new Random();

    public Linear() {
    }

    @Override
    public void move(ImageView imageFish, DeviceConnection deviceConnection) {
        if (deviceConnection.getSocket() == null || !deviceConnection.getSocket().isConnected()) {
            moveInRootScreen(imageFish);
            return;
        }

        moveWithClientScreen(imageFish, deviceConnection);

    }

    private void moveWithClientScreen(ImageView imageFish, DeviceConnection deviceConnection) {
        Bounds imageBounds = imageFish.localToScreen(imageFish.getBoundsInLocal());

        if (imageBounds.getMaxY() >= MAX_HEIGHT) {
            yAdd = -1;
        }

        if (imageBounds.getMinY() <= 0) {
            yAdd = 1;
        }

        if (imageBounds.getMaxX() > MAX_WIDTH + deviceConnection.getClientScreenWidth()) {
            xAdd = -1;
        }

        if (imageBounds.getMinX() < 0) {
            xAdd = 1;
        }

        if (xAdd < 0) {
            imageFish.setRotate(0);
        }

        if (xAdd > 0) {
            imageFish.setRotate(180);
        }

        double yNext = imageFish.getY() + yAdd * 1;
        double xNext = imageFish.getX() + xAdd * (1 + random.nextInt(2));

        imageFish.setX(xNext);
        imageFish.setY(yNext);

        if (xNext >= MAX_WIDTH - imageBounds.getWidth()) {
            pushXYFish(imageFish, deviceConnection);
        }
    }

    private void moveInRootScreen(ImageView imageFish) {
        Bounds imageBounds = imageFish.localToScreen(imageFish.getBoundsInLocal());

        if (imageBounds.getMaxY() >= MAX_HEIGHT) {
            yAdd = -1;
        }

        if (imageBounds.getMinY() <= 0) {
            yAdd = 1;
        }

        if (imageBounds.getMaxX() > MAX_WIDTH) {
            xAdd = -1;
        }

        if (imageBounds.getMinX() < 0) {
            xAdd = 1;
        }

        if (xAdd < 0) {
            imageFish.setRotate(0);
        }

        if (xAdd > 0) {
            imageFish.setRotate(180);
        }

        double yNext = imageFish.getY() + yAdd * 1;
        double xNext = imageFish.getX() + xAdd * (1 + random.nextInt(2));

        imageFish.setX(xNext);
        imageFish.setY(yNext);
    }

    private void pushXYFish(ImageView fish, DeviceConnection deviceConnection) {
        try {
            if (deviceConnection.getSocket() != null && deviceConnection.getSocket().isConnected()) {
                String imageSource = fish.getImage().impl_getUrl();        //chua tim duoc cach get url tu image
                Bounds imageBounds = fish.localToScreen(fish.getBoundsInLocal());

                System.out.println(imageBounds.getMinY());
                String jsonFish = new Document()
                        .append("id", fish.getId())
                        .append("x", imageBounds.getMinX() + imageBounds.getWidth() - MAX_WIDTH)
                        .append("y", imageBounds.getMinY())
                        .append("source", imageSource)
                        .append("rotation", xAdd < 0 ? 0 : 180)
                        .toJson();

                deviceConnection.getWriter().write(jsonFish);
                deviceConnection.getWriter().newLine();
                deviceConnection.getWriter().flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
