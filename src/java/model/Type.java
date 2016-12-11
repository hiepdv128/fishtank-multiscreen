package model;

import javafx.scene.image.Image;
import mover.Mover;

/**
 * Created by hiepdv on 11/12/2016.
 */
public class Type {

    private Image img;
    private Mover mover;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }
}
