package com.conkal.demo.controllers;
import com.conkal.demo.models.User;
import com.conkal.demo.repositories.UserRepository;
import com.conkal.demo.requests.StoreUserRequest;
import com.conkal.demo.services.UserService;
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

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> index(@RequestParam(required = false) String search) {
        if (search != null) {
            return userRepository.searchUsers(search);
        }
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public @ResponseBody ResponseEntity<?> store(@RequestBody @Valid StoreUserRequest request, BindingResult result) {

        if (result.hasErrors()) {
            // If there are validation errors, return them
            return ResponseEntity.unprocessableEntity().body(validationErrors(result));
        }
        userService.storeUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @DeleteMapping("/users/{id}")
    public @ResponseBody ResponseEntity<?> destroy(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PostMapping("/users/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable Integer id, @Valid StoreUserRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body(validationErrors(result));
        }
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.updateUser(user, request);
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
