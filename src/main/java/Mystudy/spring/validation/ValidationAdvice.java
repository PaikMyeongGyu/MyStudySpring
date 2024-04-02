package Mystudy.spring.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ValidationAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseDto exHandler(IllegalArgumentException e){
        return new ResponseDto(HttpStatus.BAD_REQUEST.value() ,null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto illegalArgExHandler(IllegalArgumentException e){
        return new ResponseDto(HttpStatus.BAD_REQUEST.value() ,e.getMessage());
    }
}
