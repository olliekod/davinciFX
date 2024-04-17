package davinci.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import davinci.library.App;
import davinci.model.Faculty;
import davinci.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class advisorScreenController implements Initializable {

    @FXML
    private Button logoutButton;

    private Faculty advisor = App.faculty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    private void logoutButtonClicked() throws IOException {
        App.setRoot("login");
    }
}


