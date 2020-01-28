package com.example.collectionofembeddables1.domain;

import com.example.collectionofembeddables1.Callbacks;
import com.example.collectionofembeddables1.enums.UserType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
@Entity(name = "user")
@Slf4j
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Embedded
    private Audit audit = new Audit();

    @ElementCollection
    @CollectionTable(name = "contact_address", joinColumns = @JoinColumn(name = "user_id"))
    @AttributeOverride(name = "streetAddress", column = @Column(name = "street_address"))
    private List<ContactAddress> address;

    @ElementCollection
    @CollectionTable(name = "contact", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "contact no")
    private Collection<String> contacts;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @PrePersist
    public void prePersist() {
        LOG.info("User#prePersist"); // called
        Callbacks.userPrePersist();
    }

}
