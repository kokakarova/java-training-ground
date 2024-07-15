package org.example;

import lombok.Data;

import java.util.HashMap;
import java.util.TreeMap;

@Data
public class Numbers {
    // (k)line, (v)(k)index, (v)value
    private HashMap<Integer, HashMap<Integer, Integer>> numbersMap = new HashMap<>();
    // constant step - one place/line before or after the index
    private final int STEP = 1;

    public void addToNumbersMap(int lineNumber, int index, Integer value) {
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
        HashMap<Integer, Integer> innerNumbersMap = numbersMap.get(lineNumber);
        return innerNumbersMap.containsKey(index)
                || innerNumbersMap.containsKey(index - STEP)
                || innerNumbersMap.containsKey(index + STEP);
    }

    public TreeMap<Integer, Integer> getListOfConsecutiveDigitsToAdd(int lineNumber, int index) {
        HashMap<Integer, Integer> numbersVar = getNumbersMap().get(lineNumber);
        TreeMap<Integer, Integer> digitsToAdd = new TreeMap<>();
        int iteration = 0;
        boolean digitLeft = true;
        boolean digitRight = true;
        while (digitLeft || digitRight) {
            if (numbersVar.containsKey(index + iteration) && digitRight) {
                digitsToAdd.put((index + iteration), numbersVar.get(index + iteration));
            } else {
                if (iteration != 0) {
                    digitRight = false;
                }
            }
            if (numbersVar.containsKey(index - iteration) && digitLeft) {
                if (iteration != 0) {
                    digitsToAdd.put((index - iteration), numbersVar.get(index - iteration));
                }
            } else {
                if (iteration != 0) {
                    digitLeft = false;
                }
            }
            iteration++;
        }
        return digitsToAdd;
    }
}
