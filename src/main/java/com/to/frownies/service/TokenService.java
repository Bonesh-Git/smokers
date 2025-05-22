package com.to.frownies.service;

import com.to.frownies.entity.Token;
import com.to.frownies.exception.items.InvalidTokenExc;
import com.to.frownies.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
public class TokenService {

    private final TokenRepo rep;
    public final Duration duration = Duration.ofMinutes(10);

    @Autowired
    public TokenService(TokenRepo rep) {
        this.rep = rep;
        invalidate();
    }

    public String generate(UUID userId) {
        return rep.save(new Token(userId, Instant.now().plus(duration))).getId();
    }

    public void invalidate() {
        Iterable<Token> lst = rep.findAll();
        for (Token token : lst) {
            if (token.expired()) rep.delete(token);
        }
    }

    public void expire(String id) {
        if (rep.existsById(id)) rep.deleteById(id);
    }

    public UUID checkValidation(String tokenId) {
        Token token = findById(tokenId);
        if (token.expired()) {
            rep.delete(token);
            throw new InvalidTokenExc();
        }
        return token.getUserId();
    }

    public Token findById(String id) {
        return rep.findById(id).orElseThrow(InvalidTokenExc::new);
    }

}
