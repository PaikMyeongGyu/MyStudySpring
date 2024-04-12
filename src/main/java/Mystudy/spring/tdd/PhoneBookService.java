package Mystudy.spring.tdd;

public class PhoneBookService {
    private PhoneBookRepository phoneBookRepository;

    public PhoneBookService(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }

    public boolean register(String name, String phone) {
        if(!name.isEmpty() && !phone.isEmpty()
                && !phoneBookRepository.contains(name)) {
            phoneBookRepository.insert(name, phone);
            return true;
        }
        return false;
    }

    public String search(String name) {
        if(!name.isEmpty() && phoneBookRepository.contains(name)) {
            return phoneBookRepository.getPhoneNumberByContactName(name);
        }
        return null;
    }
}