package davinci.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    /**
     * FINISHED AND WORKING AS OF 2/28/2024
     * Spencer Philips and Anthony GoldHammer
     */
    public static ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<Student>();
        try {
            FileReader reader = new FileReader(STUDENT_FILE_PATH);
            JSONArray studentsJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < studentsJSON.size(); i++) {
                
                JSONObject studentJSON = (JSONObject)studentsJSON.get(i); 
                UUID id = UUID.fromString((String)studentJSON.get("id"));
                String standing = (String)studentJSON.get("standing");
                String firstName = (String)studentJSON.get("firstName"); // loading in all the data for our student obj
                String lastName = (String)studentJSON.get("lastName");
                String userName = (String)studentJSON.get("userName");   
                String password = (String)studentJSON.get("password");
                double GPA = Double.parseDouble((String)studentJSON.get("GPA"));
                Major major = MajorList.getMajorByName((String)studentJSON.get("major")); // shoudnt this not work because MajorList could be null?
                                                                                              // we might need a getInstance
                ArrayList<StudentCourse> coursesTaken = new ArrayList<StudentCourse>();
                
                
                JSONArray courseArray = (JSONArray)studentJSON.get("coursesTaken"); // here we are handling the courses
                
                for (int j = 0; j < courseArray.size(); j++) {
                    JSONObject courseJSON = (JSONObject)courseArray.get(j);
                    String courseID = (String)courseJSON.get("courseid");
                    Double courseGrade = Double.parseDouble((String)courseJSON.get("grade"));
                    int attempts = Integer.parseInt((String)courseJSON.get("attempts"));
                    boolean isCompleted = Boolean.parseBoolean((String)courseJSON.get("isCompleted"));

                    CourseList list = CourseList.getInstance();
                    Course c = list.getByUUID(courseID); // get our course information by the id

                    coursesTaken.add(new StudentCourse(c.getID(), c.getTitle(), c.getHours(),
                                                       c.getSubject(), c.getCourseNumber(),
                                                       c.getPrereqs(), isCompleted, attempts, courseGrade));
                }


                
                JSONArray notesArray = (JSONArray)studentJSON.get("notes");
                ArrayList<String> notesList = new ArrayList<String>();
                for (int j = 0; j < notesArray.size(); j++) {
                    String note = (String)notesArray.get(j);
                    notesList.add(note);
                }


                
                students.add(new Student(id, userName, password,
                            firstName, lastName, standing,
                            major, GPA, coursesTaken, notesList)); // add our new student to the returned arrayList

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * finished and not tested
     */
    public static ArrayList<Faculty> getFaculty() {
        ArrayList<Faculty> faculty = new ArrayList<Faculty>();
        try {
            FileReader reader = new FileReader(FACULTY_FILE_PATH);
            JSONArray facultyJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < facultyJSON.size(); i++) {
                JSONObject facJSON = (JSONObject)facultyJSON.get(i);
                UUID id = UUID.fromString((String)facJSON.get("id"));
                String firstName = (String)facJSON.get("firstName");
                String lastName = (String)facJSON.get("lastName");
                String userName = (String)facJSON.get("userName");
                String password = (String)facJSON.get("password");

                ArrayList<Student> studentsList = new ArrayList<Student>();
                JSONArray studentArray = (JSONArray)facJSON.get("assignedStudents"); 
                UserList list = UserList.getInstance();
                for (int j = 0; j < studentArray.size(); j++) {
                    UUID studentID = UUID.fromString((String)studentArray.get(j));
                    Student s = list.getStudentByID(studentID);
                    if (s != null)
                        studentsList.add(s);
                }

                faculty.add(new Faculty(id, userName, password, firstName, lastName, studentsList));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return faculty;
    }

    /**
     * FINISHED AND WORKING AS OF 2/26/2024
     * Spencer Philips and Anthony Goldhammer
     */
    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            FileReader reader = new FileReader(COURSE_FILE_PATH);
            JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
    
            for (int i = 0; i <coursesJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
                UUID id = UUID.fromString((String)courseJSON.get("id"));
                String title = (String)courseJSON.get("title");
    
                String courseNumberStr = (String)courseJSON.get("courseNumber");
                int courseNumber = courseNumberStr.isEmpty() ? 0 : Integer.parseInt(courseNumberStr);
    
                String hoursStr = (String)courseJSON.get("hours");
                int hours = hoursStr.isEmpty() ? 0 : Integer.parseInt(hoursStr);
    
                String subject = (String)courseJSON.get("subject");
                String prereqs = (String)courseJSON.get("prereqs");
    
                ArrayList<String> preqArrayList = new ArrayList<String>();
                for (String prereq : prereqs.split("&")) {
                    if (!prereq.isEmpty()) {
                        preqArrayList.add(prereq);
                    }
                }
                
                courses.add(new Course(id, title, hours, subject, courseNumber, preqArrayList));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return courses;
    }

    /**
     * FINISHED AND WORKING AS OF 2/27/2024
     * Spencer Philips and Anthony Goldhammer
     */
    public static ArrayList<Major> getMajors() {

        ArrayList<Major> majors = new ArrayList<Major>();
        try {
            FileReader reader = new FileReader(MAJOR_FILE_PATH);
            JSONArray majorsJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < majorsJSON.size(); i++) {
                JSONObject majorJSON = (JSONObject)majorsJSON.get(i);

                UUID id = UUID.fromString((String)majorJSON.get("id"));
                String name = (String)majorJSON.get("name");
                String type = (String)majorJSON.get("type");
                int hours = Integer.parseInt((String)majorJSON.get("hours"));
                
                JSONArray requirementsJSON = (JSONArray) majorJSON.get("majorreq");
                // for(Object o : requirementsJSON) {
                //     String courseID = (String)majorJSON.get("courseID");
                //     int reccomendedSemester = Integer.parseInt((String)majorJSON.get("reccomendedSemester"));
                // }
                HashMap<Course, Integer> requirementsList = parseCourses(requirementsJSON);
    
                JSONArray electiveRequirementsJSON = (JSONArray) majorJSON.get("electivereq");
                HashMap<Course, Integer> electiveRequirementsList = parseCourses(electiveRequirementsJSON);
               // CourseList list = CourseList.getInstance(); // here we create our instance variables to be used
                majors.add(new Major(id, name, type, hours, requirementsList, electiveRequirementsList));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return majors;
    }

    // --------- pissy caca --------------
    // private static ArrayList<Course> parseCourses(JSONArray coursesJSON) {
    //     ArrayList<Course> coursesList = new ArrayList<>();
    //     CourseList courseList = CourseList.getInstance(); // Get the singleton instance of CourseList

    //     for (Object o : coursesJSON) {
    //         String courseCode = (String) o;
    //         // first 4 characters are the subject and the last 3 are the course number
    //         String subject = courseCode.substring(0, 4);
    //         int courseNumber = Integer.parseInt(courseCode.substring(4));

    //         // Attempt to find the matching Course object
    //         Course course = courseList.getByTitleAndNumber(subject, courseNumber);
    //         if (course != null) {
    //             coursesList.add(course);
    //         }
    //     }
    //     return coursesList;
    // }

    private static HashMap<Course, Integer> parseCourses(JSONArray coursesJSON) {
        HashMap<Course, Integer> coursesList = new HashMap<>();
        CourseList courseList = CourseList.getInstance(); // Get the singleton instance of CourseList
        for (Object o : coursesJSON) {
            JSONObject courseJSON = (JSONObject) o;
            String courseID = (String) courseJSON.get("courseID");
            int reccomendedSemester = Integer.parseInt((String)courseJSON.get("reccomendedSemester"));
            // Attempt to find the matching Course object
            Course course = courseList.getByUUID(courseID);
            if (course != null) {
                coursesList.put(course, reccomendedSemester);
            }
        }
        return coursesList;
    }

}