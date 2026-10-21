// Person.java - Superclass storing common personal details
// Demonstrates Encapsulation (private fields with getters/setters)
public class Person {
    // Private fields (Encapsulation)
    private int id;
    private String name;
    private int age;

    // Default constructor
    public Person() {
    }

    // Parameterized constructor
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to display basic personal details
    public void displayDetails() {
        System.out.println("ID   : " + id);
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }
}
