import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Facade {
    private User currentUser;
    private UserList users = UserList.getInstance();
    private CourseList courses = CourseList.getInstance();
    private MajorList majors = MajorList.getInstance();

    public Facade() {
        ProgramFacade();
    }

    public void ProgramFacade() {
        
    }

    public ArrayList<StudentCourse> getStudentCourses() {
        return null;
    }

    public User createAccount(UUID id, String userName, String password, String firstName, String lastName) {
        return null;
    }

    public boolean login(String userName, String password) {
       if(users.getUser(userName, password) != null) {
           currentUser = users.getUser(userName, password);
           return true;
       } else {
            currentUser = null;
            return false;
       }
    }

    public Course addCourse(UUID id, int hours, String subject, int courseNumber, ArrayList<String> prereqs) {
        return null;
    }

    public void removeCourse(String courseName) {

    }

    public ArrayList<Course> getEightSemesterPlan(UUID id) {
        
        Student temp = users.getStudentByID(id);
        System.out.println(temp.getMajor().getName());
        return temp.displayEightSemesterPlan();
    }

    public ArrayList<Major> getMajors() {
        return majors.getMajors();
    }

    public void whatIf() {
        
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User u) {
        this.currentUser = u;
    }

    public String formattedStudentCourses(Student s, boolean isCompleted) {
        String returnString = "";

        // will only be called after the user has logged into student so we will handle the user as one
        ArrayList<StudentCourse> studentCourses = s.getCourses();
        for (StudentCourse c : studentCourses) {
            
            if (isCompleted && !c.getIsCompleted()) {
                continue;
            } else if (!isCompleted && c.getIsCompleted()) {
                continue;
            }

            returnString += "----- " + c.getTitle() + " (" + c.getSubject() + c.getCourseNumber()+ ")\n";
            returnString += "\n";
            returnString += "   GPA: " + c.getGrade() + "(" + c.getLetterGrade() + ")\n";
            returnString += "   Hours: " + c.getHours() + "\n";
            returnString += "\n";
        }

        return returnString;
    }


    public String formattedStudentCoursesLeft(Student student) {
        String returnString = "";

        // will only be called after the user has logged into student so we will handle the user as one
        Student currentStudent = student;
        ArrayList<StudentCourse> studentCourses = currentStudent.getCourses();
        Major compSci = MajorList.getMajorByName("Computer Science");

        HashMap<Course, Integer> majorReqs = compSci.getMajorRequirements();
        HashMap<Course, Integer> elecReqs = compSci.getElectiveCourseReqs();

        ArrayList<Course> coursesNotTakenMajor = new ArrayList<Course>();

        // check to see if it is in students course list
        for (Course c : majorReqs.keySet()) {
            Boolean courseFound = false;
            for (Course s : studentCourses) {
                if (c.getID().equals(s.getID())) {
                    courseFound = true;
                    break;
                }
            }
            if (!courseFound) {
                coursesNotTakenMajor.add(c);
            }
        }

        // check to see if it is in students course list
        for (Course c : elecReqs.keySet()) {
            Boolean courseFound = false;
            for (Course s : studentCourses) {
                if (c.getID().equals(s.getID())) {
                    courseFound = true;
                    break;
                }
            }
            if (!courseFound) {
                coursesNotTakenMajor.add(c);
            }
        }

        // now we've handled our information lets format the string

        for (Course c : coursesNotTakenMajor) {
            returnString += "----- " + c.getTitle() + " (" + c.getSubject() + c.getCourseNumber() + ")\n";
            returnString += "\n";
            returnString += "   Hours: " + c.getHours() + "\n";
            returnString += "\n";
        }

        return returnString;
    }


	public String formattedStudentEightSemesterPlan() {
        // will only be called after the user has logged into student so we will handle the user as one
        String returnString = "@@@@@@@@@@@@@@@@@@@ EIGHT-SEMESTER PLAN @@@@@@@@@@@@@@@@@@@\n";
        Student currentStudent = users.getStudentByID(this.currentUser.getID());
        ArrayList<Course> sortedCourseList = currentStudent.displayEightSemesterPlan();

        for (Course c : sortedCourseList) {
            returnString += c.getTitle() + "\n";
        }

		return returnString;
	}

        public void writeEightSemesterPlanToFile() {
        // Call the method that formats the eight-semester plan
        String plan = formattedStudentEightSemesterPlan();
        
        // Specify the output file name
        String fileName = "output.txt";
        
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println(plan);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }


}
