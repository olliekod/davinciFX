package davinci.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import davinci.library.App;
import davinci.model.Faculty;
import davinci.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class advisorScreenController implements Initializable {


    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button myStudentsButton;

    @FXML
    private Button homeButton;

    @FXML
    private ListView<String> studentView;

    private Faculty advisor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        advisor = App.faculty;
        
        nameLabel.setText(advisor.getFirstName() + " " + advisor.getLastName());
        emailLabel.setText(advisor.generateFacultyEmail());
        idLabel.setText(advisor.getID().toString());

        ObservableList<String> studentList = FXCollections.observableArrayList();
        for (Student student : advisor.getAssignedStudents()) {
            studentList.add(student.getFirstName() + " " + student.getLastName());
        }

        studentView.setItems(studentList);

    }

    @FXML
    private void logoutButtonClicked() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void homeButtonClicked() throws IOException {
        App.setRoot("advisorScreen");
    }

    @FXML
    private void myStudentsButtonClicked() throws IOException {
        App.setRoot("advisorStudents");
    }
}


