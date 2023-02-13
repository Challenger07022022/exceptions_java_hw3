package HW3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Parser {
    public static User UserParse(String str) throws IllegalArgumentException {
        String firstName = null;
        String middleName = null;
        String lastName = null;
        Date birtDate = null;
        String phoneNumber = null;
        String gender = null;

        Queue<String> tokens = new LinkedList<>(Arrays.asList(str.split(" ")));
        while (!tokens.isEmpty()) {
            String token = tokens.poll();
            if (firstName == null) {
                if (Test.IsName(token)) {
                    firstName = token;
                    continue;
                }
            }
            if (middleName == null) {
                if (token.endsWith("ович") ||
                        token.endsWith("евич") ||
                        token.endsWith("ич") ||
                        token.endsWith("овна") ||
                        token.endsWith("евна") ||
                        token.endsWith("ична") ||
                        token.endsWith("инична")) {
                    middleName = token;
                    continue;
                }
            }
            if (birtDate == null) {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                format.setLenient(false);
                try {
                    format.setLenient(false);
                    birtDate = format.parse(token);
                    continue;
                } catch (ParseException ignored) {
                }
            }
            if (phoneNumber == null) {
                String regex = "\\d+";
                if (token.matches(regex)) {
                    phoneNumber = token;
                    continue;
                }
            }
            if (gender == null) {
                if (token.equals("m") || token.equals("f")) {
                    gender = token;
                    continue;
                }
            }
            if (lastName == null) {
                lastName = token;
            }
        }

        if (firstName == null || middleName == null || lastName == null
                || birtDate == null || phoneNumber == null || gender == null) {
            throw new IllegalArgumentException("Недопустимый формат входных данных!");
        }

        return new User(lastName, firstName, middleName, birtDate, phoneNumber, gender);
    }
}
