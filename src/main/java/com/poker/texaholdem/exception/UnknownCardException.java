package com.poker.texaholdem.exception;

public class UnknownCardException extends RuntimeException {
    public UnknownCardException(String message) {
        super("unknown card dealt :" + message);
    }
}
