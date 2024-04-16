package davinci.controllers;

import davinci.library.App;
import davinci.model.Course;
import davinci.model.DataWriter;
import davinci.model.Major;
import davinci.model.MajorList;
import davinci.model.Student;
import davinci.model.StudentCourse;
import davinci.model.UserList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private void switchToPrimary() throws IOException {

        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();


        if (username.length() == 0 || password.length() == 0 || firstName.length() == 0 || lastName.length() == 0) {
            System.out.println("password length is incorrect");
            App.setRoot("login");
            return;
        }

        Major compSci = MajorList.getMajorByName("Computer Science");
        UUID newID = UUID.randomUUID();
         
        Student student = new Student(newID, username, password, firstName,
         lastName, lastName, compSci, 0, new ArrayList<StudentCourse>(), new ArrayList<String>());

         UserList users = UserList.getInstance();
         ArrayList<Student> allStudents = users.getStudents();
         allStudents.add(student);
         DataWriter.saveStudents(allStudents);
         System.out.println("success");

        App.setRoot("login");
    }
}