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


public class EightSemController implements Initializable{
    User u = App.user;
    Student s = App.student;

    @FXML
    private ListView semesterOneListView;

    @FXML 
    private ListView semesterTwoListView;

    @FXML
    private ListView semesterThreeListView;

    @FXML
    private ListView semesterFourListView;

    @FXML
    private ListView semesterFiveListView;

    @FXML
    private ListView semesterSixListView;

    @FXML
    private ListView semesterSevenListView;

    @FXML
    private ListView semesterEightListView;

    @FXML
    private Button logoutButton;

    @FXML 
    private Button userInformationButton;

    @FXML
    private Button notesButton;

    @FXML
    private Button degreeProgressButton;


    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        ObservableList<String> semesterOnePlan = FXCollections.observableArrayList();
        ObservableList<String> semesterTwoPlan = FXCollections.observableArrayList();
        ObservableList<String> semesterThreePlan = FXCollections.observableArrayList();
        ObservableList<String> semesterFourPlan = FXCollections.observableArrayList();
        ObservableList<String> semesterFivePlan = FXCollections.observableArrayList();
        ObservableList<String> semesterSixPlan = FXCollections.observableArrayList();
        ObservableList<String> semesterSevenPlan = FXCollections.observableArrayList();
        ObservableList<String> semesterEightPlan = FXCollections.observableArrayList();

        for(Course course: s.displaySemesterbyInt(1)){
            if(course != null && course.getSubject().equals(""))
                semesterOnePlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterOnePlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }

        for(Course course: s.displaySemesterbyInt(2)){
            if(course != null && course.getSubject().equals(""))
                semesterTwoPlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterTwoPlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");  
        }

        //THIS WORKS
        int temp = 0;
        for(Course course: s.displaySemesterbyInt(3)){
            if(course != null && course.getSubject().equals(""))
                semesterThreePlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterThreePlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }

        for(Course course: s.displaySemesterbyInt(4)){
            if(course != null && course.getSubject().equals(""))
                semesterFourPlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterFourPlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }

        for(Course course: s.displaySemesterbyInt(5)){
            if(course != null && course.getSubject().equals(""))
                semesterFivePlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterFivePlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }

        for(Course course: s.displaySemesterbyInt(6)){
            if(course != null && course.getSubject().equals(""))
                semesterSixPlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterSixPlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }

        for(Course course: s.displaySemesterbyInt(7)){
            if(course != null && course.getSubject().equals(""))
                semesterSevenPlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterSevenPlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }

        for(Course course: s.displaySemesterbyInt(8)){
            if(course != null && course.getSubject().equals(""))
                semesterEightPlan.add(course.getTitle() + " - " + course.getHours() + " credits");
            else
                semesterEightPlan.add(course.getSubject() + course.getCourseNumber() + ": " + course.getTitle() + " - " + course.getHours() + " credits");
        }





        if(semesterOneListView == null)
            System.out.println("Semester One List View is null");
        else
            semesterOneListView.setItems(semesterOnePlan);

        if(semesterTwoListView == null)
            System.out.println("Semester Two List View is null");
        else
            semesterTwoListView.setItems(semesterTwoPlan);

        if(semesterThreeListView == null)
            System.out.println("Semester Three List View is null");
        else
            semesterThreeListView.setItems(semesterThreePlan);

        if(semesterFourListView == null)
            System.out.println("Semester Four List View is null");
        else
            semesterFourListView.setItems(semesterFourPlan);

        if(semesterFiveListView == null)
            System.out.println("Semester Five List View is null");
        else
            semesterFiveListView.setItems(semesterFivePlan);

        if(semesterSixListView == null)
            System.out.println("Semester Six List View is null");
        else
            semesterSixListView.setItems(semesterSixPlan);

        if(semesterSevenListView == null)
            System.out.println("Semester Seven List View is null");
        else
            semesterSevenListView.setItems(semesterSevenPlan);

        if(semesterEightListView == null)
            System.out.println("Semester Eight List View is null");
        else
            semesterEightListView.setItems(semesterEightPlan);


    }

    @FXML
    private void logoutButtonClicked() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void userInformationButtonClicked() throws IOException {
        System.out.println("User Information Button Clicked");
        App.setRoot("studentScreen");
    }

    @FXML
    private void notesButtonClicked() throws IOException {
        System.out.println("Notes Button Clicked");
        App.setRoot("studentNotes");
    }
    
    @FXML
    private void degreeProgressButtonClicked() throws IOException {
        System.out.println("Degree Progress Button Clicked");
        App.setRoot("DegreeProgress");
    }

}
