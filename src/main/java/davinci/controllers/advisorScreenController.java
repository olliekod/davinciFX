package davinci.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import davinci.library.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class advisorScreenController implements Initializable {

    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String text = "test test test";
    }

    @FXML
    private void logoutButtonClicked() throws IOException {
        App.setRoot("login");
    }
}


