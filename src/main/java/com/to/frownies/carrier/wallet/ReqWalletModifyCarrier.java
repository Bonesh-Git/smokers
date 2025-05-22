package com.to.frownies.carrier.wallet;

import com.to.frownies.Enum.WalletStatus;
public record ReqWalletModifyCarrier(String username, WalletStatus status) {
}
