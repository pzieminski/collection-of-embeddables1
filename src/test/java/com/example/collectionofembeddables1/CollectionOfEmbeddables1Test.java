package com.example.collectionofembeddables1;

import com.example.collectionofembeddables1.domain.ContactAddress;
import com.example.collectionofembeddables1.domain.User;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collections;

@SpringBootTest
class CollectionOfEmbeddables1Test {

    @PersistenceContext
    EntityManager em;
    @Autowired
    InTransactionRunner txRunner;

    @Test
    void test(@Mocked Callbacks callbacks) {

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
            final User u = em.find(User.class, 1);
            em.remove(u);
        });

        new Verifications() {{
            Callbacks.userPrePersist();
            times = 1;
            Callbacks.auditPrePersist();
            times = 1;
            Callbacks.contactAddressPrePersist();
            times = 1;
        }};

    }
}
