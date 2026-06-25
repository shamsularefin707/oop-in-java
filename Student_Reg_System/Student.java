import java.util.regex.Pattern;

public class Student {
    private final String name;
    private final int age;
    private final String rollNumber;
    private final String email;
    private final String nidBrn;
    private final String bloodGroup;
    private final String password;

    // Core validation patterns
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");
    private static final Pattern AGE_PATTERN = Pattern.compile("^(1[6-9]|2[0-9]|30)$");
    private static final Pattern ROLL_PATTERN = Pattern.compile("^BSSE\\d{4}$");

    // STRICT EMAIL REGEX: Must be bsse, followed by digits, followed by @iit.du.ac.bd
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^bsse\\d+@iit\\.du\\.ac\\.bd$");
    private static final Pattern NID_BRN_PATTERN = Pattern.compile("^[1-9]\\d{9}$|^[1-9]\\d{16}$");
    private static final Pattern BLOOD_GROUP_PATTERN = Pattern.compile("^(A|B|AB|O)[+-]$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$");

    public Student(String name, int age, String rollNumber, String email, String nidBrn, String bloodGroup, String password) {
        if (!NAME_PATTERN.matcher(name != null ? name : "").matches()) {
            throw new InvalidStudentDataException("Invalid Name: Only letters and spaces allowed.");
        }
        if (!AGE_PATTERN.matcher(String.valueOf(age)).matches()) {
            throw new InvalidStudentDataException("Invalid Age: Must be between 16 and 30.");
        }
        if (!ROLL_PATTERN.matcher(rollNumber != null ? rollNumber : "").matches()) {
            throw new InvalidStudentDataException("Invalid Roll Number: Must start with 'BSSE' followed by 4 digits.");
        }
        if (!EMAIL_PATTERN.matcher(email != null ? email : "").matches()) {
            throw new InvalidStudentDataException("Invalid Email: Must perfectly match the format 'bsse[number]@iit.du.ac.bd'.");
        }
        if (!NID_BRN_PATTERN.matcher(nidBrn != null ? nidBrn : "").matches()) {
            throw new InvalidStudentDataException("Invalid NID/BRN: Must be exactly 10 or 17 digits and cannot start with 0.");
        }
        if (!BLOOD_GROUP_PATTERN.matcher(bloodGroup != null ? bloodGroup : "").matches()) {
            throw new InvalidStudentDataException("Invalid Blood Group: Must be A, B, AB, or O followed by + or - (Uppercase only).");
        }
        if (!PASSWORD_PATTERN.matcher(password != null ? password : "").matches()) {
            throw new InvalidStudentDataException("Invalid Password: Must be 8-16 characters with at least one uppercase, one lowercase, one digit, and one special character (@$!%*?&).");
        }

        this.name = name;
        this.age = age;
        this.rollNumber = rollNumber;
        this.email = email;
        this.nidBrn = nidBrn;
        this.bloodGroup = bloodGroup;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Student [Name: %s, Age: %d, Roll: %s, Email: %s, NID/BRN: %s, Blood: %s]",
                name, age, rollNumber, email, nidBrn, bloodGroup);
    }
}
