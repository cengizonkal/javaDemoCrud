package com.conkal.demo.controllers;
import com.conkal.demo.models.User;
import com.conkal.demo.repositories.UserRepository;
import com.conkal.demo.requests.StoreUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public @ResponseBody Iterable<User> index() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public @ResponseBody String store(@RequestParam String firstName,
                                      @RequestParam String lastName,
                                      @RequestParam String email,
                                      @RequestParam String phone) {



        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);
        return "Saved";
    }



}
