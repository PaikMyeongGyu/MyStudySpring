package Mystudy.spring.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PhoneBookServiceTest {

    private PhoneBookRepository mockPhoneBookRepository = mock(PhoneBookRepository.class);
    private PhoneBookService phoneBookService = new PhoneBookService(mockPhoneBookRepository);

    @Test
    void phoneBookSearchEx() {
        // given
        given(mockPhoneBookRepository.contains(anyString())).willThrow(new IllegalArgumentException());
        // when // then
        assertThrows(
                IllegalArgumentException.class,
                () -> phoneBookService.search("example"));
    }

    @Test
    void phoneBookRegisterTest() {
        // given
        given(mockPhoneBookRepository.insert(anyString(), eq("phone"))).willThrow(new IllegalArgumentException());
        // when // then
        assertThrows(
                IllegalArgumentException.class,
                () -> phoneBookService.register("name", "phone"));
    }

    @Test
    void voidMethodTest() {
        // given
        doNothing().when(mockPhoneBookRepository).add(anyInt(), anyString());
        mockPhoneBookRepository.add(0, "1");

        verify(mockPhoneBookRepository, times(1)).add(0, "1");
    }

    @Test
    void voidMethodTest2() {
        doThrow(IllegalArgumentException.class).when(mockPhoneBookRepository).add(anyInt(), isNull());

        assertThrows(IllegalArgumentException.class, () -> {
            mockPhoneBookRepository.add(1, null);
        });
    }

    @Test
    void argumentCaptor() {
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(mockPhoneBookRepository).add(any(Integer.class), valueCapture.capture());

        mockPhoneBookRepository.add(0, "captured");
        assertEquals("captured", valueCapture.getValue());
    }
}
