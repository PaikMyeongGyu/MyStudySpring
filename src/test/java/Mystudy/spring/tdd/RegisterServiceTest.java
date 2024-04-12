package Mystudy.spring.tdd;


import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterServiceTest {

    // Mock 개체 생성 및 주입
    private EmailSender emailSender = mock(EmailSender.class);
    private UserRepository userRepository = new UserRepository();
    private RegisterService registerService = new RegisterService(emailSender, userRepository);

    @Test
    void sendEmail() {
        // given
        registerService.register("id", "pw");
        // when // then
        then(emailSender).should().sendEmail(); // 이메일 호출 여부 판단
    }


    @Test
    void throwEx() {
        emailSender = mock(EmailSender.class);
        given(emailSender).willThrow(new IllegalArgumentException());
    }
}
