package com.example.wallet.security;

import java.time.Duration;
import java.time.Instant;

public class SessionToken {
    private final String username;
    private final Instant expiresAt;

    public SessionToken(String username, Duration duration) {
        this.username = username;
        this.expiresAt = Instant.now().plus(duration);
    }

    public String getUsername() {
        return username;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }
}