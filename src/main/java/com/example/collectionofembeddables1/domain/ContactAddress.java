package com.example.collectionofembeddables1.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

@Data
@Embeddable
@Slf4j
public class ContactAddress {

    @Column(name = "street")
    private String streetAddress;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String areaCode;

    @Column(name = "addr_type")
    private String addressType;

    @PostLoad
    public void postLoad() {
        LOG.info("postLoad"); // NOT called
    }

    @PrePersist
    public void prePersist() {
        LOG.info("prePersist"); // NOT called
    }
}
