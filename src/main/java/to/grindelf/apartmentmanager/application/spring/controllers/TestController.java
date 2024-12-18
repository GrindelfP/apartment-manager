package to.grindelf.apartmentmanager.application.spring.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";  // Это имя файла hello.html
    }
}