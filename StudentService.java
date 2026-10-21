import java.util.List;

// StudentService.java - Interface defining core student operations
// Demonstrates Abstraction (declares abstract methods without implementation)
public interface StudentService {
    // Add a new student record
    void addStudent(Student student);

    // View all student records
    void viewAllStudents();

    // Search for a student by their ID
    Student searchStudentById(int id);

    // Update details of an existing student
    boolean updateStudent(int id, String newName, int newAge, String newCourse, double newMarks);

    // Delete a student by ID
    boolean deleteStudent(int id);

    // Display total number of students and average marks
    void displaySummary();

    // Check if an ID already exists to prevent duplicate entries
    boolean isIdDuplicate(int id);

    // Get the underlying list of students
    List<Student> getAllStudents();
}
