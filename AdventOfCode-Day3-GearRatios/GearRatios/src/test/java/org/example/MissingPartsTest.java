package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingPartsTest {

    CalculateMissingParts missingParts = new CalculateMissingParts();
    Numbers numbers = new Numbers();
    SpecialChars specialChars = new SpecialChars();

//    @Test
//    void shouldReturn7() {
//        int value = 7;
//        int decaPlace = 1;
//        assertEquals(7, MissingParts.decaMultiplier(value, decaPlace));
//    }
//
//    @Test
//    void shouldReturn70() {
//        int value = 7;
//        int decaPlace = 2;
//        assertEquals(70, MissingParts.decaMultiplier(value, decaPlace));
//    }

//    @Test
//    void shouldReturn0() {
//        String testString = "..38.143...1.......";
//        missingParts.processLine(testString, 1);
//        assertEquals(3, missingParts.getNumbers().size());
//    }

//    @Test
//    void shouldReturnHashMapSize2() {
//        char[] charArray = new char[]{'1', '2'};
//        HashMap<Integer, List<Integer>> actualResult = missingParts.addElements(charArray);
//        assertEquals(2, actualResult.size());
//    }

    
//    @Test
//    void calculateMissingParts() {
//        HashMap<Integer, Integer> innerMap = new HashMap<>();
//        innerMap.put(1, 1);
//        innerMap.put(2, 1);
//        innerMap.put(3, 1);
//        HashMap<Integer, HashMap<Integer, Integer>> outerMap = new HashMap<>();
//        outerMap.put(1, innerMap);
//        System.out.println("(before) numbersMap(test) = " + numbers.getNumbersMap());
//        numbers.setNumbersMap(outerMap);
//        System.out.println("(after) numbersMap(test) = " + numbers.getNumbersMap());
//        int actualResult = missingParts.addNumsFromPrevLine(1, 3);
//        assertEquals(111, actualResult);
//    }

    @Test
    void shouldGet11466ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTest.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 11466;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet794ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialCharInPrevLine.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 794;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet850ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialChar.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 850;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet100ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialCharBeforeNum.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 100;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void shouldGet434ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialCharAfterNum.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 434;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet5760ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTest-1.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 5760;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void shouldGet7104ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTest-2.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 7104;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }
}