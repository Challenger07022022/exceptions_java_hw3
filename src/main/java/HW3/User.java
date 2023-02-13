package HW3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Date birtDate;
    private final String phoneNumber;
    private final String gender;

    public User(String lastName, String firstName, String middleName,
                Date birtDate, String phoneNumber, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birtDate = birtDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + " "
                + new SimpleDateFormat("dd.MM.yyyy").format(birtDate) + " "
                + phoneNumber + " " + gender;
    }
}