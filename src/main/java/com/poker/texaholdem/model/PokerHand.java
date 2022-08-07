package com.poker.texaholdem.model;

import com.poker.texaholdem.exception.EmptyHandException;
import com.poker.texaholdem.exception.IncorrectHandLenght;
import com.poker.texaholdem.exception.UnknownCardException;

import java.util.*;

import static java.lang.Integer.compare;

public class PokerHand implements Comparable<PokerHand> {

    private final String cards;
    private final HandCombination combination;

    public PokerHand(String cards) {
        this.cards = cards;
        this.combination = defineCombination();
    }

    public HandCombination defineCombination() {

        int[] handValues = new int[13];
        int[] uniqueCardValues = new int[5];
        int uniqueCardValuesCount = 0;

        if (Objects.isNull(cards) || cards.isEmpty()) {
            throw new EmptyHandException();
        }
        String[] splitedCards = cards.split(" ");

        if (splitedCards.length != 5) {
            throw new IncorrectHandLenght();
        }

        int[] parsedCards = getCardValues(splitedCards);

        for (int card : parsedCards) {
            if (handValues[card] == 0) {
                uniqueCardValues[uniqueCardValuesCount++] = card;
            }
            handValues[card]++;
        }

        if (isTwoPairs(handValues, uniqueCardValues, uniqueCardValuesCount)) {
            return HandCombination.TWO_PAIRS;
        }

        if (isFullHouse(handValues, uniqueCardValues, uniqueCardValuesCount)) {
            return HandCombination.FULL_HOUSE;
        }

        if (isThreeOfAKind(handValues, uniqueCardValues, uniqueCardValuesCount)) {
            return HandCombination.THREE_OF_A_KIND;
        }

        if (isFourOfAKind(handValues, uniqueCardValues, uniqueCardValuesCount)) {
            return HandCombination.FOUR_OF_A_KIND;
        }

        if (isPair(handValues, uniqueCardValues, uniqueCardValuesCount)) {
            return HandCombination.PAIR;
        }

        if (isStraight(uniqueCardValues, uniqueCardValuesCount)) {
            return HandCombination.STRAIGHT;
        }
        return HandCombination.HIGHCARD;
    }

    private int[] getCardValues(String[] splitedCards) {

        int[] cardValues = new int[5];
        int count = 0;

        for (String card : splitedCards) {
            int cardLength = card.length();
            String cardValue = card.substring(0, cardLength - 1);
            switch (cardValue) {
                case "2":
                    cardValues[count++] = 0;
                    break;
                case "3":
                    cardValues[count++] = 1;
                    break;
                case "4":
                    cardValues[count++] = 2;
                    break;
                case "5":
                    cardValues[count++] = 3;
                    break;
                case "6":
                    cardValues[count++] = 4;
                    break;
                case "7":
                    cardValues[count++] = 5;
                    break;
                case "8":
                    cardValues[count++] = 6;
                    break;
                case "9":
                    cardValues[count++] = 7;
                    break;
                case "T":
                    cardValues[count++] = 8;
                    break;
                case "J":
                    cardValues[count++] = 9;
                    break;
                case "Q":
                    cardValues[count++] = 10;
                    break;
                case "K":
                    cardValues[count++] = 11;
                    break;
                case "A":
                    cardValues[count++] = 12;
                    break;
                default:
                    throw new UnknownCardException(cardValue);
            }
        }
        return cardValues;
    }

    public boolean isTwoPairs(int[] handValues, int[] uniqueCardValues, int uniqueCardValuesCount) {
        int pairCount = 0;
        for (int i = 0; i < uniqueCardValuesCount; i++) {
            if (handValues[uniqueCardValues[i]] == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    public boolean isPair(int[] handValues, int[] uniqueCardValues, int uniqueCardValuesCount) {
        boolean containPair = false;
        for (int i = 0; i < uniqueCardValuesCount; i++) {
            if (handValues[uniqueCardValues[i]] == 2) {
                containPair = true;
                break;
            }
        }
        return containPair;
    }

    public boolean isThreeOfAKind(int[] handValues, int[] uniqueCardValues, int uniqueCardValuesCount) {
        boolean containTrips = false;
        for (int i = 0; i < uniqueCardValuesCount; i++) {
            if (handValues[uniqueCardValues[i]] == 3) {
                containTrips = true;
                break;
            }
        }
        return containTrips;
    }

    public boolean isFourOfAKind(int[] handValues, int[] uniqueCardValues, int uniqueCardValuesCount) {
        boolean found = false;
        for (int i = 0; i < uniqueCardValuesCount; i++) {
            if (handValues[uniqueCardValues[i]] == 4) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean isFullHouse(int[] handValues, int[] uniqueCardValues, int uniqueCardValuesCount) {
        int pairTripsCount = 0;
        for (int i = 0; i < uniqueCardValuesCount; i++) {
            if (handValues[uniqueCardValues[i]] == 2 || handValues[uniqueCardValues[i]] == 3) {
                pairTripsCount++;
            }
        }
        return pairTripsCount == 2;
    }

    public boolean isStraight(int[] uniqueCardValues, int uniqueCardValuesCount) {

        if (uniqueCardValuesCount != 5) return false;

        Arrays.sort(uniqueCardValues);

        Integer toCompare = uniqueCardValues[0];
        for (int i = 0; i < 5; i++) {
            if (!toCompare.equals(uniqueCardValues[i])) {
                return false;
            }
            toCompare++;
        }
        return true;
    }

    @Override
    public int compareTo(PokerHand hand) {
        return compare(hand.combination.getRang(), this.combination.getRang());
    }

    public HandCombination getCombination() {
        return combination;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, combination);
    }
}
