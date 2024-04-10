package davinci.controllers;

import davinci.library.App;
import davinci.model.User;
import davinci.model.Student;
import davinci.model.Major;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentScreenController implements Initializable {

    @FXML
    private Label nameLabel;

    @FXML
    private Label StudentIDLabel;

    @FXML
    private Label GPALabel;

    @FXML
    private Label MajorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init");
        User u = App.user;
        Student s = App.student;
        String usersName = "Name: " + u.getFirstName() + " " + u.getLastName();
        String studentID = "ID: " + u.getStudentID();
        String GPA = "GPA: " + s.getGPAString();
        String major = "Major: " + s.getMajor().getName();
        nameLabel.setText(usersName);
        StudentIDLabel.setText(studentID);
        GPALabel.setText(GPA);
        MajorLabel.setText(major);

    }

}
