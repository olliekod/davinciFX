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

    @FXML
    private Label degreeProgressLabel;

    @FXML 
    private Label currentStandingLabel;

    @FXML 
    private Label takenHoursLabel;

    @FXML
    private Label hoursRemainingLabel;

    double progress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set progress bar for student progress
        degreeProgressBar.setStyle("-fx-accent: #9A2A2A;");
        progress = s.calculateDegreeProgress();
        degreeProgressBar.setProgress(progress);
        
        //Set labels for student progress
        String degreeProgress = String.format("%.2f", progress * 100);
        degreeProgressLabel.setText("Degree Progress: " + degreeProgress + "%");
        currentStandingLabel.setText("Given Degree Progress - Your Current Standing: " + s.getStanding());
        takenHoursLabel.setText("Taken Hours: " + s.calculateTotalCreditHoursInt());
        hoursRemainingLabel.setText("Remaining Hours: " + (s.getMajor().getHours() - s.calculateTotalCreditHoursInt()));
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
