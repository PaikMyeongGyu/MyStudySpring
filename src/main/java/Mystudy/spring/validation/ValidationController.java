package Mystudy.spring.validation;

import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

@RestController
public class ValidationController {

    @GetMapping("/controller/exception")
    public void exception(){
        throw new IllegalArgumentException("Exception이 발생했습니다.");
    }

    // @GetMapping("/validation")
    public String validationV2(@Valid @ModelAttribute ValidationRequest request, BindingResult bindingResult){
        System.out.println("컨트롤러가 동작했습니다.");
        List<ObjectError> errors = bindingResult.getAllErrors();

        errors.stream().forEach(error -> {
            System.out.println("error.getObjectName() = " + error.getObjectName());
            System.out.println("error.getDefaultMessage() = " + error.getDefaultMessage());
        });
        
        return "Validation is complete";
    }

    // @GetMapping("/validation")
    public String validationV1(@Valid @ModelAttribute ValidationRequest request){
        System.out.println("컨트롤러가 동작했습니다.");
        return "Validation is complete";
    }

    @GetMapping("/validation")
    public String validation(@Valid @ModelAttribute ValidationRequest request, BindingResult bindingResult){
        System.out.println("Hello Validation");
        return "Validation is complete";
    }

    @PostMapping("/validation")
    public String validationPost(@Valid @RequestBody ValidationRequest request, BindingResult bindingResult){
        System.out.println("Hello Validation");
        return "validation is complete";
    }


}
