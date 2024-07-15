package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

@Data
public class BrokenGears {
    private final char GEAR_SYMBOL = '*';
    private final int STEP = 1;
    private double totalSum = 0;
    HashMap<Integer, HashMap<Integer, List<Integer>>> gearSymbols = new HashMap<>();
    public Numbers numbers = new Numbers();
    public CalculateMissingParts missingParts = new CalculateMissingParts();

    public void processLine(String stringLine, int lineNumber) {
        char[] lineToCharsArray = stringLine.toCharArray();
        System.out.println("line -------------- : " + lineNumber);
        System.out.println("current sum: " + totalSum);
        for (int i = lineToCharsArray.length - 1; i >= 0; i--) {
            if (lineToCharsArray[i] == GEAR_SYMBOL) {
                handleGearSymbol(lineNumber, i);
            }
            if (Character.isDigit(lineToCharsArray[i])) {
                handleDigits(lineNumber, i, lineToCharsArray[i]);
            } else {
                continue;
            }
        }
    }

    private void handleGearSymbol(int lineNumber, int index) {
        addGearSymbolToMap(lineNumber, index, new ArrayList<>());
        List<Integer> numsFromPrevLine = getAdjacentNumbers(lineNumber, STEP, index);
        if (!numsFromPrevLine.isEmpty()) {
            addGearSymbolToMap(lineNumber, index, numsFromPrevLine);
            if (numsFromPrevLine.size() == 2) {
                return;
            }
        }
        List<Integer> numsFromCurrentLine = getAdjacentNumbers(lineNumber, 0, index);
        if (!numsFromCurrentLine.isEmpty()) {
            addGearSymbolToMap(lineNumber, index, numsFromCurrentLine);
        }
    }

    private void addGearSymbolToMap(int lineNumber, int index, List<Integer> adjacentNumbers) {
        HashMap<Integer, List<Integer>> symbols = new HashMap<>();
        if (gearSymbols.isEmpty()) {
            symbols.put(index, adjacentNumbers);
            gearSymbols.put(lineNumber, symbols);
        } else {
            gearSymbols.get(lineNumber).put(index, adjacentNumbers);
        }
    }

    private List<Integer> getAdjacentNumbers(int lineNumber, int step, int index) {
        List<Integer> result = new ArrayList<>();
        int line = lineNumber - STEP;
        HashMap<Integer, Integer> innerNumbersMap = numbers.getNumbersMap().get(lineNumber);
        if (innerNumbersMap.containsKey(line)) {
            if (innerNumbersMap.containsKey(index)) {
                result.add(getTheNumbers(line, index));
                return result;
            }
            if (innerNumbersMap.containsKey(index + STEP)) {
                result.add(getTheNumbers(line, index + STEP));
            }
            if (innerNumbersMap.containsKey(index - STEP)) {
                result.add(getTheNumbers(line, index - STEP));
            }
        }
        return result;
    }

    private int getTheNumbers(int lineNumber, int index) {
        TreeMap<Integer, Integer> numbersToAdd = missingParts.getListOfConsecutiveDigitsToAdd(lineNumber, index);
        return getFullNumber(numbersToAdd);
    }

    private int getFullNumber(TreeMap<Integer, Integer> numbersToAdd) {
        int result = 0;
        int decaMultiplier = numbersToAdd.size() - 1;
        for (var entry : numbersToAdd.entrySet()) {
            result += (int) Math.pow(entry.getValue(), decaMultiplier);
            decaMultiplier--;
        }
        return result;
    }

    private void handleDigits(int lineNumber, int index, char digit) {
//        if (specialChars.isNextToSpecialChar(lineNumber - STEP, index)) {
//
//        }
    }
}
