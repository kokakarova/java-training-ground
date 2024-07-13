package org.example;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        for (int i = lineToCharsArray.length - 1; i >= 0; i--) {
            if (lineToCharsArray[i] == '.') {
                setDecaMultiplier(0);
                setAdditionPerformedLastTurn(false);
                continue;
            }
            if (Character.isDigit(lineToCharsArray[i])) {
                handleDigits(lineNumber, i, lineToCharsArray[i]);
            } else {
                handleSpecialCharacters(lineNumber, i);
            }
        }
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

    private void handleSpecialCharacters(int lineNumber, int i) {
// check previous line
        setDecaMultiplier(0);
        setAdditionPerformedLastTurn(false);
        specialChars.addSpecialCharacterToMap(lineNumber, i);
        boolean addNumFromPrevLine = numbers.isNextToNumber(lineNumber - STEP, i);
        if (addNumFromPrevLine) {
            addNumsFromPrevLine(lineNumber - STEP, i);
        }
        boolean addNumFromSameLine = numbers.isNextToNumber(lineNumber, i);
        if (addNumFromSameLine) {
            addNumsFromSameLine(lineNumber, i);
        }
    }

    public void addDigitToSum(int lineNumber, int index, int numberValue) {
        int amountAtCurrentIndexToSet = (int) (getTotalSum() + (numberValue * Math.pow(10, decaMultiplier)));
        if (additionPerformedLastTurn || decaMultiplier == 0) {
            System.out.println("amountAtCurrentIndexToSet = " + amountAtCurrentIndexToSet);
            setTotalSum(amountAtCurrentIndexToSet);
        } else {
            System.out.println("amountAtCurrentIndexToSet = " + amountAtCurrentIndexToSet);
            setTotalSum(amountAtCurrentIndexToSet);
            HashMap<Integer, Integer> innerNumbersMap = numbers.getNumbersMap().get(lineNumber);
            System.out.println("innerNumbersMap = " + innerNumbersMap);
            System.out.println("index: " + index);
            for (int i = decaMultiplier - 1, j = 1; i >= 0; i--, j++) {
                Integer nextDigit = innerNumbersMap.get(index + j);
                int amountToSet = (int) (getTotalSum() + (nextDigit * Math.pow(10, i)));
                System.out.println("amountToSet = " + amountToSet);
                setTotalSum(amountToSet);
            }
        }
        setAdditionPerformedLastTurn(true);
    }

    public void addNumsFromPrevLine(int lineNumber, int index) {
        HashMap<Integer, Integer> digitsToAdd = new HashMap();
        HashMap<Integer, Integer> innerNumbersMap = numbers.getNumbersMap().get(lineNumber);
        boolean digitLeft = true;
        boolean digitRight = true;
        int iteration = 0;
        int localListIndex = 0;
        while (digitLeft || digitRight) {
            if (innerNumbersMap.containsKey(index - iteration)) {
                digitsToAdd.put(localListIndex, innerNumbersMap.get(index - iteration));
                localListIndex++;
            } else {
                digitLeft = false;
            }
            if (innerNumbersMap.containsKey(index + iteration)) {
                if (iteration != 0) {
                    digitsToAdd.put(localListIndex, innerNumbersMap.get(index + iteration));
                    localListIndex++;
                }
            } else {
                digitRight = false;
            }
            iteration++;
        }
        addListOfConsecutiveDigits(digitsToAdd);
    }

    private void addNumsFromSameLine(int lineNumber, int index) {
        HashMap<Integer, Integer> digitsToAdd = getListOfConsecutiveDigitsToAdd(lineNumber, (index + 1));
        System.out.println("digitsToAdd = " + digitsToAdd);
        addListOfConsecutiveDigits(digitsToAdd);
    }

    private HashMap<Integer, Integer> getListOfConsecutiveDigitsToAdd(int lineNumber, int index) {
        HashMap<Integer, Integer> numbersVar = numbers.getNumbersMap().get(lineNumber);
        HashMap<Integer, Integer> digitsToAdd = new HashMap<>();
        int iteration = 0;
        while (numbersVar.containsKey(index + iteration)) {
            digitsToAdd.put((iteration), numbersVar.get(index + iteration));
            iteration++;
        }
        return digitsToAdd;
    }

    private void addListOfConsecutiveDigits(HashMap<Integer, Integer> digitsToAdd) {
        for (int i = 0, j = digitsToAdd.size() - 1; i < digitsToAdd.size(); i++, j--) {
            setTotalSum(getTotalSum() + (digitsToAdd.get(i) * Math.pow(10, j)));
        }
    }
}
    
