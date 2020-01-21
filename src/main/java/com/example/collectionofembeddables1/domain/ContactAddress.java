package com.example.collectionofembeddables1.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

@Data
@Embeddable
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
        System.out.println("ContactAddress#prePersist"); // NOT called
    }
}
