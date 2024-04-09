import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CourseListTest {

    protected CourseList courses;

    @BeforeClass
    public void oneTimeSetup() {
    }

    @AfterClass
    public void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {
        courses = CourseList.getInstance();
    }

    @AfterEach
    public void tearDown() {
        // runs after each test
    }

    //assertEquals(val1,val2)
	//assertFalse(val)
	//assertTrue(val)
	//assertSame(val1,val2)
	//assertNotSame(val1,val2)
	//assertNull(val)
	//assertNotNull(val)

    @Test
    public void testGetByMajorValidMajor() {
        ArrayList<Course> returnVal = courses.getCoursesByMajor("Computer Science");
        assertNotNull(returnVal, "parameter 'Computer Science' should return an arrayList of Course obj");
    }

    @Test
    public void testGetByMajorInvalidMajor() {
        ArrayList<Course> returnVal = courses.getCoursesByMajor("Comp Sci");
        assertNull(returnVal, "parameter 'Comp Sci' should return null");
    }

    @Test
    public void testSearchByNameAndNumberValidInputs() {
        ArrayList<Course> returnVal = courses.searchCourseByNameAndNumber("CSCE", "");
        assertNotNull(returnVal, "parameters 'CSCE' and '' should return a value");
    }

    @Test
    public void testSearchByNameAndNumberInvalidName() {
        ArrayList<Course> returnVal = courses.searchCourseByNameAndNumber("Not a subject", "1");
        assertEquals(returnVal.size(), 0, "parameters 'Not a subject' and '1' should return an empty array list of Course objs");
    }

    @Test
    public void testSearchByNameAndNumberInvalidNumber() {
        ArrayList<Course> returnVal = courses.searchCourseByNameAndNumber("MATH", "-1");
        assertEquals(returnVal.size(), 0, "parameters 'MATH' and '-1' should return an empty array list of Course objs");
    }

    @Test
    public void testGetByUUIDvalidID() {
        Course returnVal = courses.getByUUID("d1433d4e-8c32-4edc-adba-348b5ddcc087");
        assertNotNull(returnVal, "parameter 'd1433d4e-8c32-4edc-adba-348b5ddcc087' should return a Course obj");
    }

    @Test
    public void testGetByUUIDinvalidID() {
        Course returnVal = courses.getByUUID("not an id");
        assertNull(returnVal, "parameter 'not an id' should return");
    }

}
