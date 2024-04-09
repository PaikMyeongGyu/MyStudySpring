package Mystudy.spring.tdd.chapter06.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker,
                fakeRepository,
                spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        // given
        stubPasswordChecker.setWeak(true);

        // when // then
        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists() {
        // given
        fakeRepository.save(new User("id", "pw1", "email@email.com"));
        // when // then
        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess() {
        // given
        userRegister.register("id", "pw", "email");
        // when // then
        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 발송함")
    @Test
    void whenRegisterThenSendEmail(){
        // given
        userRegister.register("id", "pw", "email@email.com");
        // when // then
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals(
                "email@email.com",
                spyEmailNotifier.getEmail());
    }

}