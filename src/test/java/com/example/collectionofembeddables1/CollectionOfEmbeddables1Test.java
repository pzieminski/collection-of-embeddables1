package com.example.collectionofembeddables1;

import com.example.collectionofembeddables1.domain.Audit;
import com.example.collectionofembeddables1.domain.ContactAddress;
import com.example.collectionofembeddables1.domain.User;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
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
        mockUpUser();
        mockUpAudit();
        mockUpContactAddress();

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

        new Verifications() {{
            Callbacks.userPrePersist();
            times = 1;
            Callbacks.auditPrePersist();
            times = 1;
            Callbacks.contactAddressPrePersist(); // Fails
            times = 1;

            Callbacks.userPostLoad();
            times = 0;
            Callbacks.auditPostLoad();
            times = 0;
            Callbacks.contactAddressPostLoad();
            times = 0;

            Callbacks.userPreUpdate();
            times = 0;
            Callbacks.auditPreUpdate();
            times = 0;

            Callbacks.userPreRemove();
            times = 0;
            Callbacks.auditPreRemove();
            times = 0;
        }};

        txRunner.runInNewTransaction(() -> {
            final User user = em.find(User.class, 1);
            user.setPassword("new password");
        });

        new Verifications() {{
            Callbacks.userPostLoad();
            times = 1;
            Callbacks.auditPostLoad();
            times = 1;

            Callbacks.userPreUpdate();
            times = 1;
            Callbacks.auditPreUpdate();
            times = 1;

            Callbacks.userPreRemove();
            times = 0;
            Callbacks.auditPreRemove();
            times = 0;
        }};

        txRunner.runInNewTransaction(() -> {
            final User user = em.find(User.class, 1);
            ContactAddress address = user.getAddress().get(0);
            address.setState("CA");
            em.remove(user);
        });

        new Verifications() {{
            Callbacks.userPostLoad();
            times = 2;
            Callbacks.auditPostLoad();
            times = 2;
            Callbacks.contactAddressPostLoad(); // Fails
            times = 1;

            Callbacks.userPreRemove();
            times = 1;
            Callbacks.auditPreRemove();
            times = 1;
        }};

    }

    private void mockUpUser() {
        new MockUp<User>() {

            @Mock
            public void postLoad(Invocation invocation) {
                Callbacks.userPostLoad();
                invocation.proceed();
            }

            @Mock
            public void prePersist(Invocation invocation) {
                Callbacks.userPrePersist();
                invocation.proceed();
            }

            @Mock
            public void preUpdate(Invocation invocation) {
                Callbacks.userPreUpdate();
                invocation.proceed();
            }

            @Mock
            public void preRemove(Invocation invocation) {
                Callbacks.userPreRemove();
                invocation.proceed();
            }
        };
    }

    private void mockUpAudit() {
        new MockUp<Audit>() {

            @Mock
            public void postLoad(Invocation invocation) {
                Callbacks.auditPostLoad();
                invocation.proceed();
            }

            @Mock
            public void prePersist(Invocation invocation) {
                Callbacks.auditPrePersist();
                invocation.proceed();
            }

            @Mock
            public void preUpdate(Invocation invocation) {
                Callbacks.auditPreUpdate();
                invocation.proceed();
            }

            @Mock
            public void preRemove(Invocation invocation) {
                Callbacks.auditPreRemove();
                invocation.proceed();
            }
        };
    }

    private void mockUpContactAddress() {
        new MockUp<ContactAddress>() {

            @Mock
            public void postLoad(Invocation invocation) {
                Callbacks.contactAddressPostLoad();
                invocation.proceed();
            }

            @Mock
            public void prePersist(Invocation invocation) {
                Callbacks.contactAddressPrePersist();
                invocation.proceed();
            }
        };
    }
}
