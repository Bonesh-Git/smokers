package com.bonesh.wallet.model.service;

import com.bonesh.wallet.exception.UserAlreadyExist;
import com.bonesh.wallet.model.entity.User;
import com.bonesh.wallet.model.entity.Wallet;
import com.bonesh.wallet.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final ConcurrentHashMap<String,User>activeTokens=new ConcurrentHashMap<>();


    @Autowired
    public UserService(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Transactional
    public UUID save(User user) {
        user.setWalletId(walletService.save(new Wallet(500000000000000000L)));
        if (userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExist(1,"Username already exist");

        }
        return userRepository.save(user).getId();
    }


    public User getUserByUsername(String username) {
     return    userRepository.findUserByUsername(username).orElseThrow();
    }
    public String login(String fullName, String mobileNumber, String username, String password) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getPassword().equals(password)){
                String newToken=UUID.randomUUID().toString();
                activeTokens.put(newToken,user);
                return newToken;
            }
        }
        return null;
    }
    public boolean logout(String token) {
        return activeTokens.remove(token)!=null;

    }
}
