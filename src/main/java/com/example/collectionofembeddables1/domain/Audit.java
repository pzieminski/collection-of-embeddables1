package com.example.collectionofembeddables1.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Data
@Embeddable
@Slf4j
public class Audit {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date creationTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @PostLoad
    public void postLoad() {
        LOG.info("postLoad");
    }

    @PrePersist
    public void prePersist() {
        LOG.info("prePersist");
        final Date now = new Date();
        setCreationTime(now);
        setUpdatedTime(now);
    }

    @PreUpdate
    public void preUpdate() {
        LOG.info("preUpdate");
        final Date now = new Date();
        setUpdatedTime(now);
    }

    @PreRemove
    public void preRemove() {
        LOG.info("preRemove");
    }
}
