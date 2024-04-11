package davinci.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Comparator;

public class Student extends User{
    private String standing;
    private Major major;
    private double GPA;
    private ArrayList<StudentCourse> courses;
    private ArrayList<String> notes;

    /**
     *Constructor for Student Class
     * @param standing Current Year
     * @param major Student Major
     * @param GPA current GPA of Student
     * @param courses List of all courses needed for student
     * @param currentCourses list of current courses being taken
     * @param notes notes from advisor 
     */
    public Student(UUID id, String username, String password, String firstName,
     String lastName, String standing, Major major, double GPA,
      ArrayList<StudentCourse> courses,  ArrayList<String> notes) {
        super(id, username, password, firstName, lastName);
                                                            
        this.setMajor(major);
        this.setGPA(GPA);                               // jackson - remember to use the setters in the constructor
        this.setCourses(courses);                // you also need to add the parameters (id, username, password, firstName, lastName)
                                         // in order for the super() command to work and change the uml to reflact that. thank u - spencer
        this.setGPA(GPA);
        this.setNotes(notes);
        this.setStanding(standing);
    }

    public ArrayList<Course> displayEightSemesterPlan() {
        ArrayList<Course> plan = new ArrayList<Course>();
        HashMap<Course, Integer> majorReqs = major.getMajorRequirements();
        HashMap<Course, Integer> electiveReqs = major.getElectiveCourseReqs();
        majorReqs.putAll(electiveReqs);
         

        List<Map.Entry<Course, Integer>> entryList = new ArrayList<>(majorReqs.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Course, Integer>>() {
            @Override
            public int compare(Map.Entry<Course, Integer> o1, Map.Entry<Course, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        ArrayList<Course> sortedMajorReqs = new ArrayList<>();
        for(Map.Entry<Course, Integer> entry : entryList) {
            sortedMajorReqs.add(entry.getKey());
        }

        plan = sortedMajorReqs;
        return plan;
    }
    
    @Override
    public UUID getID() {
        return super.getID();
    }

    /**
     * only returns false as of 2/29
     */
    public boolean meetsPrereqs() {


        return false;
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getPassword() {
        return super.getPassword();
    }
    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public String getStanding() {
        return this.standing;
    }

    public Major getMajor() {
        return this.major;
    }

    public double getGPA() {
        return this.GPA;
    }

    public String getGPAString() {
        return String.valueOf(this.GPA);
    }

    public ArrayList<StudentCourse> getCourses() {
        return this.courses;
    }

    public ArrayList<String> getNotes() {
        return this.notes;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
    
    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public void setCourses(ArrayList<StudentCourse> courses) {
        this.courses = courses;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public static String getDOB() {
        Random random = new Random();
        
        int year = random.nextInt(5) + 2000; 
        int month = random.nextInt(12) + 1;
        
        
        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };
        
        
        if (month == 2 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
            daysInMonth[1] = 29;
        }
        
        int day = random.nextInt(daysInMonth[month - 1]) + 1;
        
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public String generateStudentEmail() {
        Random random = new Random();
        
        // Extract the first letter of the first and last names
        char firstInitial = this.getFirstName().charAt(0);
        char lastInitial = this.getLastName().charAt(0);
        
        // Generate two random digits
        int randomNumber = random.nextInt(90) + 10; // Ensures a two-digit number
        
        // Format and return the email address
        return String.format("%c%c%d@email.sc.edu", firstInitial, lastInitial, randomNumber).toLowerCase();
    }
}
