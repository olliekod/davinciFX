import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentCourseTest {
    
    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void tearDown() {
        // runs after each test
    }

    @Test
    public void testGetCourses() {
        ArrayList<StudentCourse> courses = new ArrayList<StudentCourse>();
        ArrayList<String> notes = new ArrayList<String>();
        UUID id = UUID.randomUUID();
        Major major = MajorList.getMajorByName("Computer Science");
        Student student = new Student( id, "Goldhama", "fortnite", "Anthony", "Goldhammer", "Sophmore", major, 3.9, courses, notes);
        assertEquals(courses, student.getCourses());
    }

    @Test
    public void testgetLetterGradeValid(){
        
        UUID id = UUID.randomUUID();
        
        StudentCourse course = new StudentCourse(id, "CSCE 156", 3, "Computer Science", 156, new ArrayList<String>(), true, 1, 90.0);
        assertEquals("A", course.getLetterGrade());
    }

    @Test
    public void testgetLetterGradeInvalid(){
        
        UUID id = UUID.randomUUID();
        
        StudentCourse course = new StudentCourse(id, "CSCE 156", 3, "Computer Science", 156, new ArrayList<String>(), true, 1, 79.9);
        assertEquals("B", course.getLetterGrade());
    }
}
