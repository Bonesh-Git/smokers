package com.to.frownies.entity;

import com.to.frownies.Enum.WalletStatus;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Wallet extends EditableEntity {
    @Field
    @NonNull
    private long balance;
    @Field
    private List<UUID> transactionList = new ArrayList<>();
    @Field
    private WalletStatus status = WalletStatus.NORMAL;

    public boolean available() {
        return status.equals(WalletStatus.NORMAL);
    }

}
