package org.example;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class BrokenGears {
    private final char GEAR_SYMBOL = '*';
    private final int STEP = 1;
    private long totalSum = 0L;
    private boolean matchFound = false;
    private int decaMultiplier = 0;
    private int tempNumber = 0;
    private int tempGearMatchIndex = -1;
    private int tempLineNmber = -1;
    HashMap<Integer, HashMap<Integer, List<Integer>>> gearSymbols = new HashMap<>();
    public Numbers numbers = new Numbers();
    public CalculateMissingParts missingParts = new CalculateMissingParts();
    public SpecialChars specialChars = new SpecialChars();

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
        for (int i = lineToCharsArray.length - 1; i >= 0; i--) {
            if (lineToCharsArray[i] == GEAR_SYMBOL) {
                handleGearSymbol(lineNumber, i);
                resetControlVariables();
                continue;
            }
            if (Character.isDigit(lineToCharsArray[i])) {
                handleDigits(lineNumber, i, lineToCharsArray[i]);
                if (i == 0) {
                    addGearSymbolToMap(tempLineNmber, tempGearMatchIndex, List.of(tempNumber));
                    resetControlVariables();
                }
                continue;
            } else {
                if (matchFound) {
                    addGearSymbolToMap(tempLineNmber, tempGearMatchIndex, List.of(tempNumber));
                }
                resetControlVariables();
                continue;
            }
        }
    }

    private void resetControlVariables() {
        matchFound = false;
        decaMultiplier = 0;
        tempNumber = 0;
        tempGearMatchIndex = -1;
        tempLineNmber = -1;
    }

    private void handleGearSymbol(int lineNumber, int index) {
        if (matchFound) {
            addGearSymbolToMap(tempLineNmber, index, List.of(tempNumber));
        }
        List<Integer> numsFromPrevLine = getAdjacentNumbers(lineNumber, -STEP, index);
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
        if (numsFromPrevLine.isEmpty() && numsFromCurrentLine.isEmpty()) {
            addGearSymbolToMap(lineNumber, index, new ArrayList<>());
        }
    }

    private List<Integer> getAdjacentNumbers(int lineNumber, int lineIndicator, int index) {
        List<Integer> result = new ArrayList<>();
        int line = lineNumber + lineIndicator;
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

    private void addGearSymbolToMap(int lineNumber, int index, List<Integer> adjacentNumbers) {
        HashMap<Integer, List<Integer>> symbols = new HashMap<>();
        if (gearSymbols.isEmpty()) {
            symbols.put(index, adjacentNumbers);
            gearSymbols.put(lineNumber, symbols);
        } else {
            if (gearSymbols.containsKey(lineNumber)) {
                if (gearSymbols.get(lineNumber).containsKey(index)) {
                    gearSymbols.get(lineNumber).get(index).addAll(adjacentNumbers);
                } else {
                    gearSymbols.get(lineNumber).put(index, adjacentNumbers);
                }
            } else {
                HashMap<Integer, List<Integer>> innerMap = new HashMap<>();
                innerMap.put(index, adjacentNumbers);
                gearSymbols.put(lineNumber, innerMap);
            }
        }
    }

    private int getTheNumbers(int lineNumber, int index) {
        TreeMap<Integer, Integer> numbersToAdd = numbers.getListOfConsecutiveDigitsToAdd(lineNumber, index);
        return getFullNumber(numbersToAdd);
    }

    private int getFullNumber(TreeMap<Integer, Integer> numbersToAdd) {
        int finalNumber = 0;
        int powMultiplier = numbersToAdd.size() - 1;
        for (var entry : numbersToAdd.entrySet()) {
            int entryValue = entry.getValue();
            finalNumber += entryValue * (int) Math.pow(10, powMultiplier);
            powMultiplier--;
        }
        return finalNumber;
    }

    private void handleDigits(int lineNumber, int index, char digit) {
        int charIntValue = Character.getNumericValue(digit);
        int previousLineNumber = lineNumber - STEP;
        int gearIndexPrevLine = getAdjacentGearSymbol(previousLineNumber, index);
        if (gearIndexPrevLine != -1) {
            matchFound = true;
            tempGearMatchIndex = gearIndexPrevLine;
            tempLineNmber = previousLineNumber;
        } else {
            int gearIndexSameLine = getAdjacentGearSymbol(lineNumber, index);
            if (gearIndexSameLine != -1) {
                matchFound = true;
                tempGearMatchIndex = gearIndexSameLine;
                tempLineNmber = lineNumber;
            } else if (!matchFound) {
                numbers.addToNumbersMap(lineNumber, index, charIntValue);
            }
        }
        tempNumber += charIntValue * (int) Math.pow(10, decaMultiplier);
        decaMultiplier++;
    }

    public int getAdjacentGearSymbol(int lineNumber, int index) {
        int gearIndex = -1;
        if (gearSymbols.containsKey(lineNumber)) {
            gearIndex = checkThreeIndexes(lineNumber, index);
        }
        return gearIndex;
    }

    private int checkThreeIndexes(int lineNumber, int index) {
        if (gearSymbols.get(lineNumber).containsKey(index)) {
            return index;
        }
        if (gearSymbols.get(lineNumber).containsKey(index - 1)) {
            return index - 1;
        }
        if (gearSymbols.get(lineNumber).containsKey(index + 1)) {
            return index + 1;
        }
        return -1;
    }

    public void calculateTotalSum() {
        System.out.println("gearSymbols = " + gearSymbols);
        for (var entry : gearSymbols.values()) {
            System.out.println("entry = " + entry);
            var toList = entry.values().stream().flatMap(Collection::parallelStream).toList();
            System.out.println("toList = " + toList);
            int multiplicationResult = 1;
            if (toList.size() == 2) {
                for (int i : toList) {
                    multiplicationResult *= i;
                }
                setTotalSum((getTotalSum() + multiplicationResult));
            }
            System.out.println("in calculateTotalSum(): " + getTotalSum());
        }
    }
}
