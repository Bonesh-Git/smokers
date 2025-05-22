package com.example.wallet.security;

import com.example.wallet.exception.InvalidToken;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginTokenStore {
    private final Map<String, SessionToken> tokenMap = new ConcurrentHashMap<>();
    private final Duration tokenDuration = Duration.ofHours(1);

    public String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, new SessionToken(username, tokenDuration));
        return token;
    }

    public String getUsername(String token) {
        SessionToken session = findSession(token);
        return session != null && !session.isExpired() ? session.getUsername() : null;
    }

    public SessionToken findSession(String token) {
        if (token == null) {
            throw new InvalidToken();
        }
        SessionToken session = tokenMap.get(token);
        if (session == null || session.isExpired()) {
            throw new InvalidToken();
        }
        return session;
    }

    public void invalidate(String token) {
        tokenMap.remove(token);
    }


}