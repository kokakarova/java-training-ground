package org.camleCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CamelCardsTest {
    CamelCards cc = new CamelCards();
    String fileName = "puzzleExample.txt";

    @Test
    void shouldReturn_baibl_for_32T3K() {
        char[] card = "32T3K".toCharArray();
        String expectedResult = "baibl";
        assertEquals(expectedResult, cc.convertToLettersString(card));
    }

    @Test
    void shouldReturn_iddjd_for_T55J5() {
        char[] card = "T55J5".toCharArray();
        String expectedResult = "iddjd";
        assertEquals(expectedResult, cc.convertToLettersString(card));
    }

    @Test
    void shouldReturn_lleff_for_KK677() {
        char[] card = "KK677".toCharArray();
        String expectedResult = "lleff";
        assertEquals(expectedResult, cc.convertToLettersString(card));
    }

    @Test
    void calculateWinningsTest() {
        long expectedWinnings = 6440;
        cc.readFromFile(fileName);
        assertEquals(expectedWinnings, cc.calculateWinnings());
    }

    @Test
    void shouldReturn_14_2Cards3ofKind_AndOneHighCard_For_KTKKJ_8K884_78K65() {
        String line1 = "8K884 2";
        String line2 = "KTKKJ 3";
        String line3 = "78K65 1";
        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line);
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_14_For_All5ofKind_AAAAA_KKKKK_22222() {
        String line1 = "AAAAA 3"; // * 3 = 6
        String line2 = "KKKKK 2"; // * 2 = 4
        String line3 = "22222 1"; // * 1 = 1
        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line);
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_13_For_1_OnePair_And2HighCard_A2345_A2345_A2345() {
        String line1 = "A2345 3"; // * 2 = 6
        String line2 = "K234K 2"; // * 3 = 6
        String line3 = "23456 1"; // * 1 = 1
        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line);
        }
        long expectedResult = 13;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_14_For_AAAA9_22322_KQ752() {
        String line1 = "AAAA9 3"; // * 3 = 9
        String line2 = "22322 2"; // * 2 = 4
        String line3 = "KQ752 1"; // * 1 = 1

        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line);
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_14_For_AllOnePair_AAAA9_22322_KQ752() {
        String line1 = "A2342 3"; // * 3 = 9
        String line2 = "KQ75Q 2"; // * 2 = 4
        String line3 = "98A2A 1"; // * 1 = 1

        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line);
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_14_For_AllHighCard_A2342_TQ752_98A26() {
        String line1 = "A2342 3"; // * 3 = 9
        String line2 = "TQ752 2"; // * 2 = 4
        String line3 = "98A26 1"; // * 1 = 1

        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line);
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }


}