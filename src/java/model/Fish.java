package model;

import javafx.scene.image.ImageView;
import mover.Mover;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Fish implements Runnable {

    private int id;
    private int deviceId;
    private int xPosition;
    private int yPosition;
    private ImageView imageFish;
    private Mover mover;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public ImageView getImageFish() {
        return imageFish;
    }

    public void setImageFish(ImageView imageFish) {
        this.imageFish = imageFish;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    @Override
    public void run() {
        while (true) {
            mover.move(imageFish);

        }
    }
}
