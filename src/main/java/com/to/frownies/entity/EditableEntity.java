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
public class EditableEntity extends BaseEntity {
    @Field
    private UUID createdBy;
    @Field
    private Instant lastModifiedDate;
    @Field
    private UUID lastModifiedBy;

}
