package com.example.wallet.controller;

import com.example.wallet.carrier.LoginCarrier;
import com.example.wallet.carrier.ModifyUserCarrier;
import com.example.wallet.carrier.UserRegistrationREQCarrier;
import com.example.wallet.carrier.UserRegistrationRESCarrier;
import com.example.wallet.entity.User;
import com.example.wallet.exception.PermissionDeniedException;
import com.example.wallet.exception.UserNotFoundException;
import com.example.wallet.security.LoginTokenStore;
import com.example.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;
    private final LoginTokenStore tokenStore;

    @Autowired
    public UserController(UserService service, LoginTokenStore tokenStore) {
        this.service = service;
        this.tokenStore = tokenStore;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginCarrier carrier) {
        User user = service.getUserByUsername(carrier.username());
        if (!user.getPassword().equals(carrier.password())) {
            throw new UserNotFoundException();
        }
        return ResponseEntity.ok(tokenStore.generateToken(carrier.username()));
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String token) {
        tokenStore.invalidate(token);
        return ResponseEntity.ok("success");
    }

    @PostMapping("register")
    public UserRegistrationRESCarrier registerUser(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody UserRegistrationREQCarrier carrier) {
        return new UserRegistrationRESCarrier(service.save(new User(carrier.fullName(), carrier.mobileNumber(), carrier.username(), carrier.password(), service.getAdminIdByToken(token))), tokenStore.generateToken(carrier.username()));
    }

    @PostMapping("registerAsAdmin")
    public UserRegistrationRESCarrier registerAsAdmin(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody UserRegistrationREQCarrier carrier) {
        return new UserRegistrationRESCarrier(service.save(new User(carrier.fullName(), carrier.mobileNumber(), carrier.username(), carrier.password(), service.getAdminIdByToken(token), true)),tokenStore.generateToken(carrier.username()));
    }

    @PostMapping("modify")
    public ResponseEntity<?> modify(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody ModifyUserCarrier carrier) {
        tokenStore.findSession(token);
        User admin = service.getUserByUsername(tokenStore.getUsername(token));
        User user = service.getUserByUsername(carrier.username());

        if (!admin.isAdmin() && !admin.getId().equals(user.getId())) {
            throw new PermissionDeniedException();
        }

        user.setFullName(carrier.newUser().getFullName());
        user.setMobileNumber(carrier.newUser().getMobileNumber());
        user.setUsername(carrier.newUser().getUsername());
        user.setPassword(carrier.newUser().getPassword());
        user.setLastModifiedDate(Instant.now());
        user.setLastModifiedBy(admin.getId());

        service.update(user);

        return ResponseEntity.ok(user);
    }
}
