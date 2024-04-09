package davinci.model;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private static ArrayList<Student> students = new ArrayList<Student>();
    private static ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    
    private UserList(){
        
    }

    public static UserList getInstance(){
        if (userList == null) {
            userList = new UserList();
            students = DataLoader.getStudents();
            faculty = DataLoader.getFaculty();
        }
        return userList;
    }


    /**
     * getUser method checks both arraylist of students and faculty to see if the user \
     * exists by Username and password
     * @param userName takes in a username
     * @param password takes in a password 
     * @return If the user is found, it returns said user. If not, it returns null. 
     */
    public User getUser(String userName, String password){
        for(int i = 0; i < students.size(); i++){
            User user = students.get(i);
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        for(int i = 0; i < faculty.size(); i++){
            User user = faculty.get(i);
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    /**
     * getUser method checks both arraylist of students and faculty to see if the user 
     * exists by UUID
     * @param id takes in a UUID
     * @return user if UUID matches, null if not.
     */
    public User getUser(UUID id){
        for(int i = 0; i < students.size(); i++){
            User user = students.get(i);
            if(user.getID().equals(id)){
                return user;
            }
        }
        for(int i = 0; i<faculty.size(); i++){
            User user = faculty.get(i);
            if(user.getID().equals(id)){
                return user;
            }
        }
        return null;
    }

    public static Student getStudentByID(UUID id) {              //I don't believe this method isnt neccesary, given getUser(UUID id)  
         for (int i = 0; i < students.size(); i++)  {      //does the same thing.
             Student s = students.get(i);
             if (s.getID().equals(id))
                 return s;
         }
         return null;
    }

    public static Faculty getFacultyByID(UUID id) {
        for (int i = 0; i < faculty.size(); i++) {
            Faculty f = faculty.get(i);
            if (f.getID().equals(id))
                return f;
        }
        return null;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void addFaculty(Faculty faculty){
        this.faculty.add(faculty);
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
    public static ArrayList<Faculty> getFaculty() {
        return faculty;
    }

    public void setInstance(UserList userList){
        UserList.userList = userList;
    }
}
