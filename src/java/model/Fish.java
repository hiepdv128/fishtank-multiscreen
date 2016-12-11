package model;

import javafx.scene.image.ImageView;
import mover.Mover;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Fish {

    private String id;
    private int deviceId;
    private ImageView imageFish;
    private Mover mover;

    public Fish(ImageView imageFish, Mover mover) {
        this.id = String.valueOf(System.currentTimeMillis());
        this.imageFish = imageFish;
        this.mover = mover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
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

    public void swim() {
        mover.move(imageFish);
    }
}
