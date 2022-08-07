package com.poker.texaholdem.model;

public enum HandCombination {
    HIGHCARD(1),
    PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_A_KIND(4),
    STRAIGHT(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7);

    private final int rang;

    HandCombination(int rang) {
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }
}