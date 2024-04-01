package Mystudy.spring.validation;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidationController {

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
    public String validation(@Valid @ModelAttribute ValidationRequest request){
        ValidationService validationService = new ValidationService();
        validationService.service(request.createRequest());
        return "Validation is complete";
    }
}
