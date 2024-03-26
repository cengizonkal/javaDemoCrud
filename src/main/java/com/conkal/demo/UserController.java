package com.conkal.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/users")
    public @ResponseBody String index(){
        return "Hello";
    }

}
