import java.util.Scanner;

// Main.java - Entry point of the Student Management System
// Handles the console-based menu and user interactions
public class Main {

    // Abstraction & Polymorphism: Using interface type for implementation reference
    private static StudentService studentService = new StudentServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("        WELCOME TO STUDENT MANAGEMENT SYSTEM (CORE JAVA)       ");
        System.out.println("   [Note: Records are stored in-memory (ArrayList) and will   ]");
        System.out.println("   [be cleared automatically when the application is closed.  ]");
        System.out.println("===============================================================");

        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = readIntInput("Enter your choice (1-8): ");

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleViewAllStudents();
                    break;
                case 3:
                    handleSearchStudent();
                    break;
                case 4:
                    handleUpdateStudent();
                    break;
                case 5:
                    handleDeleteStudent();
                    break;
                case 6:
                    handleCalculateGrade();
                    break;
                case 7:
                    handleDisplaySummary();
                    break;
                case 8:
                    isRunning = false;
                    System.out.println("\nThank you for using the Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select an option between 1 and 8.");
            }
        }

        scanner.close();
    }

    // Displays the main interactive menu
    private static void displayMenu() {
        System.out.println("\n==================== MAIN MENU ====================");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student Details");
        System.out.println("5. Delete Student Record");
        System.out.println("6. Calculate Student Grade");
        System.out.println("7. Display Total Students & Average Marks");
        System.out.println("8. Exit Application");
        System.out.println("===================================================");
    }

    // 1. Add Student
    private static void handleAddStudent() {
        System.out.println("\n--- Add New Student ---");

        int studentId;
        while (true) {
            studentId = readPositiveIntInput("Enter Student ID (e.g. 101): ");
            if (studentService.isIdDuplicate(studentId)) {
                System.out.println("Error: Student with ID " + studentId + " already exists! Duplicate IDs are not allowed.");
            } else {
                break;
            }
        }

        String name = readNonEmptyString("Enter Student Name: ");
        int age = readAgeInput("Enter Student Age (1 - 120): ");
        String course = readNonEmptyString("Enter Course / Branch (e.g. Computer Science): ");
        double marks = readMarksInput("Enter Marks (0.0 - 100.0): ");

        Student student = new Student(studentId, name, age, course, marks);
        studentService.addStudent(student);
    }

    // 2. View All Students
    private static void handleViewAllStudents() {
        studentService.viewAllStudents();
    }

    // 3. Search Student by ID
    private static void handleSearchStudent() {
        System.out.println("\n--- Search Student by ID ---");
        int studentId = readPositiveIntInput("Enter Student ID to search: ");

        Student student = studentService.searchStudentById(studentId);
        if (student != null) {
            System.out.println("\nStudent Found Details:");
            System.out.println("---------------------------------");
            student.displayDetails();
            System.out.println("---------------------------------");
        } else {
            System.out.println("\nStudent with ID " + studentId + " not found.");
        }
    }

    // 4. Update Student Details
    private static void handleUpdateStudent() {
        System.out.println("\n--- Update Student Details ---");
        int studentId = readPositiveIntInput("Enter Student ID to update: ");

        Student student = studentService.searchStudentById(studentId);
        if (student == null) {
            System.out.println("\nStudent with ID " + studentId + " not found. Cannot update.");
            return;
        }

        System.out.println("\nCurrent Details:");
        System.out.println("Name: " + student.getName() + ", Age: " + student.getAge()
                + ", Course: " + student.getCourse() + ", Marks: " + student.getMarks());

        System.out.println("\nEnter New Details:");
        String newName = readNonEmptyString("Enter New Name: ");
        int newAge = readAgeInput("Enter New Age (1 - 120): ");
        String newCourse = readNonEmptyString("Enter New Course: ");
        double newMarks = readMarksInput("Enter New Marks (0.0 - 100.0): ");

        boolean updated = studentService.updateStudent(studentId, newName, newAge, newCourse, newMarks);
        if (updated) {
            System.out.println("\nSUCCESS: Student record updated successfully!");
        } else {
            System.out.println("\nFailed to update student record.");
        }
    }

    // 5. Delete Student Record
    private static void handleDeleteStudent() {
        System.out.println("\n--- Delete Student Record ---");
        int studentId = readPositiveIntInput("Enter Student ID to delete: ");

        boolean deleted = studentService.deleteStudent(studentId);
        if (deleted) {
            System.out.println("\nSUCCESS: Student with ID " + studentId + " deleted successfully!");
        } else {
            System.out.println("\nStudent with ID " + studentId + " not found. Cannot delete.");
        }
    }

    // 6. Calculate Student Grade
    private static void handleCalculateGrade() {
        System.out.println("\n--- Calculate Student Grade ---");
        int studentId = readPositiveIntInput("Enter Student ID to calculate grade: ");

        Student student = studentService.searchStudentById(studentId);
        if (student != null) {
            System.out.println("\nGrade Details for ID " + student.getId() + " (" + student.getName() + "):");
            System.out.println("---------------------------------------");
            System.out.printf("Marks Obtained : %.2f / 100.00%n", student.getMarks());
            System.out.println("Calculated Grade: " + student.calculateGrade());
            System.out.println("---------------------------------------");
            System.out.println("Grading Scale: [90-100: A+] [80-89: A] [70-79: B] [60-69: C] [50-59: D] [<50: F]");
        } else {
            System.out.println("\nStudent with ID " + studentId + " not found.");
        }
    }

    // 7. Display Summary
    private static void handleDisplaySummary() {
        studentService.displaySummary();
    }

    // ==========================================
    // Robust Helper Methods for Input Validation
    // ==========================================

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer number.");
            }
        }
    }

    private static int readPositiveIntInput(String prompt) {
        while (true) {
            int value = readIntInput(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("ID must be a positive number greater than 0.");
        }
    }

    private static int readAgeInput(String prompt) {
        while (true) {
            int age = readIntInput(prompt);
            if (age >= 1 && age <= 120) {
                return age;
            }
            System.out.println("Invalid age! Please enter an age between 1 and 120.");
        }
    }

    private static double readMarksInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (marks >= 0.0 && marks <= 100.0) {
                    return marks;
                }
                System.out.println("Invalid marks! Marks must be between 0.0 and 100.0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid numeric value for marks.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please enter a valid text.");
        }
    }
}
