package com.example.collectionofembeddables1.domain;

import com.example.collectionofembeddables1.Callbacks;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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

    @PrePersist
    public void prePersist() {
        LOG.info("ContactAddress#prePersist"); // NOT called
        Callbacks.contactAddressPrePersist();
    }
}
