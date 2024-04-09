import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private int hours;
    private String subject;
    private int courseNumber;
    private ArrayList<String> prereqs;
    private String title;

    public Course(UUID id, String title, int hours, String subject, int coursenumber, ArrayList<String> prereqs){
        this.id = id;
        this.title = title;
        this.hours = hours;
        this.subject = subject;
        this.courseNumber = coursenumber;
        this.prereqs = prereqs;
    }

    public ArrayList<String> getPrereqs(){
        return this.prereqs;
    }

    public void setPrereqs(ArrayList<String> prereqs){
        this.prereqs = prereqs;
    }

    public int getCourseNumber(){
        return this.courseNumber;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseNumber(int coursenumber){
        this.courseNumber = coursenumber;
    }

    public String getSubject(){
        return this.subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public int getHours(){
        return this.hours;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    public UUID getID(){
        return this.id;
    }




}
