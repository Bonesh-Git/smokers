package com.to.frownies.service;

import com.to.frownies.entity.User;
import com.to.frownies.entity.Wallet;
import com.to.frownies.exception.items.*;
import com.to.frownies.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo rep;
    private final WalletService wService;
    private final TokenService tokenService;
    public final Long starterBalance = 350 * 1000L;

    @Autowired
    public UserService(UserRepo rep, WalletService wService, TokenService tokenService) {
        this.rep = rep;
        this.wService = wService;
        this.tokenService = tokenService;
    }

    @Transactional
    public User save(User user) {
        if (rep.existsUserByUsername(user.getUsername())) throw new DupUserExc();
        user.setWalletId(wService.save(new Wallet(starterBalance)));
        user.setCreatedDate(Instant.now());
        return rep.save(user);
    }

    @Transactional
    public User update(User user) {
        User existing = findById(user.getId());
        if (existing == null) throw new PessimisticLockExc();
        if (!existing.getVersion().equals(user.getVersion())) throw new OptimisticLockExc();
        return rep.save(user);
    }

    public UUID getAdminIdByToken(String tokenId) {
        User admin;
        try {
            admin = findById(tokenService.checkValidation(tokenId));
        } catch (Exception e) {
            return null;
        }
        if (!admin.isAdmin()) throw new UserNotAllowedExc().setDetail("Logout first");
        return admin.getId();
    }

    public UUID findWalletIdByToken(String tokenId) {
        return findById(tokenService.checkValidation(tokenId)).getWalletId();
    }

    public User findByUsername(String username) {
        return rep.findUserByUsername(username).orElseThrow(UserNotFoundExc::new);
    }

    public User findById(UUID id) {
        return rep.findUserById(id).orElseThrow(UserNotFoundExc::new);
    }

    public User allowed(User targetUser, User loggedInUser) {
        if (!loggedInUser.getId().equals(targetUser.getId())) {
            if (targetUser.isAdmin() || !loggedInUser.isAdmin()) {
                throw new UserNotAllowedExc();
            }
        }
        return targetUser;
    }
}
