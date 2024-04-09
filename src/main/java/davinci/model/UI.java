import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UI {
    private Scanner scanner;
    private Facade facade;

    // COURSE LIST MUST BE INITIALIZED BEFORE WE LOAD ANY STUDENTS WHAT SO EVER

    public UI() {
        this.scanner = new Scanner(System.in);
        this.facade = new Facade(); 
    }

    public void run() {
        while (true) {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("Enter 1 to create an account Enter 2 to login ('q' to quit): ");
            String choice = this.scanner.nextLine();
            if (choice.equals("1")) {
                createAccountScreen();
            } else if (choice.equals("2")) {
                loginScreen();
                break;
            } else if (choice.equals("q")) {
                break;
            }
        }
        
         // comment this line out if you don't want the app to run
    }

    public void loginScreen() {

        System.out.print("\033[H\033[2J");  
        System.out.flush();
        while (this.facade.getCurrentUser() == null) {
            System.out.println("-----DaVinci Academia-----");
            System.out.println("Enter username: ");
            String enteredUsername = scanner.nextLine();

            if (enteredUsername.equals("q"))
                System.exit(0);;

            System.out.println("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (this.facade.login(enteredUsername, enteredPassword)) {
                break;
            } else {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("log in information was incorrected please try again");
            }
            
        }

        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("logging in");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep((int)(Math.random() * 300));
            } catch (Exception e) {}
            System.out.print("..");
        }

        System.out.print("\033[H\033[2J");  
        System.out.flush();

        String govName = this.facade.getCurrentUser().getFirstName() + " " + this.facade.getCurrentUser().getLastName();
        System.out.println("logged in as " + govName);

        if (this.facade.getCurrentUser() instanceof Student)
            studentScreen();
        if (this.facade.getCurrentUser() instanceof Faculty)
            facultyScreen();
    }

    public void studentScreen() {
        Student currentStudent = UserList.getStudentByID(this.facade.getCurrentUser().getID());
        while (true) {
            System.out.println("Please choose an option to display: ");
            System.out.println("1. View my current courses ");
            System.out.println("2. View my past courses ");
            System.out.println("3. View uncompleted major courses ");
            System.out.println("4. Search for a course ");
            System.out.println("5. View my eight-semester plan ");
            System.out.println("q. logout");
            System.out.println("\n\n\nEnter choice: ");

            String choice = this.scanner.nextLine();

            System.out.print("\033[H\033[2J");  
            System.out.flush();

            if (choice.equals("1")) {
                System.out.println(this.facade.formattedStudentCourses(currentStudent, false));
            }
            if (choice.equals("2")) {
                System.out.println(this.facade.formattedStudentCourses(currentStudent, true));
            }
            if (choice.equals("3")) {
                System.out.println(this.facade.formattedStudentCoursesLeft(currentStudent));
            }
            if (choice.equals("4")) {
                searchScreen();
            }
            if (choice.equals("5")) { // to do
                System.out.println(this.facade.formattedStudentEightSemesterPlan());
                this.facade.writeEightSemesterPlanToFile();
            }
            if (choice.equals("q")) {
                this.facade.setCurrentUser(null);
                loginScreen();
            }

            while (true) {
                System.out.println("Enter 'q' to return home: ");
                if (this.scanner.nextLine().equalsIgnoreCase("q")) {
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    break;
                }
            }
        }
    }

    public void facultyScreen() {
        while (true) {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("1. Add advisee by StudentUUID");
            System.out.println("2. View advisees");
            System.out.println("q. Logout");

            String menuChoice = this.scanner.nextLine();

            if (menuChoice.equals("1")) {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("Enter student's UUID: ");
                UUID inputUUID = UUID.fromString(this.scanner.nextLine());
                Faculty currentUser = UserList.getFacultyByID(this.facade.getCurrentUser().getID());
                currentUser.addStudent(UserList.getStudentByID(inputUUID));

            } else if (menuChoice.equals("2")) {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                Faculty currentUser = UserList.getFacultyByID(this.facade.getCurrentUser().getID());
                ArrayList<Student> students = currentUser.getAssignedStudents();

                //If students is null, do something

                for (int i = 0; i < students.size(); i++) {
                    Student s = students.get(i);
                    System.out.println((i+1) + ": " + s.getFirstName() + " " + s.getLastName());
                }

                System.out.println("Enter the number to select student");
                String studentChoice = this.scanner.nextLine();
                int index = Integer.parseInt(studentChoice) - 1;
                Student s = students.get(index);
                System.out.println("your current student is: " + s.getFirstName() + " " + s.getLastName());
                System.out.println("1. View current courses");
                System.out.println("2. View taken courses");
                System.out.println("3. View courses to be taken in major");
                System.out.println("4. Add a note to the student's profile");
                System.out.println("q. exit");
            
                String studentMenuChoice = this.scanner.nextLine();
                if (studentMenuChoice.equals("1")) {
                    System.out.println(this.facade.formattedStudentCourses(s, false));
                } else if (studentMenuChoice.equals("2")) {
                   System.out.println(this.facade.formattedStudentCourses(s, true));
                } else if (studentMenuChoice.equals("3")) {
                    System.out.println(this.facade.formattedStudentCoursesLeft(s));
                } else if (studentMenuChoice.equals("4")) {
                    System.out.println("\nType out the note you would like to add:");
                    String note = this.scanner.nextLine();
                    currentUser.addNote(s, note);
                }

                System.out.println("Enter 'q' to quit");
                this.scanner.nextLine();
                
            } else if (menuChoice.equals("q")) {
                this.facade.setCurrentUser(null);
                loginScreen();
            }


        }
    }

    public void searchScreen() {
        System.out.println("Enter a parameter to search courses by: "); 
        System.out.println("1. By name and number");
        System.out.println("2. By requirement");
        System.out.println("3. By Application Area");

        String searchChoice = this.scanner.nextLine();

        if (searchChoice.equals("1")) {
            System.out.println("Enter the subject to search by: ");
            String subject = this.scanner.nextLine();
            System.out.println("Enter the number to search by: ");
            System.out.println("    *optional ('1' will return any 100 level classes, etc)");
            String number = this.scanner.nextLine();

            ArrayList<Course> results = CourseList.searchCourseByNameAndNumber(subject, number);
            displayResults(results);

        } else if (searchChoice.equals("2")) {
            System.out.println("choose a requirement to search by: ");
            System.out.println("1. Carolina Core GFL");
            System.out.println("2. Carolina Core GSS");
            System.out.println("3. Carolina Core AIU");
            System.out.println("4. Carolina Core SCI");
            System.out.println("5. Carolina Core CMS");
            System.out.println("6. Application Area Elective");
            System.out.println("7. Laboratory Science Elective");

            String reqChoice = scanner.nextLine();
            ArrayList<Course> results = new ArrayList<Course>();
            if (reqChoice.equals("1")) {
                ArrayList<Course> result = CourseList.searchCourseByNameAndNumber(new String[]{"GERM", "SPAN", "ITAL", "FREN"}, reqChoice);
                displayResults(result);
            } else if (reqChoice.equals("2")) {

            } else if (reqChoice.equals("3")) {

            } else if (reqChoice.equals("4")) {

            } else if (reqChoice.equals("5")) {

            } else if (reqChoice.equals("6")) {

            } else if (reqChoice.equals("7")) {

            }
        } else if (searchChoice.equals("3")){
            System.out.println("choose a Application Area to search by: ");
            System.out.println("1. Science");
            System.out.println("2. Math");
            System.out.println("3. Digital Design");
            System.out.println("4. Robotics");
            System.out.println("5. Speech");

            String ApAreaChoice = scanner.nextLine();
            
            if (ApAreaChoice.equals("1")){

            } else if (ApAreaChoice.equals("2")) {

            } else if (ApAreaChoice.equals("3")) {
                ArrayList<Course> result2 = CourseList.searchCourseByNameAndNumber(new String[]{"CSCE"}, ApAreaChoice);
                displayResults(result2);
            } else if (ApAreaChoice.equals("4")) {

            } else if (ApAreaChoice.equals("5")) {

            }
        }
    }

    public void createAccountScreen() {
        System.out.println("Welcome to the account creator");
        System.out.println("If you are a new student enter 1, if you are a new faculty member press 2 (Enter q to quit)");

        String choice = this.scanner.nextLine();
        if (choice.equals("1")) {

        } else if (choice.equals("2")) {
            System.out.println("Creating faculty member...");
            UUID newID = UUID.randomUUID();
            System.out.println("Enter first name: ");
            String firstName = this.scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = this.scanner.nextLine();
            System.out.println("Enter username: ");
            String userName = this.scanner.nextLine();
            System.out.println("Enter password: ");
            String password = this.scanner.nextLine();

            ArrayList<Student> emptyList = new ArrayList<Student>();
            Faculty newFac = new Faculty(newID, userName, password, firstName, lastName, emptyList);
            ArrayList<Faculty> tempList = UserList.getFaculty();
            tempList.add(newFac);
            DataWriter.saveFaculty(tempList);
        }
    }

    public void displayResults(ArrayList<Course> results) {
        System.out.println("\n#--------------------------------------#\n");
        for (Course c : results) {
            System.out.println("----- " + c.getTitle() + " (" + c.getSubject() + c.getCourseNumber()+ ")\n");
            System.out.println("    Hours: " + c.getHours() + "\n");
        }
        System.out.println("\n#--------------------------------------#\n\n\n\n");
    }

    public void CreateTawnie() {
        System.out.println("creating Brax West...");

        MajorList majors =  MajorList.getInstance();
        Major computerInfoSystems = majors.getMajorByName("Computer Information Systems");

        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> allCourses = courseList.getCoursesByMajor("Computer Information Systems");
        ArrayList<StudentCourse> courses = new ArrayList<StudentCourse>();
        for (Course c : allCourses) {

            // Math.random() returns a double in the set [0, 1)
            Double rand = Math.random();

            StudentCourse sCourse = new StudentCourse(c.getID(), c.getTitle(), c.getHours(),
             c.getSubject(), c.getCourseNumber(), c.getPrereqs(), (rand > .5), (int)(rand * 2), rand * 100);

            courses.add(sCourse);
        }

        ArrayList<String> notes = new ArrayList<String>();
        notes.add("");

        Student tawnie = new Student(UUID.fromString("45a0b8be-5b55-4555-a835-eeab21a8ba0e"),
        "thill", "fortnite", "Tawnie", "Hill", "sophomore", computerInfoSystems, 1.7, courses, notes);

        System.out.println("created Tawnie");

        ArrayList<Student> allStudents = UserList.getStudents();
        allStudents.add(tawnie);
        DataWriter.saveStudents(allStudents);

        System.out.println("saved tawnie");
    }

    public void getMajorTest() {
        MajorList majorList = MajorList.getInstance(); // Assuming MajorList is correctly implemented as a singleton
        ArrayList<Major> majors = majorList.getMajors();
        for(Major major : majors) { 
            HashMap<Course, Integer> temp = major.getMajorRequirements();
            HashMap<Course, Integer> temp2 = major.getElectiveCourseReqs();
            System.out.println("---------------------------------------------");
            System.out.println("Printing Major Requirements Test");
            System.out.println("---------------------------------------------");
            for(HashMap.Entry<Course, Integer> entry : temp.entrySet()) {
                    System.out.println("Course: " + entry.getKey().getTitle() + " (" + entry.getKey().getSubject() + " " + entry.getKey().getCourseNumber() + ") " + "Reccomended Semester: " + entry.getValue());
            
            }
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.println("Printing Elective Requirements Test");
            System.out.println("---------------------------------------------");
            for(HashMap.Entry<Course, Integer> entry : temp2.entrySet()) {
                    System.out.println("Course: " + entry.getKey().getTitle() + " (" + entry.getKey().getSubject() + " " + entry.getKey().getCourseNumber() + ") " + "Reccomended Semester: " + entry.getValue());
            }
        }
    }

    // public void testDataWriter() {
        // //This can be deleted eventually. 
        // System.out.println();
        // DataLoader dl = new DataLoader();
        // Course a = dl.getCourses().getFirst();
        // StudentCourse b = new StudentCourse(a.getID(), a.getTitle(), a.getHours(), a.getSubject(), a.getCourseNumber(), a.getPrereqs(), true, 1, 95);
        // ArrayList<StudentCourse> courses = new ArrayList<StudentCourse>();
        // courses.add(b);
        // ArrayList<Student> st = dl.getStudents();
        // Major cs = dl.getMajors().getFirst();
        // ArrayList<String> not = new ArrayList<String>();
        // Student michael = new Student(UUID.randomUUID(), "u n", "4675", "Owen", "Lars", "sophomore", cs, 14.3, courses, not);
        // System.out.println(michael.getStanding());
        // st.add(michael);
        // DataWriter.saveStudents(st);
    // }

    public void testEightSemesterPlan() {
        ArrayList<Student> students = UserList.getStudents();
        Student temp = students.get(0);
        int totalHours = 0;
        System.out.println("Student Name: " + temp.getFirstName() + " " + temp.getLastName());
        System.out.println("Student Major: " + temp.getMajor().getName());
        for(Course course : temp.displayEightSemesterPlan()) {
            System.out.println("Course: " + course.getTitle() + " (" + course.getSubject() + " " + course.getCourseNumber() + ")");
            totalHours += course.getHours();
        }
        System.out.println("Total Hours: " + totalHours);
    }




    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }
}
