package com.example.collectionofembeddables1;

import com.example.collectionofembeddables1.domain.ContactAddress;
import com.example.collectionofembeddables1.domain.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class CollectionOfEmbeddables1ApplicationTests {

    @PersistenceContext
    EntityManager em;
    @Autowired
    InTransactionRunner txRunner;

    @Test
    void contextLoads() {
        txRunner.runInNewTransaction(() -> {
            User user = new User();
            user.setId(1);
            user.setUserName("J Doe");

            ContactAddress address = new ContactAddress();
            address.setCity("Las Vegas");
            address.setState("NV");

            user.setAddress(Collections.singletonList(address));
            em.persist(user);
        });

        txRunner.runInNewTransaction(() -> {
            final User user = em.find(User.class, 1);
            System.out.println(user);
        });
    }

}
