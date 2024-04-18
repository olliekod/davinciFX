package davinci.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Faculty extends User{
    private ArrayList<Student> assignedStudents = new ArrayList<Student>();

    public Faculty(UUID id, String username, String password, String firstName, String lastName, ArrayList<Student> students) {
        super(id, username, password, firstName, lastName);
        this.setAssignedStudents(students);
    }

    public void addCourse(HashMap<UUID, String> courses) {
        
    }
    public void removeCourse(ArrayList<Course> courses, Course course) {

    }

    public void editCourse(ArrayList<Course> courses, String category, String description) {

    }
    
    public void addStudent(Student student){
        assignedStudents.add(student);
        DataWriter.saveFaculty(UserList.getFaculty());
    }
    public void removeStudent(UUID id) {
        // Remove student as advisee
    }
    public void editStudent(Student student) {
        // This function is kinda vague.
        // Might want to break this up into smaller functions?
    }

    public void setAssignedStudents(ArrayList<Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    public ArrayList<Student> getAssignedStudents() {
        return assignedStudents;
    }

    public void addNote(Student student, String note) {
        ArrayList<String> notes = student.getNotes();
        notes.add(note);
        student.setNotes(notes);
        DataWriter.saveStudents(UserList.getStudents());
    }

    public String generateFacultyEmail() {
        Random random = new Random();
        
        char firstInitial = this.getFirstName().charAt(0);
        char lastInitial = this.getLastName().charAt(0);
        
        int randomNumber = random.nextInt(90) + 10; 
        
        return String.format("%c%c%d@email.sc.edu", firstInitial, lastInitial, randomNumber).toLowerCase();
    }
}
