package mover;

import javafx.geometry.Bounds;
import javafx.stage.Screen;
import model.Fish;

import java.util.Random;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Linear implements Mover {

    public static double MAX_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static double MAX_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private double yAdd = 1.5;
    private double xAdd = 1;

    private static Random random = new Random();

    public Linear() {
    }

    @Override
    public void move(Fish fish) {
        Bounds imageBounds = fish.getImage().localToScreen(fish.getImage().getBoundsInLocal());

        if (imageBounds.getMaxY() >= MAX_HEIGHT) {
            yAdd = -1;
        }

        if (imageBounds.getMinY() <= 0) {
            yAdd = 1;
        }

        if (imageBounds.getMaxX() > MAX_WIDTH + fish.getConnection().getClientScreenWidth()) {
            xAdd = -1;
        }

        if (imageBounds.getMinX() < 0) {
            xAdd = 1;
        }

        if (xAdd < 0) {
            fish.getImage().setRotate(0);
            fish.setRotation(0D);
        }

        if (xAdd > 0) {
            fish.getImage().setRotate(180);
            fish.setRotation(180D);
        }

        double yNext = fish.getImage().getY() + yAdd * (1 + random.nextInt(1));
        double xNext = fish.getImage().getX() + xAdd * (1 + random.nextInt(2));

        fish.getImage().setX(xNext);
        fish.getImage().setY(yNext);
    }
}
