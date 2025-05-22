package com.bonesh.wallet.model.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;


import java.sql.Timestamp;
import java.util.UUID;
@Document
public abstract class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private UUID id;
    @Field
    private Timestamp createdDateTime;
    @Field
    private Timestamp lastModifiedDateTime;
    @Field
    private String createdBy;
    @Field
    private String lastModifiedBy;
    @Version
    private Long version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Timestamp getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Timestamp lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public GenericEntity(Timestamp createdDateTime, Timestamp lastModifiedDateTime, String createdBy, String lastModifiedBy) {
        this.createdDateTime = createdDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;

    }

    public GenericEntity() {
    }
}

