package Mystudy.spring.tdd;

public class PasswordValidator {

    public boolean isValid(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        if (id.length() < 8) {
            return false;
        }
        return true;
    }
}
