package davinci.controllers;

import davinci.library.App;
import davinci.model.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentScreenController {

    @FXML
    private Label testLabel;
    
    @FXML
    public void initialize() throws IOException {
        User u = App.user;
        String name = u.getFirstName() + " " + u.getLastName();
        testLabel.setText(name);
    }

    
}
