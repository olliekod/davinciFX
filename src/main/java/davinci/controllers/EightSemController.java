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


public class EightSemController implements Initializable{
    User u = App.user;
    Student s = App.student;

    @FXML
    private TableView semesterOnePlanTable;

    @FXML 
    private TableView semesterTwoPlanTable;

    @FXML
    private TableView semesterThreePlanTable;

    @FXML
    private TableView semesterFourPlanTable;

    @FXML
    private TableView semesterFivePlanTable;

    @FXML
    private TableView semesterSixPlanTable;

    @FXML
    private TableView semesterSevenPlanTable;

    @FXML
    private TableView semesterEightPlanTable;

    @FXML
    private ObservableList<Course> courses;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        courses = FXCollections.observableArrayList(); // Create an ObservableList
        for(Course c : s.displayEightSemesterPlan()) {
            courses.add(c);
        }
        semesterOnePlanTable.setItems(courses);
    }
}
