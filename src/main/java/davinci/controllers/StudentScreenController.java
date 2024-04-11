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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class StudentScreenController implements Initializable {

    User u = App.user;
    Student s = App.student;
    @FXML
    private Label nameLabel;

    @FXML
    private Label StudentIDLabel;

    @FXML
    private Label GPALabel;

    @FXML
    private Label MajorLabel;

    @FXML
    private Label StandingLabel;

    @FXML
    private ListView eightSemesterPlanListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init");
        
        String usersName = "Name: " + u.getFirstName() + " " + u.getLastName();
        String studentID = "ID: " + u.getStudentID();
        String GPA = "GPA: " + s.getGPAString();
        String major = "Major: " + s.getMajor().getName();
        String standing = "Standing: " + s.getStanding();
        

        //displayEightSemesterPlan();
        nameLabel.setText(usersName);
        StudentIDLabel.setText(studentID);
        GPALabel.setText(GPA);
        MajorLabel.setText(major);
        StandingLabel.setText(standing);

    }

    private void displayEightSemesterPlan(){
        ObservableList<String> eightSemesterPlan = FXCollections.observableArrayList();

        for(Course course : s.displayEightSemesterPlan()) {
            eightSemesterPlan.add(course.getTitle());
        }
        eightSemesterPlanListView.setItems(eightSemesterPlan);
    }

}
