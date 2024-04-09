import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.UUID;

public class DataWriterTest {
    Student sampleStudent;
    Faculty sampleFaculty;
    Major sampleMajor;
    Course sampleCourse;

    @BeforeAll
    public void setupStudent() {
        sampleStudent = DataLoader.getStudents().get(0);
    }

    @BeforeAll
    public void setupFaculty() {
        sampleFaculty = DataLoader.getFaculty().get(0);
    }
    @BeforeAll
    public void setupMajor() {
        sampleMajor = DataLoader.getMajors().get(0);
    }
    @BeforeAll
    public void setupCourse() {
        sampleCourse = DataLoader.getCourses().get(0);
    }
    
// TESTS

    @Test
    public void testSaveStudents() {
        ArrayList<Student> students = DataLoader.getStudents();
        // Call the method and assert result
        assertTrue(DataWriter.saveStudents(students));
    }

    @Test
    public void testGetStudentJSON() {
        JSONObject studentJSON = DataWriter.getStudentJSON(sampleStudent);
        assertNotNull(studentJSON);
    }

// FACULTY TESTING-------------------------------------------------

    @Test
    public void testSaveFaculty() {
        ArrayList<Faculty> faculty = DataLoader.getFaculty();
        assertTrue(DataWriter.saveFaculty(faculty));
    }

    @Test
    public void testGetFacultyJSON() {
        JSONObject facultyJSON = DataWriter.getFacultyJSON(sampleFaculty);
        assertNotNull(facultyJSON);
    }

// COURSE TESTING--------------------------------------------------

    @Test
    public void testSaveCourses() {
        ArrayList<Course> courses = DataLoader.getCourses();
        assertTrue(DataWriter.saveCourses(courses));
    }

    @Test
    public void testGetCourseJSON() {
        JSONObject courseJSON = DataWriter.getCourseJSON(sampleCourse);
        assertNotNull(courseJSON);
    }

// MAJOR TESTING---------------------------------------------------

    @Test
    public void testSaveMajors() {
        ArrayList<Major> majors = DataLoader.getMajors();
        assertTrue(DataWriter.saveMajors(majors));
    }

    @Test
    public void testGetMajorJSON() {
        JSONObject majorJSON = DataWriter.getMajorJSON(sampleMajor);
        assertNotNull(majorJSON);
    }
}
