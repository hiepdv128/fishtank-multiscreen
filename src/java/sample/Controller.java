package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnSubmit;

    @FXML
    private void handleSubmitClick(ActionEvent event) {
        System.out.println("Hihihi");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
