package com.to.frownies.carrier.transaction;

import java.util.UUID;

public record ReqDepositCarrier (UUID walletId, Long amount){
}
