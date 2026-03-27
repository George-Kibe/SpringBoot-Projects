package com.fitness.userService.controllers;

import com.fitness.userService.dto.RegisterUserRequest;
import com.fitness.userService.dto.UserResponse;
import com.fitness.userService.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        return ResponseEntity.ok(
                userService.getUserProfile(userId)
        );
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody RegisterUserRequest registerUserRequest) {
        UserResponse user = userService.register(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
