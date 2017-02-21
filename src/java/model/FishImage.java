package model;

import javafx.scene.image.ImageView;

/**
 * Created by hiepdv on 20/02/2017.
 */
public class FishImage extends ImageView {

    private String uri;

    public FishImage(String uri) {
        super(uri);
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
