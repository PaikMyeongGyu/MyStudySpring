package Mystudy.spring.tdd;

public class RegisterService {

    private EmailSender emailSender;
    private PasswordValidator passwordValidator;
    private UserRepository userRepository;

    public RegisterService(EmailSender emailSender,
                           UserRepository userRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }

    public boolean register(String id, String password) {
        User savedUser = userRepository.save(id, password);
        if (savedUser != null) {
           emailSender.sendEmail();
           return true;
        }
        return false;
    }


}
