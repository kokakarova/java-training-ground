package org.example;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeMap;

@Data
public class CalculateMissingParts {

    public SpecialChars specialChars = new SpecialChars();
    public Numbers numbers = new Numbers();
    private double totalSum = 0;
    private final int STEP = 1;
    private int decaMultiplier = 0;
    private boolean additionPerformedLastTurn = false;

    public void readFromFile(String fileName) throws IOException {
        try (InputStream file = GearRatios.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int i = 1;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                processLine(s, i);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String stringLine, int lineNumber) {
        char[] lineToCharsArray = stringLine.toCharArray();
        System.out.println("line -------------- : " + lineNumber);
        System.out.println("current sum: " + totalSum);
        for (int i = lineToCharsArray.length - 1; i >= 0; i--) {
            if (lineToCharsArray[i] == '.') {
                handleDot();
                continue;
            }
            if (Character.isDigit(lineToCharsArray[i])) {
                handleDigits(lineNumber, i, lineToCharsArray[i]);
            } else {
                handleSpecialCharacters(lineNumber, i);
            }
        }
    }

    private void handleDot() {
        setDecaMultiplier(0);
        setAdditionPerformedLastTurn(false);
    }

    private void handleDigits(int lineNumber, int index, char digitChar) {
        int charIntValue = Character.getNumericValue(digitChar);
        if (specialChars.isNextToSpecialChar(lineNumber, index) || additionPerformedLastTurn) {
            addDigitToSum(lineNumber, index, charIntValue);
        } else {
            numbers.addToNumbersMap(lineNumber, index, charIntValue);
        }
        decaMultiplier++;
    }

    private void handleSpecialCharacters(int lineNumber, int index) {
// check previous line
        setDecaMultiplier(0);
        setAdditionPerformedLastTurn(false);
        specialChars.addSpecialCharacterToMap(lineNumber, index);
        if (numbers.isNextToNumber(lineNumber - STEP, index)) {
            addNumsFromPrevLine(lineNumber - STEP, index);
        }
        if (numbers.isNextToNumber(lineNumber, index)) {
            addNumsFromSameLine(lineNumber, index);
        }
    }

    public void addDigitToSum(int lineNumber, int index, int numberValue) {
        double amountAtCurrentIndexToSet = (getTotalSum() + (numberValue * Math.pow(10, decaMultiplier)));
        if (additionPerformedLastTurn || decaMultiplier == 0) {
            setTotalSum(amountAtCurrentIndexToSet);
        } else {
            setTotalSum(amountAtCurrentIndexToSet);
            HashMap<Integer, Integer> innerNumbersMap = numbers.getNumbersMap().get(lineNumber);
            for (int i = decaMultiplier - 1, j = 1; i >= 0; i--, j++) {
                Integer nextDigit = innerNumbersMap.get(index + j);
                double amountToSet = getTotalSum() + (nextDigit * Math.pow(10, i));
                setTotalSum(amountToSet);
            }
        }
        setAdditionPerformedLastTurn(true);
    }

    public void addNumsFromPrevLine(int lineNumber, int index) {
        HashMap<Integer, Integer> innerNumbersMap = numbers.getNumbersMap().get(lineNumber);
        TreeMap<Integer, Integer> digitsToAdd;
        if (innerNumbersMap.containsKey(index)) {
            digitsToAdd = numbers.getListOfConsecutiveDigitsToAdd(lineNumber, index);
            addListOfConsecutiveDigits(digitsToAdd);
            removeFromNumbersMap(lineNumber, digitsToAdd);
            return;
        }
        if (innerNumbersMap.containsKey(index - STEP)) {
            digitsToAdd = numbers.getListOfConsecutiveDigitsToAdd(lineNumber, index - STEP);
            addListOfConsecutiveDigits(digitsToAdd);
            removeFromNumbersMap(lineNumber, digitsToAdd);
        }
        if (innerNumbersMap.containsKey(index + STEP)){
            digitsToAdd = numbers.getListOfConsecutiveDigitsToAdd(lineNumber, index + STEP);
            addListOfConsecutiveDigits(digitsToAdd);
            removeFromNumbersMap(lineNumber, digitsToAdd);
        }
    }

    private void removeFromNumbersMap(int lineNumber, TreeMap<Integer, Integer> indexesToRemoveFromNumbers) {
        indexesToRemoveFromNumbers.forEach((i, v) -> numbers.getNumbersMap().get(lineNumber).remove(i));
    }

    private void addNumsFromSameLine(int lineNumber, int index) {
        TreeMap<Integer, Integer> digitsToAdd = numbers.getListOfConsecutiveDigitsToAdd(lineNumber, (index + 1));
        addListOfConsecutiveDigits(digitsToAdd);
    }

    private void addListOfConsecutiveDigits(TreeMap<Integer, Integer> digitsToAdd) {
        int j = digitsToAdd.size() - 1;
        for (Integer i : digitsToAdd.keySet()) {
            setTotalSum(getTotalSum() + (digitsToAdd.get(i) * Math.pow(10, j)));
            j--;
        }
    }
}
    
