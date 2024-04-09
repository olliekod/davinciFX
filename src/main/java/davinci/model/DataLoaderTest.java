import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DataLoaderTest {
    private UserList users = UserList.getInstance();
    private ArrayList<Student> userList = users.getStudents();

    @BeforeEach
    public void setup() {
        userList.clear();
        ArrayList<StudentCourse> courses = new ArrayList<StudentCourse>();
        ArrayList<String> notes = new ArrayList<String>();
        UUID id = UUID.randomUUID();
        Major major = MajorList.getMajorByName("Computer Science");
        userList.add(new Student( id, "Goldhama", "fortnite", "Anthony", "Goldhammer", "Sophmore", major, 3.9, courses, notes));
        DataWriter.saveStudents(userList);
    }

    @AfterEach
    public void tearDown() {
        userList.clear();
        DataWriter.saveStudents(userList);
    }

    @Test
    public void testgetStudents() {
        userList = DataLoader.getStudents();
        assertEquals(3, userList.size());
    }

    @Test
    public void testgetMajors() {
        ArrayList<Major> majorList = DataLoader.getMajors();
        assertEquals(2, majorList.size());
    }

    @Test
    public void testgetfaculty() {
        ArrayList<Faculty> facultyList = DataLoader.getFaculty();
        assertEquals(3, facultyList.size());
    }

    @Test
    public void testgetCourse() {
        ArrayList<Course> courseList = DataLoader.getCourses();
        assertEquals(73, courseList.size());
    }


}
