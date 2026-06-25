public class MainClass {
    public static void main(String[] args) {

        System.out.println("=== Test Case 1: Valid Student ===");
        try {
            Student student1 = new Student("Al Araf", 20, "BSSE1634");
            System.out.println("Successfully created: " + student1);
        } catch (InvalidStudentDataException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 2: Invalid Roll Number ===");
        try {
            // Fails because it has 5 digits instead of 4
            Student student2 = new Student("Ezaz Rahman", 25, "BA9999");
            System.out.println("Successfully created: " + student2);
        } catch (InvalidStudentDataException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 3: Invalid Age ===");
        try {
            // Fails because 15 is outside the 16-30 range
            Student student3 = new Student("Samia Mumtahina", 15, "BSSE1612");
            System.out.println("Successfully created: " + student3);
        } catch (InvalidStudentDataException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }
