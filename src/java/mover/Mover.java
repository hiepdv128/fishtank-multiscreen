package mover;

import javafx.scene.image.ImageView;
import server.DeviceConnection;

/**
 * Created by hiepdv on 11/12/2016.
 */
public interface Mover {

    void move(ImageView imageView, DeviceConnection deviceConnection);
}
