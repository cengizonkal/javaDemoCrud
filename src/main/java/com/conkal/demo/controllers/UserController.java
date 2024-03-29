package com.conkal.demo.controllers;
import com.conkal.demo.models.User;
import com.conkal.demo.repositories.UserRepository;
import com.conkal.demo.requests.StoreUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public @ResponseBody Iterable<User> index(@RequestParam(required = false) String search) {
        if (search != null) {
            return userRepository.searchUsers(search);
        }
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public @ResponseBody ResponseEntity<?> store(@Valid StoreUserRequest request, BindingResult result) {

        if (result.hasErrors()) {
            // If there are validation errors, return them
            return ResponseEntity.unprocessableEntity().body(validationErrors(result));
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/users/{id}")
    public @ResponseBody ResponseEntity<?> destroy(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PatchMapping("/users/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable Integer id, @Valid StoreUserRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body(validationErrors(result));
        }
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    private Map<String, String> validationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



}
