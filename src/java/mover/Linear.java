package mover;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Linear implements Mover {

    private double maxWidth;
    private double maxHeight;
    private double yAdd = 1;
    private double xAdd = 1;

    public Linear() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        this.maxHeight = screen.getHeight();
        this.maxWidth = screen.getWidth();
    }

    @Override
    public void move(ImageView imageFish) {
        Bounds imageBounds = imageFish.localToScreen(imageFish.getBoundsInLocal());

        if (imageBounds.getMaxY() > maxHeight || imageBounds.getMinY() < 0) {
            yAdd = -yAdd;
        }

        if (imageBounds.getMaxX() > maxWidth || imageBounds.getMinX() < 0) {
            xAdd = -xAdd;
        }

        if (xAdd < 0) {
            imageFish.setRotate(0);
        }

        if (xAdd > 0) {
            imageFish.setRotate(180);
        }

        double yNext = imageFish.getY() + yAdd * 3;
        double xNext = imageFish.getX() + xAdd * 3;
//
//        if (yNext < 0) {
//            yNext = 0;
//        }
//        if (yNext > maxHeight - imageBounds.getHeight()) {
//            yNext = maxHeight - imageBounds.getHeight();
//        }
//
//        if (xNext < 0) {
//            xNext = 0;
//        }
//        if (xNext > maxWidth - imageBounds.getWidth()) {
//            xNext = maxWidth - imageBounds.getWidth();
//        }
//
        imageFish.setX(xNext);
        imageFish.setY(yNext);
    }
}
