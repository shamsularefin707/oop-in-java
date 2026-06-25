import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("   Strict Student Registration System    ");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n--- Register a New Student ---");

            String name = "", rollNumber = "", email = "", nidBrn = "", bloodGroup = "", password = "";
            int age = -1;

            // 1. Name Input Loop
            while (true) {
                System.out.print("Enter Name: ");
                name = scanner.nextLine();
                try {
                    new Student(name, 20, "BSSE1634", "bsse1419@iit.du.ac.bd", "5501234567", "A+", "SecurePass1!");
                    break;
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                }
            }

            // 2. Age Input Loop
            while (true) {
                System.out.print("Enter Age: ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    try {
                        new Student(name, age, "BSSE1634", "bsse1419@iit.du.ac.bd", "5501234567", "A+", "SecurePass1!");
                        break;
                    } catch (InvalidStudentDataException e) {
                        System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                    }
                } else {
                    System.out.println("[ERROR] Invalid Input: Age must be a numeric integer. Try again.\n");
                    scanner.nextLine();
                }
            }

            // 3. Roll Number Input Loop
            while (true) {
                System.out.print("Enter Roll Number (e.g., BSSE1634): ");
                rollNumber = scanner.nextLine();
                try {
                    new Student(name, age, rollNumber, "bsse1419@iit.du.ac.bd", "5501234567", "A+", "SecurePass1!");
                    break;
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                }
            }

            // 4. Email Input Loop (Enforces the requested strict rule)
            while (true) {
                System.out.print("Enter Institutional Email (e.g., bsse1419@iit.du.ac.bd): ");
                email = scanner.nextLine();
                try {
                    new Student(name, age, rollNumber, email, "5501234567", "A+", "SecurePass1!");
                    break;
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                }
            }

            // 5. NID / BRN Input Loop
            while (true) {
                System.out.print("Enter NID or BRN (10 or 17 digits, no leading 0): ");
                nidBrn = scanner.nextLine();
                try {
                    new Student(name, age, rollNumber, email, nidBrn, "A+", "SecurePass1!");
                    break;
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                }
            }

            // 6. Blood Group Input Loop
            while (true) {
                System.out.print("Enter Blood Group (e.g., A+, AB-, O+): ");
                bloodGroup = scanner.nextLine();
                try {
                    new Student(name, age, rollNumber, email, nidBrn, bloodGroup, "SecurePass1!");
                    break;
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                }
            }

            // 7. Password Input Loop
            while (true) {
                System.out.print("Enter Portal Password: ");
                password = scanner.nextLine();
                try {
                    new Student(name, age, rollNumber, email, nidBrn, bloodGroup, password);
                    break;
                } catch (InvalidStudentDataException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Try again.\n");
                }
            }

            // Final object confirmation
            Student student = new Student(name, age, rollNumber, email, nidBrn, bloodGroup, password);
            System.out.println("\n[SUCCESS] Registration complete!");
            System.out.println("Data: " + student);

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
