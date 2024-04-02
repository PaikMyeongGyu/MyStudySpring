package Mystudy.spring.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data @Getter
@AllArgsConstructor
public class ResponseDto {
    private Integer errorCode;
    private String message;
}
