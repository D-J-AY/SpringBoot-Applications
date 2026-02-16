package Practice1.hotelapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to Hotel Management Project";
    }

    @GetMapping("/status")
    public String status() {
        return "Application is running Successfully";
    }
}
