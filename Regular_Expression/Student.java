import java.util.regex.Pattern;

/**
 * Encapsulates Student data and enforces validation rules.
 */
public class Student {
    private final String name;
    private final int age;
    private final String rollNumber;

    // RegEx Rules
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");
    private static final Pattern AGE_PATTERN = Pattern.compile("^(1[6-9]|2[0-9]|30)$");
    private static final Pattern ROLL_PATTERN = Pattern.compile("^BSSE\\d{4}$");

    public Student(String name, int age, String rollNumber) {
        if (!isValidName(name)) {
            throw new InvalidStudentDataException("Invalid Name: Only letters and spaces allowed.");
        }
        if (!isValidAge(age)) {
            throw new InvalidStudentDataException("Invalid Age: Must be between 16 and 30.");
        }
        if (!isValidRollNumber(rollNumber)) {
            throw new InvalidStudentDataException("Invalid Roll Number: Must start with 'BSSE' followed by 4 digits.");
        }

        this.name = name;
        this.age = age;
        this.rollNumber = rollNumber;
    }

    private boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    private boolean isValidAge(int age) {
        return AGE_PATTERN.matcher(String.valueOf(age)).matches();
    }

    private boolean isValidRollNumber(String rollNumber) {
        return rollNumber != null && ROLL_PATTERN.matcher(rollNumber).matches();
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getRollNumber() { return rollNumber; }

    @Override
    public String toString() {
        return String.format("Student [Name: %s, Age: %d, Roll: %s]", name, age, rollNumber);
    }
}