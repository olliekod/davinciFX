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
import javafx.scene.control.ChoiceBox;
import java.util.ArrayList;
import javafx.fxml.Initializable;

public class advisorStudentSelectController implements Initializable{
    User u = App.user;
    Faculty f = App.faculty;

    //These need to stay here so they can be edited by the add/remove student buttons.
    ObservableList<String> myStudents = FXCollections.observableArrayList();
    ObservableList<String> allStudents = FXCollections.observableArrayList();


    @FXML
    private Button logoutButton;

    @FXML
    private Button advisorAddStudentButton;

    @FXML
    private Button advisorRemoveStudentButton;

    @FXML
    private ChoiceBox allStudentsChoiceBox;
    
    @FXML
    private ChoiceBox myStudentsChoiceBox;

    @FXML
    private ListView advisorAllStudents;

    @FXML
    private ListView advisorMyStudents;

    @FXML
    private Button homeButton;

    UserList userList = UserList.getInstance();
    ArrayList<Student> myStudentsList = f.getAssignedStudents();
    ArrayList<Student> allStudentsList = userList.getStudents();
    


    @Override
    public void initialize(URL url, ResourceBundle rb)  {

        //display all student list view
        //--------------------------------
        for(Student student : allStudentsList) {
            if(student != null){
                String name = student.getFirstName() + " " + student.getLastName();
                allStudents.add(name);
            }
        }
        if(advisorAllStudents == null) {
            System.out.println("allStudentsList is null");
        }
        else {
            advisorAllStudents.setItems(allStudents);
        }

        if(allStudentsChoiceBox == null) {
            System.out.println("allStudentsChoiceBox is null");
        }
        else {
            allStudentsChoiceBox.setItems(allStudents);
        }


        //display my studeny list view 
        //----------------------------------------
        for(Student student : myStudentsList) {
            if(student != null){
                String name = student.getFirstName() + " " + student.getLastName();
                myStudents.add(name);
            }
        }
        if(advisorMyStudents == null) {
            System.out.println("allStudentsList is null");
        }
        else {
            advisorMyStudents.setItems(myStudents);
        }

        if(myStudentsChoiceBox == null) {
            System.out.println("allStudentsChoiceBox is null");
        }
        else {
            myStudentsChoiceBox.setItems(myStudents);
        }
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
     private void addStudentButtonClicked() throws IOException {
        //Idk if this string cast will work or not. 
        //Can't get past the error above in displayAllStudentListView()
            myStudents.add((String)allStudentsChoiceBox.getValue());
            myStudentsChoiceBox.setItems(myStudents);
            for(Student student : allStudentsList){
                if(allStudentsChoiceBox.getValue().equals(student.getFirstName() + " " + student.getLastName())){
                    f.addStudent(student);
                }
            }
            
     }
    
     @FXML
     private void removeStudentButtonClicked() throws IOException {
         //Idk if this string cast will work or not. 
         //Can't get past the error above in displayAllStudentListView().
         myStudents.remove((String)myStudentsChoiceBox.getValue());
         myStudentsChoiceBox.setItems(myStudents);

         for(int i = 0; i < myStudentsList.size(); i++){
             if(myStudentsChoiceBox.getValue().equals(myStudentsList.get(i).getFirstName() + " " + myStudentsList.get(i).getLastName())){
                 f.removeStudent(myStudentsList.get(i));
             }
        }
     }
}
