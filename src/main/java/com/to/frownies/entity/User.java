package com.to.frownies.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends EditableEntity {
    @Field
    @NonNull
    private String username;
    @Field
    @NonNull
    private String password;
    @Field
    @NonNull
    private String fullName;
    @Field
    @NonNull
    private String mobileNumber;
    @Field
    private String address;
    @Field
    private String email;
    @Field
    private boolean admin = false;
    @Field
    private UUID walletId;

    public User(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String mobileNumber, UUID createdBy) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        assert createdBy != null;
        setCreatedBy(createdBy);
    }

    public User(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String mobileNumber, UUID createdBy, boolean admin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.admin = admin;
        assert createdBy != null;
        setCreatedBy(createdBy);
    }

}
