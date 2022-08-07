package com.poker.texaholdem.exception;

public class IncorrectHandLenght extends RuntimeException {
    public IncorrectHandLenght() {
        super("incomplete hand dealt");
    }
}
