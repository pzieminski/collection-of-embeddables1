package com.example.collectionofembeddables1.domain;

import com.example.collectionofembeddables1.enums.UserType;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;

import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Embedded
    Audit audit = new Audit();

    @ElementCollection
    @CollectionTable(name = "CONTACT_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    @AttributeOverride(name = "streetAddress", column = @Column(name = "STREET_ADDRESS"))
    private List<ContactAddress> address;

    @ElementCollection
    @CollectionTable(name = "Contacts", joinColumns = @JoinColumn(name = "ID"))
    @Column(name = "CONTACT_NO")
    private Collection<String> contacts;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;

    @PrePersist
    public void prePersist() {
        System.out.println("User#prePersist");
    }

}
