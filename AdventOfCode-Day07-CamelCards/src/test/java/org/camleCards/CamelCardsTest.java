package org.camleCards;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CamelCardsTest {
    CamelCards cc = new CamelCards();
    String fileName = "puzzleExample.txt";

//    @Test
//    void shouldReturn_baibl_for_32T3K() {
//        char[] card = "32T3K".toCharArray();
//        String expectedResult = "baibl";
//        assertEquals(expectedResult, cc.convertToLettersStringPart1(card));
//    }
//
//    @Test
//    void shouldReturn_iddjd_for_T55J5() {
//        char[] card = "T55J5".toCharArray();
//        String expectedResult = "iddjd";
//        assertEquals(expectedResult, cc.convertToLettersStringPart1(card));
//    }

//    @Test
//    void shouldReturn_lleff_for_KK677() {
//        char[] card = "KK677".toCharArray();
//        String expectedResult = "lleff";
//        assertEquals(expectedResult, cc.convertToLettersStringPart1(card));
//    }

    @Test
    void calculateWinningsTest_PuzzleExample_Part1() {
        long expectedWinnings = 6440;
        cc.readFromFile(fileName, "part_1");
        assertEquals(expectedWinnings, cc.calculateWinnings());
    }

    @Test
    void shouldReturn_14_2Cards3ofKind_AndOneHighCard_For_KTKKJ_8K884_78K65() {
        String line1 = "8K884 2";
        String line2 = "KTKKJ 3";
        String line3 = "78K65 1";
        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_1");
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
        assertEquals(2, cc.kind3s.size());
        assertEquals(1, cc.highCards.size());
    }

    @Test
    void shouldReturn_14_For_All5ofKind_AAAAA_KKKKK_22222() {
        String line1 = "AAAAA 3"; // * 3 = 6
        String line2 = "KKKKK 2"; // * 2 = 4
        String line3 = "22222 1"; // * 1 = 1
        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_1");
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
        assertEquals(3, cc.kind5s.size());
    }

    @Test
    void shouldReturn_13_For_1_OnePair_And2HighCard_A2345_A2345_A2345() {
        String line1 = "A2345 3"; // * 2 = 6
        String line2 = "K234K 2"; // * 3 = 6
        String line3 = "23456 1"; // * 1 = 1
        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_1");
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
            cc.processLine(line, "part_1");
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
            cc.processLine(line, "part_1");
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
            cc.processLine(line, "part_1");
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateWinningsTest_PuzzleExample_Part2() {
        long expectedWinnings = 5905;
        cc.readFromFile(fileName, "part_2");
        assertEquals(expectedWinnings, cc.calculateWinnings());
    }

    @Test
    void shouldReturn_14_For_OneJoker_In_HighCard_OnePair_ThreeOfKind_Part2() {
        String line1 = "J2242 3"; // * 3 = 9
        String line2 = "T2J52 2"; // * 2 = 4
        String line3 = "98A2J 1"; // * 1 = 1

        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_2");
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_Type_FiveOfKind_For_FourOfKind_And_1J_Part_2() {
        List<Character> card1 = cc.convertToList("2222J".toCharArray());
        List<Character> card2 = cc.convertToList("KKJKK".toCharArray());
        List<Character> card3 = cc.convertToList("J5555".toCharArray());
        Type expectedType = Type.FIVE_OF_KIND;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }

    @Test
    void shouldReturn_Type_FourOfKind_For_ThreeOfKind_And_1J_Part_2() {
        List<Character> card1 = cc.convertToList("2223J".toCharArray());
        List<Character> card2 = cc.convertToList("KKJKQ".toCharArray());
        List<Character> card3 = cc.convertToList("J5554".toCharArray());
        Type expectedType = Type.FOUR_OF_KIND;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }

    @Test
    void shouldReturn_Type_FiveOfKind_For_ThreeOfKind_And_2J_Part_2() {
        List<Character> card1 = cc.convertToList("222JJ".toCharArray());
        List<Character> card2 = cc.convertToList("KKJKJ".toCharArray());
        List<Character> card3 = cc.convertToList("J555J".toCharArray());
        List<Character> card4 = cc.convertToList("J55J5".toCharArray());
        List<Character> card5 = cc.convertToList("JJ555".toCharArray());
        Type expectedType = Type.FIVE_OF_KIND;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
        assertEquals(expectedType, cc.getHandTypePart2(card4));
        assertEquals(expectedType, cc.getHandTypePart2(card5));
    }

    @Test
    void shouldReturn_Type_FullHouse_For_TwoPair_And_1J_Part_2() {
        List<Character> card1 = cc.convertToList("22QQJ".toCharArray());
        List<Character> card2 = cc.convertToList("JK5K5".toCharArray());
        List<Character> card3 = cc.convertToList("2J255".toCharArray());
        Type expectedType = Type.FULL_HOUSE;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }


    @Test
    void shouldReturn_Type_ThreeOfKind_For_OnePair_And_1J_Part_2() {
        List<Character> card1 = cc.convertToList("2234J".toCharArray());
        List<Character> card2 = cc.convertToList("45J75".toCharArray());
        List<Character> card3 = cc.convertToList("J9K55".toCharArray());
        Type expectedType = Type.THREE_OF_KIND;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }

    @Test
    void shouldReturn_Type_FourOfKind_For_OnePair_And_2J_Part_2() {
        List<Character> card1 = cc.convertToList("223JJ".toCharArray());
        List<Character> card2 = cc.convertToList("45JJ5".toCharArray());
        List<Character> card3 = cc.convertToList("JJK55".toCharArray());
        Type expectedType = Type.FOUR_OF_KIND;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }

    @Test
    void shouldReturn_Type_FiveOfKind_For_OnePair_And_3J_Part_2() {
        List<Character> card1 = cc.convertToList("22JJJ".toCharArray());
        List<Character> card2 = cc.convertToList("J5JJ5".toCharArray());
        List<Character> card3 = cc.convertToList("JJJ55".toCharArray());
        Type expectedType = Type.FIVE_OF_KIND;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }

    @Test
    void shouldReturn_Type_OnePair_For_HighCard_And_1J_Part_2() {
        List<Character> card1 = cc.convertToList("2345J".toCharArray());
        List<Character> card2 = cc.convertToList("J2345".toCharArray());
        List<Character> card3 = cc.convertToList("2J345".toCharArray());
        Type expectedType = Type.ONE_PAIR;
        assertEquals(expectedType, cc.getHandTypePart2(card1));
        assertEquals(expectedType, cc.getHandTypePart2(card2));
        assertEquals(expectedType, cc.getHandTypePart2(card3));
    }

    @Test
    void shouldReturn_14_For_AllHighCard_A2342_TQ752_98A26_Part_2() {
        String line1 = "A2342 3"; // * 3 = 9
        String line2 = "TQ752 2"; // * 2 = 4
        String line3 = "98A26 1"; // * 1 = 1

        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_2");
        }
        long expectedResult = 14;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_14_For_AllHighCard_4KTJ4_38T4K_4T437_Part_2() {
        String line1 = "4KTJ4 3"; // * 3 = 9
        String line2 = "38T4K 2"; // * 1 = 2
        String line3 = "4T437 1"; // * 2 = 2

        String[] puzzleLines = {line1, line2, line3};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_2");
        }
        long expectedResult = 13;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_81110() {
        String highCard1 = "A234K 653";
        String highCard2 = "38T4Q 449";

        String onePair1 = "28JQ5 397";
        String onePair2 = "33T4K 449";

        String twoPairs1 = "54225 219";
        String twoPairs2 = "4ATA4 225";

        String threeOfKind1 = "4222A 543";
        String threeOfKind2 = "2J9KK 749";
        String threeOfKind3 = "JT43J 872";
        String threeOfKind4 = "7775A 318";

        String full1 = "AQAQQ 827";
        String full2 = "6JAA6 512";
        String full3 = "73377 847";

        String fourOfKind1 = "AATAJ 30";
        String fourOfKind2 = "Q8QQQ 321";
        String fourOfKind3 = "K4444 175";
        String fourOfKind4 = "99939 377";
        String fourOfKind5 = "79999 85";

        String fiveOfKind1 = "KJJKK 617";

        String[] puzzleLines = {
                highCard1,
                highCard2,
                onePair1,
                onePair2,
                twoPairs1,
                twoPairs2,
                threeOfKind1,
                threeOfKind2,
                threeOfKind3,
                threeOfKind4,
                full1,
                full2,
                full3,
                fourOfKind1,
                fourOfKind2,
                fourOfKind3,
                fourOfKind4,
                fourOfKind5,
                fiveOfKind1};
        for (String line : puzzleLines) {
            cc.processLine(line, "part_2");
        }

        long expectedResult = 81110L;
        long actualResult = cc.calculateWinnings();
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void shouldJustPrint() {
        String highCard1 = "A234K 653";
        String card1 = highCard1.split(" ")[0];
        String encoded1 = cc.convertToLettersStringPart2(cc.convertToList(card1.toCharArray()));

        String highCard2 = "38T4Q 449";
        String card2 = highCard2.split(" ")[0];
        String encoded2 = cc.convertToLettersStringPart2(cc.convertToList(card2.toCharArray()));


        String onePair1 = "28JQ5 397";
        String card3 = onePair1.split(" ")[0];
        String encoded3 = cc.convertToLettersStringPart2(cc.convertToList(card3.toCharArray()));

        String onePair2 = "33T4K 449";
        String card4 = onePair2.split(" ")[0];
        String encoded4 = cc.convertToLettersStringPart2(cc.convertToList(card4.toCharArray()));


        String twoPairs1 = "54225 219";
        String card5 = twoPairs1.split(" ")[0];
        String encoded5 = cc.convertToLettersStringPart2(cc.convertToList(card5.toCharArray()));

        String twoPairs2 = "4ATA4 225";
        String card6 = twoPairs2.split(" ")[0];
        String encoded6 = cc.convertToLettersStringPart2(cc.convertToList(card6.toCharArray()));


        String threeOfKind1 = "4222A 543";
        String card7 = threeOfKind1.split(" ")[0];
        String encoded7 = cc.convertToLettersStringPart2(cc.convertToList(card7.toCharArray()));

        String threeOfKind2 = "2J9KK 749";
        String card8 = threeOfKind2.split(" ")[0];
        String encoded8 = cc.convertToLettersStringPart2(cc.convertToList(card8.toCharArray()));

        String threeOfKind3 = "JT43J 872";
        String card9 = threeOfKind3.split(" ")[0];
        String encoded9 = cc.convertToLettersStringPart2(cc.convertToList(card9.toCharArray()));

        String threeOfKind4 = "7775A 318";
        String card10 = threeOfKind4.split(" ")[0];
        String encoded10 = cc.convertToLettersStringPart2(cc.convertToList(card10.toCharArray()));

        String full1 = "AQAQQ 827";
        String card11 = full1.split(" ")[0];
        String encoded11 = cc.convertToLettersStringPart2(cc.convertToList(card11.toCharArray()));

        String full2 = "6JAA6 512";
        String card12 = full2.split(" ")[0];
        String encoded12 = cc.convertToLettersStringPart2(cc.convertToList(card12.toCharArray()));

        String full3 = "73377 847";
        String card13 = full3.split(" ")[0];
        String encoded13 = cc.convertToLettersStringPart2(cc.convertToList(card13.toCharArray()));

        String fourOfKind1 = "AATAJ 30";
        String card14 = fourOfKind1.split(" ")[0];
        String encoded14 = cc.convertToLettersStringPart2(cc.convertToList(card14.toCharArray()));

        String fourOfKind2 = "Q8QQQ 321";
        String card15 = fourOfKind2.split(" ")[0];
        String encoded15 = cc.convertToLettersStringPart2(cc.convertToList(card15.toCharArray()));

        String fourOfKind3 = "K4444 175";
        String card16 = fourOfKind3.split(" ")[0];
        String encoded16 = cc.convertToLettersStringPart2(cc.convertToList(card16.toCharArray()));

        String fourOfKind4 = "99939 377";
        String card17 = fourOfKind4.split(" ")[0];
        String encoded17 = cc.convertToLettersStringPart2(cc.convertToList(card17.toCharArray()));

        String fourOfKind5 = "79999 85";
        String card18 = fourOfKind5.split(" ")[0];
        String encoded18 = cc.convertToLettersStringPart2(cc.convertToList(card18.toCharArray()));

        String fiveOfKind1 = "KJJKK 617";
        String card19 = fiveOfKind1.split(" ")[0];
        String encoded19 = cc.convertToLettersStringPart2(cc.convertToList(card19.toCharArray()));
    }

}