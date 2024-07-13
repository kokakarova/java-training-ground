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
    // final sum
    private double totalSum = 0;
    // constant step - one place/line before or after the index
    private final int STEP = 1;
    private int decaMultiplier = 0;
    private boolean additionPerformedLastTurn = false;
//    private static final String fileName = "puzzleFile.txt";

    public void readFromFile(String fileName) throws IOException {
        try (InputStream file = GearRatios.class.getClassLoader().getResourceAsStream(fileName)) {
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

            } else { // if it's not a '.' nor a digit, it's a special character
                // check previous line
                HashMap<Integer, List<Integer>> specialCharsMap = specialChars.getSpecialChars();
                if (specialCharsMap.containsKey(lineNumber)) {
                    List<Integer> innerList = specialCharsMap.get(lineNumber);
                    innerList.add(i);
                    specialCharsMap.put(lineNumber, innerList);
                    specialChars.setSpecialChars(specialCharsMap);
                } else {
                    List<Integer> innerList = new ArrayList<>();
                    innerList.add(i);
                    specialCharsMap.put(lineNumber, innerList);
                    specialChars.setSpecialChars(specialCharsMap);
                }
                boolean addNumFromPrevLine = numbers.isNextToNumber(lineNumber - STEP, i);
                if (addNumFromPrevLine) {
                    addNumsFromPrevLine(lineNumber - STEP, i);
                }
                boolean addNumFromSameLine = numbers.isNextToNumber(lineNumber, i);
                if (addNumFromSameLine) {
                    addNumsFromSameLine(lineNumber, i);
                }
            }
        }
    }

    public void addDigitToSum(int lineNumber, int index, int value) {
        int amountAtCurrentIndexToSet = (int) (getTotalSum() + value * Math.pow(10, decaMultiplier));
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
                int amountToSet = (int) (getTotalSum() + nextDigit * Math.pow(10, i));
                System.out.println("amountToSet = " + amountToSet);
                setTotalSum(amountToSet);
            }
        }
        setAdditionPerformedLastTurn(true);
    }

    public void addNumsFromPrevLine(int lineNumber, int index) {
        HashMap<Integer, Integer> digitsToAdd = new HashMap();
        var numbersVar = numbers.getNumbersMap();
        HashMap<Integer, Integer> innerNumbersMap = numbersVar.get(lineNumber);
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
        if (!digitsToAdd.isEmpty()) {
            System.out.println("In addNumsFromPrevLine: ");
            System.out.println("digitsToAdd = " + digitsToAdd);
            for (int i = 0, j = digitsToAdd.size() - 1; i < digitsToAdd.size(); i++, j--) {
                int amountToSet = (int) (getTotalSum() + digitsToAdd.get(i) * Math.pow(10, j));
                System.out.println("amountToSet = " + amountToSet);
                setTotalSum(amountToSet);
            }
        }
    }

    private void addNumsFromSameLine(int lineNumber, int index) {
        HashMap<Integer, Integer> digitsToAdd = new HashMap();
        HashMap<Integer, Integer> numbersVar = numbers.getNumbersMap().get(lineNumber);
        int iteration = 1;
        while (numbersVar.containsKey(index + iteration)) {
            digitsToAdd.put((iteration - 1), numbersVar.get(index + iteration));
            iteration++;
        }
        System.out.println("digitsToAdd = " + digitsToAdd);
        for (int i = 0, j = digitsToAdd.size() - 1; i < digitsToAdd.size(); i++, j--) {
            setTotalSum(getTotalSum() + (digitsToAdd.get(i) * Math.pow(10, j)));
        }
    }

    private void handleDigits(int lineNumber, int index, char digitChar) {
        int charIntValue = Character.getNumericValue(digitChar);
        if (specialChars.isNextToSpecialChar(lineNumber, index)
                || additionPerformedLastTurn) {
            addDigitToSum(lineNumber, index, charIntValue);
        } else {
            numbers.addNumbersToMap(lineNumber, index, charIntValue);
        }
        decaMultiplier++;
    }
}
    
