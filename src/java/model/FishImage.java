package model;

import javafx.scene.image.ImageView;

/**
 * Created by hiepdv on 20/02/2017.
 */
public class FishImage extends ImageView {

    private String url;

    public FishImage(String url) {
        super(url);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
