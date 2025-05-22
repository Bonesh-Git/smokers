package com.to.frownies.carrier.user;

import com.to.frownies.entity.User;

public record ResLoginCarrier(String tokenId, User user) {
}
