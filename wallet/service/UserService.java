package com.example.wallet.service;

import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.*;
import com.example.wallet.repository.UserRepository;
import com.example.wallet.security.LoginTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final LoginTokenStore tokenStore;

    @Autowired
    public UserService(UserRepository userRepository, WalletService walletService, LoginTokenStore tokenStore) {
        this.userRepository = userRepository;
        this.walletService = walletService;
        this.tokenStore = tokenStore;
    }

    @Transactional
    public UUID save(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UserAlreadyExist();
        }

        user.setWalletId(walletService.save(new Wallet(100000L)));
        user.setCreatedDate(Instant.now());
        return userRepository.save(user).getId();
    }

    @Transactional
    public void update(User updated) {
        User existing = getUserById(updated.getId());

        if (existing == null) {
            throw new PessimisticLockException();
        }
        if (!existing.getVersion().equals(updated.getVersion())) {
            throw new OptimisticLockException();
        }

        userRepository.save(updated);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public UUID getAdminIdByToken(String token) {
        User admin=null;
        try{
            tokenStore.findSession(token);
            admin = getUserByUsername(tokenStore.getUsername(token));
        }catch (Exception e){
            return null;
        }
        if (!admin.isAdmin()) {
            throw new PermissionDeniedException("User does not have the permission: logout first");
        }
        return admin.getId();
    }

    public User getUserById(UUID id) {
        return userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }
}
