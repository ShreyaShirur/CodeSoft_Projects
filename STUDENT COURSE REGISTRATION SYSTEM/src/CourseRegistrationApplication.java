import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseCode;
    private String title;
    private String description;
    public int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

class CourseRegistrationSystem {
    private List<Course> courseDatabase;
    private List<Student> studentDatabase;

    public CourseRegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseDatabase.add(course);
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("------------------------------");
        }
    }

    public void registerStudent(Student student) {
        studentDatabase.add(student);
    }

    public void displayStudents() {
        System.out.println("\nRegistered Students:");
        for (Student student : studentDatabase) {
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Name: " + student.getName());
            System.out.println("Registered Courses: " + student.getRegisteredCourses());
            System.out.println("------------------------------");
        }
    }

    public void registerStudentForCourse(int studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.getRegisteredCourses().size() < 3 && course.getCapacity() > 0) {
                student.registerCourse(courseCode);
                courseDatabase.get(courseDatabase.indexOf(course)).capacity--;
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed. Either the student has reached the maximum course limit or the course is full.");
            }
        } else {
            System.out.println("Student or course not found. Registration failed.");
        }
    }

    public void dropStudentFromCourse(int studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            student.dropCourse(courseCode);
            courseDatabase.get(courseDatabase.indexOf(course)).capacity++;
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("Student or course not found. Course removal failed.");
        }
    }

    private Student findStudentByID(int studentID) {
        for (Student student : studentDatabase) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courseDatabase) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

public class CourseRegistrationApplication {
    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Adding sample courses to the system
        registrationSystem.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon, Wed 10:00 AM - 11:30 AM"));
        registrationSystem.addCourse(new Course("ENG201", "English Literature", "Survey of English literature", 25, "Tue, Thu 2:00 PM - 3:30 PM"));
        registrationSystem.addCourse(new Course("MAT301", "Calculus", "Fundamental principles of calculus", 20, "Mon, Wed, Fri 9:00 AM - 10:30 AM"));

        // Adding sample students to the system
        registrationSystem.registerStudent(new Student(1, "John Doe"));
        registrationSystem.registerStudent(new Student(2, "Jane Smith"));

        // Displaying available courses
        registrationSystem.displayCourses();

        // Registering students for courses
        registrationSystem.registerStudentForCourse(1, "CS101");
        registrationSystem.registerStudentForCourse(1, "ENG201");
        registrationSystem.registerStudentForCourse(2, "MAT301");

        // Displaying registered students
        registrationSystem.displayStudents();

        // Dropping a student from a course
        registrationSystem.dropStudentFromCourse(1, "ENG201");

        // Displaying updated registered students and available courses
        registrationSystem.displayStudents();
        registrationSystem.displayCourses();
    }
}
