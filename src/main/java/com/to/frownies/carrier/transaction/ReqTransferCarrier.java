package com.to.frownies.carrier.transaction;

import java.util.UUID;

public record ReqTransferCarrier(UUID sourceWalletId, UUID destinationWalletId, Long amount){
}
