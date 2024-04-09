import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
    
    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void tearDown() {
        // runs after each test
    }

    @Test
    public void testGetUser() {
        UserList userList = UserList.getInstance();
        UUID id = UUID.randomUUID();
        Student student = new Student(id, "Goldhama", "fortnite", "Anthony", "Goldhammer", "sophmore", MajorList.getMajorByName("Computer Science"), 4.0, new ArrayList<StudentCourse>(), new ArrayList<String>());
        userList.addStudent(student);
        assertEquals(student, userList.getUser("Goldhama", "fortnite"));
    }

    @Test
    public void getStudentByIDTest() {
        UserList userList = UserList.getInstance();
        UUID id = UUID.randomUUID();
        Student student = new Student(id, "Goldhama", "fortnite", "Anthony", "Goldhammer", "sophmore", MajorList.getMajorByName("Computer Science"), 4.0, new ArrayList<StudentCourse>(), new ArrayList<String>());
        userList.addStudent(student);
        assertEquals(student, userList.getStudentByID(id));
    }

    @Test
    public void getFacultyByIDTest() {
        UserList userList = UserList.getInstance();
        UUID id = UUID.randomUUID();
        Faculty faculty = new Faculty(id, "Goldhama", "fortnite", "Anthony", "Goldhammer", new ArrayList<Student>());
    }

    @Test
    public void testgetStudents() {
        UserList userList = UserList.getInstance();
        UUID id = UUID.randomUUID();
        Student student = new Student(id, "Goldhama", "fortnite", "Anthony", "Goldhammer", "sophmore", MajorList.getMajorByName("Computer Science"), 4.0, new ArrayList<StudentCourse>(), new ArrayList<String>());
        userList.addStudent(student);
        ArrayList<Student> stu = userList.getStudents();
        ArrayList<Student> students = new ArrayList<Student>();
        
        students.add(student);
        assertEquals(stu, userList.getStudents());
    }

    @Test
    public void testgetFaculty() {
        UserList userList = UserList.getInstance();
        UUID id = UUID.randomUUID();
        Faculty faculty = new Faculty(id, "Goldhama", "fortnite", "Anthony", "Goldhammer", new ArrayList<Student>());
        userList.addFaculty(faculty);
        ArrayList<Faculty> fac = userList.getFaculty();
        ArrayList<Faculty> facultyList = new ArrayList<Faculty>();
        facultyList.add(faculty);
        assertEquals(fac, userList.getFaculty());
    }
}
