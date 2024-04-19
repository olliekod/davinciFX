package davinci.controllers;
import davinci.library.App;
import davinci.model.User;
import davinci.model.UserList;
import davinci.model.Student;
import davinci.model.Major;
import davinci.model.Course;
import davinci.model.Faculty;

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
import javafx.fxml.Initializable;

public class advisorStudentSelectController implements Initializable{
    User u = App.user;
    Faculty f = App.faculty;

    @FXML
    private Button logoutButton;

    @FXML
    private Button advisorAddStudentButton;

    @FXML
    private Button advisorRemoveStudentButton;

    @FXML
    private ListView allStudentsList;

    @FXML
    private ListView myStudentsList;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        displayAllStudentListView();
        displayMyStudentListView();
    }

    public void displayAllStudentListView() {
        ObservableList<String> allStudents = FXCollections.observableArrayList();

        for(Student student : UserList.getInstance().getStudents()) {
            if(student != null){
                String name = student.getFirstName() + student.getLastName();
                allStudents.add(name);
            }
        }
        allStudentsList.setItems(allStudents);
    }

    public void displayMyStudentListView() {
        ObservableList<String> myStudents = FXCollections.observableArrayList();

        for(Student student : f.getAssignedStudents()) {
            if(student != null){
                String name = student.getFirstName() + student.getLastName();
                myStudents.add(name);
            }
        }
        myStudentsList.setItems(myStudents);
    }

    @FXML
    private void logoutButtonClicked() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void addStudentButtonClicked() throws IOException {
        allStudentsList.getItem(position);
    }
}
