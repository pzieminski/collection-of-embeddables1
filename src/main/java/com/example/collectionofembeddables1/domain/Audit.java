/*
 * Copyright (c) 2020 OpenText Corporation. All Rights Reserved.
 * OpenText Confidential: Restricted Internal Distribution
 */
package com.example.collectionofembeddables1.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Embeddable
public class Audit {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date creationTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @PrePersist
    public void prePersist() {
        System.out.println("Audit#prePersist"); // called
    }
}
