package davinci.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.UUID;

public class Major {
    private UUID id;
    private String name;
    private String type;
    private int hours;
    private HashMap<Course, Integer> majorRequirements;
    private HashMap<Course, Integer> electiveCourseReq;

    public Major(UUID id, String name, String type, int hours, HashMap<Course, Integer> majorReqs, HashMap<Course, Integer> electiveReqs) {
        setID(id);
        setName(name);
        setType(type);
        setHours(hours);
        setMajorRequirements(majorReqs);
        setElectiveCourseReqs(electiveReqs);
    }

    public int getHours() {
        return this.hours;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }

    public UUID getID() {
        return this.id;
    }

    private void setID(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<Course, Integer> getMajorRequirements() {
        return this.majorRequirements;
    }

    private void setMajorRequirements(HashMap<Course, Integer> reqs) {
        this.majorRequirements = reqs;
    }

    public HashMap<Course, Integer> getElectiveCourseReqs() {
        return this.electiveCourseReq;
    }

    private void setElectiveCourseReqs(HashMap<Course, Integer> reqs) {
        this.electiveCourseReq = reqs;
    }
}
