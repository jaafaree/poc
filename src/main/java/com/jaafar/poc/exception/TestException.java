package com.jaafar.poc.exception;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 8/27/2019 10:29 AM
 */
public class TestException extends RuntimeException {

    public TestException() {
    }

    public TestException(String message) {
        super(message);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
}
