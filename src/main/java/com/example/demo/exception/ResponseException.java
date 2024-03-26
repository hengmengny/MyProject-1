package com.example.demo.exception;

public class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }
}
