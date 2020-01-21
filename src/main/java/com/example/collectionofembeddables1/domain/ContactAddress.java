package com.example.collectionofembeddables1.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

@Data
@Embeddable
public class ContactAddress {

    @Column(name = "STREET")
    private String streetAddress;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP_CODE")
    private String areaCode;

    @Column(name = "ADDR_TYPE")
    private String addressType;

    @PrePersist
    public void prePersist() {
        System.out.println("ContactAddress#prePersist");
    }
}
