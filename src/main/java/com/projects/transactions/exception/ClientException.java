package com.projects.transactions.exception;

public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }
    public ClientException(String message, Throwable ex) {
        super(message, ex);
    }

}
