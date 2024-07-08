package org.example;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MissingParts {

    // (k)line, (v)index
    private HashMap<Integer, List<Integer>> specialChars = new HashMap<>();
    // (k)line, (v)(k)index, (v)value
    private HashMap<Integer, HashMap<Integer, Integer>> numbers = new HashMap<>();
    // final sum
    private double totalSum = 0;
    // constant step - one place/line before or after the index
    private final int STEP = 1;
    private int decaMultiplier = 0;
    private boolean additionPerformedLastTurn = false;

    public void addDigitToSum(int lineNumber, int index, int value) {

        if (additionPerformedLastTurn) {
            setTotalSum(getTotalSum() + value * Math.pow(10, decaMultiplier));
        } else if (decaMultiplier == 0) {
            setTotalSum(getTotalSum() + value);
        } else {
            setTotalSum(getTotalSum() + value * Math.pow(10, decaMultiplier));
            HashMap<Integer, Integer> indexesAndNumberValuesForLine = numbers.get(lineNumber);
            for (int i = decaMultiplier - 1, j = 1; i >= 0; i--, j++) {
                Integer nextDigit = indexesAndNumberValuesForLine.get(index + j);
                setTotalSum(getTotalSum() + nextDigit * Math.pow(10, decaMultiplier));
            }
        }
        decaMultiplier++;
        setAdditionPerformedLastTurn(true);
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
                boolean addToSum = isNextToSpecialChar(lineNumber, i);
                if (addToSum) {
                    addDigitToSum(lineNumber, i, lineToCharsArray[i]);
                } else {
                    addNumbersToMap(lineNumber, i, intValue);
                }
            } else { // if it's not a '.' nor a digit, it's a special character

            }
        }
    }

    private boolean isNextToSpecialChar(int lineNumber, int index) {
        // check previous line
        if (specialChars.containsKey(lineNumber - 1)) {
            if (checkThreeIndexes(lineNumber - 1, index)) {
                return true;
            }
        }
        // check same line
        return checkThreeIndexes(lineNumber, index);
    }

    private boolean checkThreeIndexes(int lineNumber, int index) {
        List<Integer> indexListForLine = specialChars.get(lineNumber);
        return indexListForLine.contains(index)
                || indexListForLine.contains(index - 1)
                || indexListForLine.contains(index + 1);
    }

    private void addNumbersToMap(int lineNumber, int index, Integer value) {
        HashMap<Integer, Integer> indexAndValueMap = new HashMap<>();
        indexAndValueMap.put(index, value);
        numbers.put(lineNumber, indexAndValueMap);
    }
}
    
