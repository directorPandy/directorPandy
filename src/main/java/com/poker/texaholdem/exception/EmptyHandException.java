package com.poker.texaholdem.exception;

public class EmptyHandException extends RuntimeException {
    public EmptyHandException() { super("Hand is empty"); }
}
