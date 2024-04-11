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
    private ListView EightSemesterPlanListView;

    @FXML
    private Label DateofBirthLabel;

    @FXML
    private Label EmailLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init");
        
        String usersName = "Name: " + u.getFirstName() + " " + u.getLastName();
        String studentID = "ID: " + u.getStudentID();
        String GPA = "GPA: " + s.getGPAString();
        String major = "Major: " + s.getMajor().getName();
        String standing = "Standing: " + s.getStanding();
        String DOB = "Date of Birth:" + s.getDOB();
        String Email = "Email: " + s.generateStudentEmail();

        
        nameLabel.setText(usersName);
        StudentIDLabel.setText(studentID);
        GPALabel.setText(GPA);
        MajorLabel.setText(major);
        StandingLabel.setText(standing);
        DateofBirthLabel.setText(DOB);
        EmailLabel.setText(Email);
        displayEightSemesterPlanListView();

    }

    private void displayEightSemesterPlanListView(){
        
        ObservableList<String> eightSemesterPlan = FXCollections.observableArrayList();
        System.out.println("Size: " + eightSemesterPlan.size());
        for(Course course : s.displayEightSemesterPlan()) {
            eightSemesterPlan.add(course.getTitle());
            
        }
        //eightSemesterPlanListView = new ListView<String>(eightSemesterPlan);
        EightSemesterPlanListView.setItems(eightSemesterPlan);
    }

}
