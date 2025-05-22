package com.to.frownies.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.UUID;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction extends BaseEntity {
    @Field
    private long amount;
    @Field
    private UUID sourceWallet;
    @Field
    private UUID destinationWallet;

    public Transaction(long amount, UUID sourceWallet, UUID destinationWallet, Instant createdDate) {
        this.amount = amount;
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
        setCreatedDate(createdDate);
    }

}
