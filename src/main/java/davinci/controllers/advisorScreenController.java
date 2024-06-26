package davinci.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import davinci.library.App;
import davinci.model.DataWriter;
import davinci.model.Faculty;
import davinci.model.Student;
import davinci.model.UserList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class advisorScreenController implements Initializable {


    private Student selectedStudent;

    @FXML
    private Label selectedStudentLabel;

    @FXML
    private Button sendNoteButton;

    @FXML
    private TextField noteTextField;

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
    private ListView<Student> studentView;

    private Faculty advisor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        advisor = App.faculty;
        
        nameLabel.setText(advisor.getFirstName() + " " + advisor.getLastName());
        emailLabel.setText(advisor.generateFacultyEmail());
        idLabel.setText(advisor.getID().toString());

        ObservableList<Student> studentList = FXCollections.observableArrayList();
        for (Student student : advisor.getAssignedStudents()) {
            studentList.add(student);
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

    @FXML
    private void studentViewPressed() throws IOException {
        selectedStudent = studentView.getSelectionModel().getSelectedItem();
        selectedStudentLabel.setText(selectedStudent.toString());
    }

    @FXML
    private void sendNoteButtonClicked() throws IOException {
        String note = noteTextField.getText();
        System.out.println(note);
        if (note.length() == 0)
            return;
        
        ArrayList<Student> students = UserList.getStudents();
        students.remove(selectedStudent);

        ArrayList<String> selectedStudentNotes = selectedStudent.getNotes();
        selectedStudentNotes.add(note);
        selectedStudent.setNotes(selectedStudentNotes);

        students.add(selectedStudent);
        DataWriter.saveStudents(students);
    }
}