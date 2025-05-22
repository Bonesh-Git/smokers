package com.to.frownies.carrier.transaction;

import java.util.UUID;

public record ReqWithdrawCarrier(UUID walletId, Long amount) {
}
