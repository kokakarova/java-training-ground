package org.example;

import lombok.Data;

import java.util.HashMap;

@Data
public class Numbers {
    // (k)line, (v)(k)index, (v)value
    private HashMap<Integer, HashMap<Integer, Integer>> numbersMap = new HashMap<>();
    // constant step - one place/line before or after the index
    private final int STEP = 1;

    public void addNumbersToMap(int lineNumber, int index, Integer value) {
        if (numbersMap.containsKey(lineNumber)) {
            numbersMap.get(lineNumber).put(index, value);
        } else {
            HashMap<Integer, Integer> numbersInnerMap = new HashMap<>();
            numbersInnerMap.put(index, value);
            numbersMap.put(lineNumber, numbersInnerMap);
        }
    }

    public boolean isNextToNumber(int lineNumber, int index) {
        if (numbersMap.containsKey(lineNumber)) {
            return checkThreeIndexes(lineNumber, index);
        }
        return false;
    }

    private boolean checkThreeIndexes(int lineNumber, int index) {
        HashMap<Integer, Integer> indexAndValuesForLine = numbersMap.get(lineNumber);
        return indexAndValuesForLine.containsKey(index)
                || indexAndValuesForLine.containsKey(index - STEP)
                || indexAndValuesForLine.containsKey(index + STEP);
    }
}
