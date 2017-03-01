package mover;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import org.bson.Document;
import server.DeviceConnection;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Linear implements Mover {

    private static double MAX_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
//    private static double MAX_WIDTH =800;
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

        if (imageBounds.getMaxX() >MAX_WIDTH + deviceConnection.getClientScreenWidth() || imageBounds.getMinX() < 0) {
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

        pushXYFish(imageFish,deviceConnection);
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
        System.out.println("push to client : " + xNext+"           "+yNext);

    }


        public void pushXYFish(ImageView fish,DeviceConnection deviceConnection) {
        if (deviceConnection.getSocket() != null && deviceConnection.getSocket().isConnected()) {
            PrintStream pr = null;
            try {
                pr = new PrintStream(deviceConnection.getSocket().getOutputStream());
                String jsonFish = getFishJson(fish).toJson();
                pr.println(jsonFish);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Document getFishJson(ImageView fish) {
        String imageSource = fish.getImage().impl_getUrl();        //chua tim duoc cach get url tu image
        return new Document()
                .append("id", fish.getId())
                .append("x", fish.getX())
                .append("y", fish.getY())
                .append("source", imageSource);

    }

}
