package com.bonesh.wallet.controller;

import com.bonesh.wallet.exception.UserAlreadyExist;
import com.bonesh.wallet.model.carrier.user.AuthRequestCarrier;
import com.bonesh.wallet.model.carrier.user.ExceptionCarrier;
import com.bonesh.wallet.model.carrier.user.UserRegistrationREQCarrier;
import com.bonesh.wallet.model.carrier.user.UserRegistrationRESCarrier;
import com.bonesh.wallet.model.entity.User;
import com.bonesh.wallet.model.service.UserService;
import com.couchbase.client.core.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserRegistrationRESCarrier registerUser(@RequestBody UserRegistrationREQCarrier carrier) {
        return new UserRegistrationRESCarrier(service.save(new User(carrier.fullName(), carrier.mobileNumber(), carrier.username(), carrier.password())));


    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody AuthRequestCarrier carrier) {
        String token=service.login(carrier.fullName(), carrier.mobileNumber(), carrier.username(), carrier.password());
        if(token!=null) {
            return ResponseEntity.ok(token);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            boolean loggedOut = service.logout(token);
            if (loggedOut) {
                return ResponseEntity.ok("Logged out successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header missing or invalid.");
    }
}

