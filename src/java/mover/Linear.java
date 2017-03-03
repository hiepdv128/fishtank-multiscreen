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
    //    private static double MAX_WIDTH =800;
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

        if (imageBounds.getMaxY() >= MAX_HEIGHT || imageBounds.getMinY() <= 300) {
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

        double yNext = imageFish.getY() + 1;
        double xNext = imageFish.getX() + xAdd * (1 + random.nextInt(2));

        imageFish.setX(xNext);
        //imageFish.setY(yNext);

        pushXYFish(imageFish, deviceConnection);
    }

    private void moveInRootScreen(ImageView imageFish) {
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

        double yNext = imageFish.getY() + yAdd;
        double xNext = imageFish.getX() + xAdd * (1 + random.nextInt(2));

        imageFish.setX(xNext);
        imageFish.setY(yNext);
        //System.out.println("push to client : " + xNext+"           "+yNext);
    }


    private void pushXYFish(ImageView fish, DeviceConnection deviceConnection) {
        if (deviceConnection.getSocket() != null && deviceConnection.getSocket().isConnected()) {
            try {
                String jsonFish = getFishJson(fish).toJson();

                deviceConnection.getWriter().write(jsonFish);
                deviceConnection.getWriter().newLine();
                deviceConnection.getWriter().flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Document getFishJson(ImageView fish) {
        String imageSource = fish.getImage().impl_getUrl();        //chua tim duoc cach get url tu image
        System.out.println(fish.getY());
        return new Document()
                .append("id", fish.getId())
                .append("x", fish.getX() - MAX_WIDTH )
                .append("y", fish.getY())
                .append("source", imageSource)
                .append("rotation", xAdd < 0 ? 0 : 180);

    }

}
