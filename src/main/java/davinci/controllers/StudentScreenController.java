package davinci.controllers;

import davinci.library.App;
import davinci.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentScreenController implements Initializable {

    @FXML
    private Label testLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init");
        User u = App.user;
        String usersName = u.getFirstName() + " " + u.getLastName();
        testLabel.setText(usersName);
    }

}
