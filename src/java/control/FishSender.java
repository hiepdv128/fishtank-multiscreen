package control;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import model.Fish;
import mover.Linear;
import org.bson.Document;
import server.DeviceConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepdv on 05/03/2017.
 */
public class FishSender extends AnimationTimer {

    private List<Fish> fishs;
    private DeviceConnection connection;
    private ObjectMapper mapper;

    public FishSender(List<Fish> fishs, DeviceConnection connection) {
        this.fishs = fishs;
        this.connection = connection;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(long l) {
        List<Document> fishToSend = new ArrayList<>();

        if (connection.getSocket() == null || connection.getSocket().isClosed()) {
            return;
        }

        try {
            for (Fish fish : fishs) {
                Bounds imageBounds = fish.getImage().localToScreen(fish.getImage().getBoundsInLocal());

                Document fishDocument = new Document()
                        .append("id", fish.getId())
                        .append("x", imageBounds.getMinX() - Linear.MAX_WIDTH)
                        .append("y", imageBounds.getMinY())
                        .append("source", fish.getImage().getUrl())
                        .append("rotation", fish.getRotation());
                fishToSend.add(fishDocument);
            }

            if (fishToSend.isEmpty()) {
                return;
            }

            String message = mapper.writeValueAsString(fishToSend);
            connection.getWriter().write(message);
            connection.getWriter().newLine();
            connection.getWriter().flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
