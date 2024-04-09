package davinci.model;

import java.util.ArrayList;
import java.util.UUID;

public class MajorList {
    private static MajorList majorList;
    private static ArrayList<Major> majors = new ArrayList<Major>();

    public MajorList() {

    }

    public ArrayList<Major> getMajors() {
        return majors;
    }   

    public static void setMajors(ArrayList<Major> majorsList) {
        majors = majorsList;
    }

    public static MajorList getInstance() {
        if (majorList == null) {
            majorList = new MajorList();
            setMajors(DataLoader.getMajors());
        }
        return majorList;
    }

    public static Major getMajorByName(String major) {
        majors = DataLoader.getMajors();
        for (Major m : majors) {
            if (m.getName().equals(major)) {
                return m;
            }
        }
        return null;
    }

    public static void addMajortoMajorList(Major major) {
        majors.add(major);
    }

    /**
     * @param String
     * @return major
     */
    public static Major getByUUID(String id) {
        for (Major m : majors) {
            if (m.getID().toString() == id)
                return m;
        }
        return null;
    }
}
