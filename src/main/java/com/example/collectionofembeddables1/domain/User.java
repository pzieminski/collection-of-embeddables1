package com.example.collectionofembeddables1.domain;

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
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

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

    @PostLoad
    public void postLoad() {
        LOG.info("postLoad");
    }

    @PrePersist
    public void prePersist() {
        LOG.info("prePersist");
    }

    @PreUpdate
    public void preUpdate() {
        LOG.info("preUpdate");
    }

    @PreRemove
    public void preRemove() {
        LOG.info("preRemove");
    }
}
