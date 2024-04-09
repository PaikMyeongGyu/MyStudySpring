package Mystudy.spring.tdd.chapter06.user;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
