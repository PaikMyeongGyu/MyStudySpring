package Mystudy.spring.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class filterController {
    @GetMapping("/filter")
    public String filter(){
        log.info("컨트롤러가 동작했습니다.");
        return "filter Method Operated";
    }
}
