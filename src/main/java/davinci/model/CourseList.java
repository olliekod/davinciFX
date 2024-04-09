import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    private static CourseList courseList;
    private static ArrayList<Course> courses = new ArrayList<Course>();

    private static void CourseList() {}

    /**
     * if 'this' is null then it loads the courses
     * returns itself
     */
    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
            loadCourses();
        }
        return courseList;
    }
    
    private static void loadCourses() {
        courses = DataLoader.getCourses();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * returns an array list of courses
     * number does not have to be of length 3 - returns any matches for the amount of digits provided
     */
    public static ArrayList<Course> searchCourseByNameAndNumber(String nameParam, String numberParam) {
        ArrayList<Course> searchResult = new ArrayList<Course>();
        for (Course c : courses) {
            if (c.getSubject().equals(nameParam)) {
                String[] cNumber = String.valueOf(c.getCourseNumber()).split("");
                String[] numberParamArr = numberParam.split("");
                boolean isMatch = true;
                for (int i = 0; i < numberParam.length(); i++) {
                    if (cNumber[i].equals(numberParamArr[i]) == false) {
                        isMatch = false;
                    }
                }
                if (isMatch) {
                    searchResult.add(c);
                }
            }
        }
        return searchResult;
    }

    public static ArrayList<Course> searchCourseByNameAndNumber(String[] nameParams, String numberParam) {
        ArrayList<Course> finalResult = new ArrayList<Course>();
        for (int i = 0; i < nameParams.length; i++) {
            ArrayList<Course> currentResult = searchCourseByNameAndNumber(nameParams[i], numberParam);
            for (Course c : currentResult)
                finalResult.add(c);
        }
        return finalResult;
    }

    /**
     * returns an unsorted list of major reqs and elective reqs
     * input string is case sensitive
     */
    public ArrayList<Course> getCoursesByMajor(String major) {
        ArrayList<Course> majorCourses = new ArrayList<Course>();
        Major currentMajor = MajorList.getMajorByName(major);
        
        for (Course c : currentMajor.getMajorRequirements().keySet())
            majorCourses.add(c);

        for (Course c : currentMajor.getElectiveCourseReqs().keySet())
            majorCourses.add(c);

        return majorCourses;
    }

    /**
     * iterates through courses and returns if there is a match
     * if there is no match then it return null
     * @param String
     * @return Course
     */
    public Course getByUUID(String id) {
        for (Course c : courses) {
            if (c.getID().toString().equals(id))
                return c;
        }
        return null;
    }

    public Course getByUUID(UUID id) {
        return this.getByUUID(id.toString());
    }

}
