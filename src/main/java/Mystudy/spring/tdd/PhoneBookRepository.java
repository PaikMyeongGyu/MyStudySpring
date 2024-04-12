package Mystudy.spring.tdd;

public class PhoneBookRepository {

    public boolean contains(String name) {
        return false;
    }

    public boolean insert(String name, String phone) {
        return true;
    }

    public void add(int index, String element) {

    }

    public String getPhoneNumberByContactName(String name) {
        return "phone";
    }
}
