import java.util.ArrayList;
import java.util.List;

// StudentServiceImpl.java - Implementation of StudentService
// Demonstrates Polymorphism (implements interface methods and provides concrete logic)
public class StudentServiceImpl implements StudentService {

    // In-memory data store using ArrayList
    // Note: Data is stored in RAM and resets when the application is terminated
    private List<Student> studentList;

    public StudentServiceImpl() {
        this.studentList = new ArrayList<>();
    }

    @Override
    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("\nSUCCESS: Student record added successfully!");
    }

    @Override
    public void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("\nNo student records found. The list is empty.");
            return;
        }

        System.out.println("\n=================================== ALL STUDENT RECORDS ===================================");
        System.out.printf("%-8s %-20s %-6s %-20s %-8s %-10s%n", "ID", "Name", "Age", "Course", "Marks", "Grade");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Student student : studentList) {
            System.out.printf("%-8d %-20s %-6d %-20s %-8.2f %-10s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getMarks(),
                    student.calculateGrade());
        }
        System.out.println("===========================================================================================");
    }

    @Override
    public Student searchStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean updateStudent(int id, String newName, int newAge, String newCourse, double newMarks) {
        Student student = searchStudentById(id);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
            student.setCourse(newCourse);
            student.setMarks(newMarks);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student = searchStudentById(id);
        if (student != null) {
            studentList.remove(student);
            return true;
        }
        return false;
    }

    @Override
    public void displaySummary() {
        int totalStudents = studentList.size();

        System.out.println("\n==================== SYSTEM SUMMARY ====================");
        System.out.println("Total Number of Students : " + totalStudents);

        if (totalStudents == 0) {
            System.out.println("Average Marks            : 0.00 (No records available)");
        } else {
            double totalMarks = 0.0;
            for (Student student : studentList) {
                totalMarks += student.getMarks();
            }
            double averageMarks = totalMarks / totalStudents;
            System.out.printf("Average Marks            : %.2f%n", averageMarks);
        }
        System.out.println("========================================================");
    }

    @Override
    public boolean isIdDuplicate(int id) {
        return searchStudentById(id) != null;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }
}
