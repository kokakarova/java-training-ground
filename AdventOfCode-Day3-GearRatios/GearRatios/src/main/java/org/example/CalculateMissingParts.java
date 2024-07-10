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

    public SpecialChars specialChars;
    public Numbers numbers;
    // final sum
    private double totalSum = 0;
    // constant step - one place/line before or after the index
    private final int STEP = 1;
    private int decaMultiplier = 0;
    private boolean additionPerformedLastTurn = false;
    private static final String fileName = "puzzleFile.txt";

    public void readFromFile() {
        try (InputStream file = GearRatios.class.getClassLoader().getResourceAsStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int i = 1;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                System.out.println("Line_" + i + ": " + s);
                processLine(s, i);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String stringLine, int lineNumber) {
        char[] lineToCharsArray = stringLine.toCharArray();
        for (int i = lineToCharsArray.length - 1; i >= 0; i--) {
            if (lineToCharsArray[i] == '.') {
                setDecaMultiplier(0);
                setAdditionPerformedLastTurn(false);
                continue;
            }
            if (Character.isDigit(lineToCharsArray[i])) {
                Integer intValue = Character.getNumericValue(lineToCharsArray[i]);
                boolean addToSum = specialChars.isNextToSpecialChar(lineNumber, i);
                if (addToSum || additionPerformedLastTurn) {
                    addDigitToSum(lineNumber, i, lineToCharsArray[i]);
                } else {
                    numbers.addNumbersToMap(lineNumber, i, intValue);
                }
            } else { // if it's not a '.' nor a digit, it's a special character
                // check previous line
                boolean addNumFromPrevLine = numbers.isNextToNumber(lineNumber - STEP, i);
                boolean addNumFromSameLine = numbers.isNextToNumber(lineNumber, i);
                if (addNumFromPrevLine) {
                    addNumsFromPrevLine(lineNumber, i);
                }
            }
        }
    }

    public void addDigitToSum(int lineNumber, int index, int value) {

        if (additionPerformedLastTurn) {
            setTotalSum(getTotalSum() + value * Math.pow(10, decaMultiplier));
        } else if (decaMultiplier == 0) {
            setTotalSum(getTotalSum() + value);
        } else {
            setTotalSum(getTotalSum() + value * Math.pow(10, decaMultiplier));
            HashMap<Integer, Integer> indexesAndNumberValuesForLine = numbers.getNumbersMap().get(lineNumber);
            for (int i = decaMultiplier - 1, j = 1; i >= 0; i--, j++) {
                Integer nextDigit = indexesAndNumberValuesForLine.get(index + j);
                setTotalSum(getTotalSum() + nextDigit * Math.pow(10, decaMultiplier));
            }
        }
        decaMultiplier++;
        setAdditionPerformedLastTurn(true);
    }

    private void addNumsFromPrevLine(int lineNumber, int index) {
        List<Integer> digitsToAdd = new ArrayList<>();
        HashMap<Integer, Integer> indexesAndValsForLine = numbers.getNumbersMap().get(lineNumber - STEP);
        boolean digitLeft = true;
        boolean digitRight = true;
        int iteration = 1;
        while (digitLeft || digitRight) {

        }
    }

}
    
