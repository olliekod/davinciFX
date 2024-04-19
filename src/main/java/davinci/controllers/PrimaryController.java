package davinci.controllers;

import davinci.library.App;
import davinci.model.Facade;
import davinci.model.Faculty;
import davinci.model.UserList;

import java.io.IOException;
import java.util.UUID;

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
            App.user = f.getCurrentUser();

            UUID searchID = App.user.getID();
            Faculty fac = UserList.getFacultyByID(searchID);
            if (fac != null) {
                App.faculty = fac;
                App.setRoot("advisorScreen");
                System.out.println("fac works");
                return;
            }

            App.student = f.getCurrentStudent();
            App.setRoot("studentScreen");
        } else {
            System.out.println("login failed");
        }

        
    }

}
