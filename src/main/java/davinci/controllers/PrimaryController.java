package davinci.controllers;

import davinci.library.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("createaccount");
    }

    @FXML
    private void loginButtonClicked() throws IOException {
        App.setRoot("studentScreen");
    }

}
