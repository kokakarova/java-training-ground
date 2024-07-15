package org.example;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public void readFromFile(String fileName) throws IOException {
        try (InputStream file = GearRatios.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int i = 1;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                 processLine(s, i);
                i++;
            }
            calculateTotalSum();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            List<Integer> gearsInnerList = gearSymbols.get(lineNumber).get(index);
            if (gearsInnerList == null) {
                gearSymbols.get(lineNumber).put(index, adjacentNumbers);
            } else {
                gearsInnerList.addAll(adjacentNumbers);
                gearSymbols.get(lineNumber).put(index, gearsInnerList);
            }
        }
    }

    private List<Integer> getAdjacentNumbers(int lineNumber, int lineIndicator, int index) {
        List<Integer> result = new ArrayList<>();
        int line = lineNumber - lineIndicator;
        if (numbers.getNumbersMap().containsKey(line)) {
            HashMap<Integer, Integer> innerNumbersMap = numbers.getNumbersMap().get(line);
            if (innerNumbersMap.containsKey(index)) {
                result.add(getTheNumbers(line, index));
                return result;
            }
            if (innerNumbersMap.containsKey(index + STEP)) {
                result.add(getTheNumbers(line, (index + STEP)));
            }
            if (innerNumbersMap.containsKey(index - STEP)) {
                result.add(getTheNumbers(line, (index - STEP)));
            }
        }
        return result;
    }

    private int getTheNumbers(int lineNumber, int index) {
        TreeMap<Integer, Integer> numbersToAdd = numbers.getListOfConsecutiveDigitsToAdd(lineNumber, index);
        return getFullNumber(numbersToAdd);
    }

    private int getFullNumber(TreeMap<Integer, Integer> numbersToAdd) {
        int finalNumber = 0;
        int decaMultiplier = numbersToAdd.size() - 1;
        for (var entry : numbersToAdd.entrySet()) {
            int number = entry.getValue();
            finalNumber += number * (int) Math.pow(10, decaMultiplier);
            decaMultiplier--;
        }
        return finalNumber;
    }

    private void handleDigits(int lineNumber, int index, char digit) {
        int charIntValue = Character.getNumericValue(digit);
        numbers.addToNumbersMap(lineNumber, index, charIntValue);
    }

    public void calculateTotalSum() {
        System.out.println("gearSymbols = " + gearSymbols);
        for (var entry : gearSymbols.entrySet()) {
            System.out.println("entry = " + entry);
            int multiplicationResult = 1;
            System.out.println("entry.getValue() - " + entry.getValue());
            HashMap<Integer, List<Integer>> test = entry.getValue();
            System.out.println("test = " + test);
            for (int i = 0; i < test.size(); i++) {
                if (test.get(3).size() == 2) {
                    List<Integer> numbers = test.get(3);
                    for (var j : numbers) {
                        multiplicationResult *= j;
                        System.out.println("multiplicationResult = " + multiplicationResult);
                    }
                }
            }
            setTotalSum((getTotalSum() + multiplicationResult));
            System.out.println("in calculateTotalSum(): " + getTotalSum());
        }
    }
}
