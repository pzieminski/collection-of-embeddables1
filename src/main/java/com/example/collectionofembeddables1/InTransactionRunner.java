/*
 * Copyright (c) 2020 OpenText Corporation. All Rights Reserved.
 * OpenText Confidential: Restricted Internal Distribution
 */
package com.example.collectionofembeddables1;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.Callable;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InTransactionRunner {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runInNewTransaction(Runnable statement) {
        statement.run();
    }

    @Transactional
    public void runInTransaction(Runnable statement) {
        statement.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T callInNewTransaction(Callable<T> statement) throws WrapperException {
        return callSilently(statement);
    }

    @Transactional
    public <T> T callInTransaction(Callable<T> statement) throws WrapperException {
        return callSilently(statement);
    }

    private <T> T callSilently(Callable<T> statement) {
        try {
            return statement.call();
        } catch (Exception e) {
            throw new WrapperException(e);
        }
    }

    public static class WrapperException extends RuntimeException {
        private WrapperException(Exception e) {
            super(Objects.requireNonNull(e));
        }

        public Exception getWrappedException() {
            return (Exception)getCause();
        }
    }
}

