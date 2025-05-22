package com.bonesh.wallet.model.carrier.user;

import java.util.UUID;

public record UserTransactionRequestCarrier (String sourceUsername, String destinationUsername,Long amount) {
}
