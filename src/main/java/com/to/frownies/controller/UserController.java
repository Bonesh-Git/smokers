package com.to.frownies.controller;

import com.to.frownies.carrier.user.*;
import com.to.frownies.entity.User;
import com.to.frownies.exception.items.UserNotFoundExc;
import com.to.frownies.service.TokenService;
import com.to.frownies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("register")
    public ResRegisterCarrier registerUser(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqRegisterCarrier carrier) {
        User user = service.save(new User(carrier.username(), carrier.password(), carrier.fullName(), carrier.mobileNumber(), service.getAdminIdByToken(tokenId)));
        return new ResRegisterCarrier(tokenService.generate(user.getId()), user);
    }

    @PostMapping("registerAsAnAdmin")
    public ResRegisterCarrier registerAsAnAdmin(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqRegisterCarrier carrier) {
        User user = service.save(new User(carrier.username(), carrier.password(), carrier.fullName(), carrier.mobileNumber(), service.getAdminIdByToken(tokenId), true));
        return new ResRegisterCarrier(tokenService.generate(user.getId()), user);
    }

    @PostMapping("login")
    public ResLoginCarrier login(@RequestBody ReqLoginCarrier carrier) {
        User user = service.findByUsername(carrier.username());
        if (!user.getPassword().equals(carrier.password())) throw new UserNotFoundExc();
        return new ResLoginCarrier(tokenService.generate(user.getId()), user);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "auth-token", required = false) String tokenId) {
        tokenService.expire(tokenId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("modify")
    public ResModifyCarrier modify(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqModifyCarrier carrier) {
        User loggedInUser = service.findById(tokenService.checkValidation(tokenId));
        User targetUser = service.allowed(service.findByUsername(carrier.username()), loggedInUser);
        targetUser.setUsername(carrier.user().getUsername());
        targetUser.setPassword(carrier.user().getPassword());
        targetUser.setFullName(carrier.user().getFullName());
        targetUser.setMobileNumber(carrier.user().getMobileNumber());
        targetUser.setAddress(carrier.user().getAddress());
        targetUser.setEmail(carrier.user().getEmail());//todo: mail validation
        targetUser.setLastModifiedDate(Instant.now());
        targetUser.setLastModifiedBy(loggedInUser.getId());
        return new ResModifyCarrier(service.update(targetUser));
    }
}
