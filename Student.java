// Student.java - Subclass storing student-specific details
// Demonstrates Inheritance (extends Person) and Encapsulation
public class Student extends Person {
    // Private fields specific to Student
    private String course;
    private double marks;

    // Default constructor
    public Student() {
        super();
    }

    // Parameterized constructor using super() to initialize Person attributes
    public Student(int id, String name, int age, String course, double marks) {
        super(id, name, age); // Inheritance: invokes Person constructor
        this.course = course;
        this.marks = marks;
    }

    // Getters and Setters
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Method to calculate grade based on marks
    public String calculateGrade() {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F (Fail)";
        }
    }

    // Method overriding: specialized displayDetails for Student (Polymorphism)
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Course: " + course);
        System.out.printf("Marks : %.2f%n", marks);
        System.out.println("Grade : " + calculateGrade());
    }
}
