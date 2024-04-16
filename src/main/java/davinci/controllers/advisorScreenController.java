package davinci.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class advisorScreenController implements Initializable {

    @FXML
    private Label testLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String text = "test test test";
        testLabel.setText(text);
    }
}


