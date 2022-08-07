package com.poker.texaholdem;

import com.poker.texaholdem.model.HandCombination;
import com.poker.texaholdem.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    void defineCombination() {

        PokerHand fourOfAKind = new PokerHand("AS AH AC AD KD");
        assertEquals(HandCombination.FOUR_OF_A_KIND, fourOfAKind.getCombination(),
                "expected combination - FOUR_OF_A_KIND");

        PokerHand highcard = new PokerHand("2S KH 3C 4D 5D");
        assertEquals(HandCombination.HIGHCARD, highcard.getCombination(),
                "expected combination - HIGHCARD");

        PokerHand pair = new PokerHand("2S 2H 3C 4D 5D");
        assertEquals(HandCombination.PAIR, pair.getCombination(),
                "expected combination - PAIR");

        PokerHand twoPair = new PokerHand("2S 2H 3C 3D 5D");
        assertEquals(HandCombination.TWO_PAIRS, twoPair.getCombination(),
                "expected combination - TWO_PAIRS");

        PokerHand threeOfAKind = new PokerHand("2S 2H 2C 3D 5D");
        assertEquals(HandCombination.THREE_OF_A_KIND, threeOfAKind.getCombination(),
                "expected combination - THREE_OF_A_KIND");

        PokerHand straight = new PokerHand("TS JH QC KD AD");
        assertEquals(HandCombination.STRAIGHT, straight.getCombination(),
                "expected combination - STRAIGHT");

        PokerHand fullHouse = new PokerHand("2S 2H 2C KD KD");
        assertEquals(HandCombination.FULL_HOUSE, fullHouse.getCombination(),
                "expected combination - FULL_HOUSE");

    }

    @Test
    void sortCombinations() {

        List<PokerHand> handsToSort = new ArrayList<>();

        PokerHand fourOfAKind = new PokerHand("AS AH AC AD KD");
        handsToSort.add(fourOfAKind);

        PokerHand highcard = new PokerHand("2S KH 3C 4D 5D");
        handsToSort.add(highcard);

        PokerHand pair = new PokerHand("2S 2H 3C 4D 5D");
        handsToSort.add(pair);

        PokerHand twoPair = new PokerHand("2S 2H 3C 3D 5D");
        handsToSort.add(twoPair);

        PokerHand threeOfAKind = new PokerHand("2S 2H 2C 3D 5D");
        handsToSort.add(threeOfAKind);

        PokerHand straight = new PokerHand("TS JH QC KD AD");
        handsToSort.add(straight);

        PokerHand fullHouse = new PokerHand("2S 2H 2C KD KD");
        handsToSort.add(fullHouse);

        Collections.sort(handsToSort);

        int index = 0;
        assertEquals(HandCombination.FOUR_OF_A_KIND, handsToSort.get(index).getCombination(),
                "expected senior hand - FOUR_OF_A_KIND");
        index++;
        assertEquals(HandCombination.FULL_HOUSE, handsToSort.get(index).getCombination(),
                "expected next hand - FULL_HOUSE");
        index++;
        assertEquals(HandCombination.STRAIGHT, handsToSort.get(index).getCombination(),
                "expected next hand - STRAIGHT");
        index++;
        assertEquals(HandCombination.THREE_OF_A_KIND, handsToSort.get(index).getCombination(),
                "expected next hand - THREE_OF_A_KIND");
        index++;
        assertEquals(HandCombination.TWO_PAIRS, handsToSort.get(index).getCombination(),
                "expected next hand - TWO_PAIRS");
        index++;
        assertEquals(HandCombination.PAIR, handsToSort.get(index).getCombination(),
                "expected next hand - PAIR");
        index++;
        assertEquals(HandCombination.HIGHCARD, handsToSort.get(index).getCombination(),
                "expected junior hand - HIGHCARD");
    }
}