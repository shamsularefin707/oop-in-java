import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("   Dynamic Student Registration System   ");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n--- Register a New Student ---");
            
            String name = "";
            int age = -1;
            String rollNumber = "";

            // 1. Dynamic Name Validation Loop
            while (true) {
                System.out.print("Enter Name (Letters & Spaces only): ");
                name = scanner.nextLine();
                
                // Temporary dummy instance to catch immediate validation exceptions
                try {
                    new Student(name, 20, "BSSE1634"); 
                    break; // If no exception is thrown, input is valid; exit name loop
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Please try again.\n");
                }
            }

            // 2. Dynamic Age Validation Loop
            while (true) {
                System.out.print("Enter Age (Must be between 16 and 30): ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    scanner.nextLine(); // Clear newline buffer
                    
                    try {
                        new Student("Valid Name", age, "BSSE1634");
                        break; // Input is valid; exit age loop
                    } catch (InvalidStudentDataException e) {
                        System.out.println("[ERROR] " + e.getMessage() + " Please try again.\n");
                    }
                } else {
                    System.out.println("[ERROR] Invalid Input: Age must be a numeric integer. Please try again.\n");
                    scanner.nextLine(); // Clear the bad string input from buffer
                }
            }

            // 3. Dynamic Roll Number Validation Loop
            while (true) {
                System.out.print("Enter Roll Number (e.g., BSSE1634): ");
                rollNumber = scanner.nextLine();
                
                try {
                    new Student("Valid Name", 20, rollNumber);
                    break; // Input is valid; exit roll number loop
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Please try again.\n");
                }
            }

            // 4. Final Object Creation
            Student student = new Student(name, age, rollNumber);
            System.out.println("\n[SUCCESS] Student completely verified and registered!");
            System.out.println("Final Details: " + student);

            // 5. Ask to continue
            System.out.print("\nDo you want to enter another student? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("yes") && !choice.equals("y")) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }
        }
        
        scanner.close();
    }
}
