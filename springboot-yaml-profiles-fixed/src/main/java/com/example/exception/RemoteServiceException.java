package com.example.exception;

public class RemoteServiceException extends RuntimeException {
    private final int status;

    public RemoteServiceException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

