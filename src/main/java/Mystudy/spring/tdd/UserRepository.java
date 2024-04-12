package Mystudy.spring.tdd;

public class UserRepository {

    public User save(String id, String password) {
        return new User(id, password);
    }
}
