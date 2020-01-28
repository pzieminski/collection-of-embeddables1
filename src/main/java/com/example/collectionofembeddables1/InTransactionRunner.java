package com.example.collectionofembeddables1;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InTransactionRunner {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runInNewTransaction(Runnable statement) {
        statement.run();
    }

}

