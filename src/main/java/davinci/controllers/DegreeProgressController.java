package davinci.controllers;

import davinci.library.App;
import davinci.model.User;
import davinci.model.Student;
import davinci.model.Major;
import davinci.model.Course;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.application.Platform;

public class DegreeProgressController implements Initializable  {

    User u = App.user;
    Student s = App.student;

    @FXML
    private Button logoutButton;

    @FXML
    private Button userInformationButton;

    @FXML
    private Button notesButton;

    @FXML
    private Button eightSemesterButton;

    @FXML
    private ProgressBar degreeProgressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    private void logoutButtonClicked() throws IOException {
        App.setRoot("login");
    }   

    @FXML
    private void userInformationButtonClicked() throws IOException {
        App.setRoot("studentScreen");
    }

    @FXML
    private void notesButtonClicked() throws IOException {
        App.setRoot("studentNotes");
    }

    @FXML
    private void eightSemesterButtonClicked() throws IOException {
        App.setRoot("studentEightSemPlan");
    }
    
}
