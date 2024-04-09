import java.util.ArrayList;
import java.util.UUID;

public class StudentCourse extends Course {
    private boolean isCompleted;
    private int classAtempts;
    private double grade;

    public StudentCourse(UUID id, String title, int hours, String subject,
                         int courseNumber, ArrayList<String> prereqs, boolean isCompleted,
                          int classAtempts, double grade){
        super(id, title, hours, subject, courseNumber, prereqs);
        this.setIsCompleted(isCompleted);
        this.setClassAttempts(classAtempts);
        this.setGrade(grade);
    }

    public void setIsCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public void setClassAttempts(int attempts) {
        this.classAtempts = attempts;
    }

    public void setGrade(double grade){
        if (grade < 0.0 || grade > 100.0)
            System.out.println("THAT GRADE IS NOT POSSIBLE");
        this.grade = grade;
    }

    public double getGrade() {
        return this.grade;
    }

    public int getAttempts() {
        return this.classAtempts;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public String getLetterGrade() {
        if (this.grade >= 90) {
            return "A";
        }
        else if (this.grade >= 80) {
            return "B";
        }
        else if (this.grade >= 70) {
            return "C";
        }
        else if (this.grade >= 60) {
            return "D";
        }
        else {
            return "F";
        }
    }



}
