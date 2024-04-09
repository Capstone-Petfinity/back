package Capstone.Petfinity.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HelloController {

    @GetMapping("/hello/yezi")
    public String hello() {
//        model.addAttribute("data", "hello!!");
        return "민식이바보";
    }
}