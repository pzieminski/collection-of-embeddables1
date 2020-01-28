/*
 * Copyright (c) 2020 OpenText Corporation. All Rights Reserved.
 * OpenText Confidential: Restricted Internal Distribution
 */
package com.example.collectionofembeddables1.domain;

import com.example.collectionofembeddables1.Callbacks;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Embeddable
@Slf4j
public class Audit {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date creationTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @PrePersist
    public void prePersist() {
        LOG.info("Audit#prePersist"); // called
        Callbacks.auditPrePersist();
    }
}
