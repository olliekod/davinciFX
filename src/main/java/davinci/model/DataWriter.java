import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataWriter extends DataConstants {

    public static boolean saveStudents(ArrayList<Student> students) {
        JSONArray jsonStudents = new JSONArray();

        for (int i = 0; i < students.size(); i++) {
            jsonStudents.add(getStudentJSON(students.get(i)));
        }

        try {
            
            try (FileWriter file = new FileWriter(STUDENT_FILE_PATH)) {
            file.write(jsonStudents.toJSONString());
            file.flush();

            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static JSONObject getStudentJSON(Student student) {
        JSONObject studentDetails = new JSONObject();
        System.out.println(student.getUsername());
        studentDetails.put("id", String.valueOf(student.getID()));
        studentDetails.put("standing", student.getStanding());
        studentDetails.put("firstName", student.getFirstName());
        studentDetails.put("lastName", student.getLastName());
        studentDetails.put("userName", student.getUsername());
        studentDetails.put("password", student.getPassword());
        studentDetails.put("major", student.getMajor().getName());
        studentDetails.put("GPA", String.valueOf(student.getGPA()));

        //Define new JSONArray for the courses key
        // for each Student
        JSONArray coursesToAdd = new JSONArray();

        //Loop through all of the student's courses
        for (int i = 0; i < student.getCourses().size(); i++) {
            //Grabs the current course
            StudentCourse c = (student.getCourses().get(i));
            
            //If the course is taken,
            // create and fill out the JSONObject with the course's info
            if (c.getIsCompleted()) {
                JSONObject courseTaken = new JSONObject();
                courseTaken.put("courseid", String.valueOf(c.getID()));
                courseTaken.put("grade", String.valueOf(c.getGrade()));
                courseTaken.put("attempts", String.valueOf(c.getAttempts()));
                courseTaken.put("isCompleted", String.valueOf(c.getIsCompleted()));

                //Add the course object to the JSONArray
                coursesToAdd.add(courseTaken);
            }
        }
        //Finally, add the JSONArray as the value for "coursesTaken"
        studentDetails.put("coursesTaken", coursesToAdd);
        studentDetails.put("notes", student.getNotes());

        return studentDetails;
    }

    public static boolean saveFaculty(ArrayList<Faculty> faculty) {
        JSONArray jsonFaculty = new JSONArray();

        for (int i = 0; i < faculty.size(); i++) {
            jsonFaculty.add(getFacultyJSON(faculty.get(i)));
        }

        try (FileWriter file = new FileWriter(FACULTY_FILE_PATH)) {
            file.write(jsonFaculty.toJSONString());
            file.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static JSONObject getFacultyJSON(Faculty faculty) {
        JSONObject facultyDetails = new JSONObject();

        facultyDetails.put("id", String.valueOf(faculty.getID()));
        facultyDetails.put("firstName", faculty.getFirstName());
        facultyDetails.put("lastName", faculty.getLastName());
        facultyDetails.put("userName", faculty.getUsername());
        facultyDetails.put("password", faculty.getPassword());

        JSONArray assignedStudentIDs = new JSONArray();

        for(Student student : faculty.getAssignedStudents()) {
            assignedStudentIDs.add(String.valueOf(student.getID()));
        }
        
        facultyDetails.put("assignedStudents", assignedStudentIDs);

        return facultyDetails;
    }
    
    public static boolean saveCourses(ArrayList<Course> courses) {
        JSONArray jsonCourses = new JSONArray();

        for (int i = 0; i < courses.size(); i++) {
            jsonCourses.add(getCourseJSON(courses.get(i)));
        }

        try (FileWriter file = new FileWriter(COURSE_FILE_PATH)) {
            file.write(jsonCourses.toJSONString());
            file.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put("id", String.valueOf(course.getID()));
        courseDetails.put("title", course.getTitle());
        courseDetails.put("courseNumber", String.valueOf(course.getCourseNumber()));
        courseDetails.put("hours", String.valueOf(course.getHours()));
        courseDetails.put("subject", course.getSubject());

        //Had to turn the prereq list into a string for the sake of the JSON and
        // because dataloader was already written with it as a string.
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < course.getPrereqs().size(); i++) {
            str.append(course.getPrereqs().get(i)).append("&");
        }
        courseDetails.put("prereqs", str.toString());

        return courseDetails;
    }

    //SHOULD WORK
    //NOT TESTED
    public static boolean saveMajors(ArrayList<Major> majors) {
        JSONArray jsonMajors = new JSONArray();

        for (int i = 0; i < majors.size(); i++) {
            jsonMajors.add(getMajorJSON(majors.get(i)));
        }

        try (FileWriter file = new FileWriter(MAJOR_FILE_PATH)) {
            file.write(jsonMajors.toJSONString());
            file.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static JSONObject getMajorJSON(Major major) {
        JSONObject majorDetails = new JSONObject();
        majorDetails.put("id", String.valueOf(major.getID()));
        majorDetails.put("name", major.getName());
        majorDetails.put("type", major.getType());
        majorDetails.put("hours", String.valueOf(major.getHours()));

        JSONArray majorClassJSON = new JSONArray();
        JSONArray majorElectJSON = new JSONArray();

        JSONObject majorClassWithRec = new JSONObject();
        JSONObject majorElecWithRec = new JSONObject();

        HashMap<Course, Integer> majorReqs = major.getMajorRequirements();
        HashMap<Course, Integer> majorElectiveReqs = major.getElectiveCourseReqs();

        for (HashMap.Entry<Course, Integer> entry : majorReqs.entrySet()) {
            Course targetCourse = entry.getKey();
            String recSemester = String.valueOf(entry.getValue());

            majorClassWithRec.put("courseName", targetCourse.getTitle() + String.valueOf(targetCourse.getCourseNumber()));  
            majorClassWithRec.put("courseID", String.valueOf(targetCourse.getID()));
            majorClassWithRec.put("reccomendedSemester", recSemester);

            majorClassJSON.add(majorClassWithRec);
        }
        for (HashMap.Entry<Course, Integer> entry : majorElectiveReqs.entrySet()) {
            Course targetCourse = entry.getKey();
            String recSemester = String.valueOf(entry.getValue());
            
            majorElecWithRec.put("courseName", targetCourse.getTitle() + String.valueOf(targetCourse.getCourseNumber()));  
            majorElecWithRec.put("courseID", String.valueOf(targetCourse.getID()));
            majorElecWithRec.put("reccomendedSemester", recSemester); 
            majorElectJSON.add(majorElecWithRec);
        }

        majorDetails.put("majorreq", majorClassJSON);
        majorDetails.put("electivereq", majorElectJSON);

        return majorDetails;
    }
}
