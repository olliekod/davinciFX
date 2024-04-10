package davinci.controllers;

import davinci.library.App;
import davinci.model.Facade;
import davinci.model.UserList;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField usernameBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("createaccount");
    }

    @FXML
    private void loginButtonClicked() throws IOException {

        String username = usernameBox.getText();
        String password = passwordBox.getText();
        System.out.println(username);
        System.out.println(password);
        
        Facade f = new Facade();
        if (f.login(username, password)) {
            App.setRoot("studentScreen");
        } else {
            System.out.println("login failed");
        }

        
    }

}
