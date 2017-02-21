package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mover.Mover;
import org.bson.types.ObjectId;
import server.DeviceConnection;

import java.io.Serializable;
import java.net.Socket;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Fish extends AnimationTimer {

    private String id;
    private FishImage image;
    private Mover mover;
    private DeviceConnection connection;

    public Fish(DeviceConnection connection, Mover mover, String imageSource) {
        this.id = ObjectId.get().toHexString();
        this.mover = mover;
        this.image = new FishImage(imageSource);

        this.connection = connection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FishImage getImage() {
        return image;
    }

    public void setImage(FishImage image) {
        this.image = image;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public DeviceConnection getConnection() {
        return connection;
    }

    public void setConnection(DeviceConnection connection) {
        this.connection = connection;
    }

    @Override
    public void handle(long l) {
        mover.move(imageFish);
        imageFish.getImage().
    }
}
