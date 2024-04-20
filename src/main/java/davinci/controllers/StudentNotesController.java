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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

public class StudentNotesController implements Initializable{

    User u = App.user;
    Student s = App.student;


    @FXML
    private ListView noteList;

    @FXML
    private Button logoutButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayNotesListView();
    }

    public void displayNotesListView() {
        ObservableList<String> notes = FXCollections.observableArrayList();
        for(String note : s.getNotes()) {
            if(note != null){
                notes.add(note);
            }
        }
        noteList.setItems(notes);
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
    private void degreeProgressButtonClicked() throws IOException {
        App.setRoot("DegreeProgress");
    }

    @FXML
    private void eightSemPlanButtonClicked() throws IOException {
        App.setRoot("studentEightSemPlan");
    }
}
