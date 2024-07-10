package org.example;

import lombok.Data;

import java.util.HashMap;

@Data
public class Numbers {
    // (k)line, (v)(k)index, (v)value
    private HashMap<Integer, HashMap<Integer, Integer>> numbersMap = new HashMap<>();

    public void addNumbersToMap(int lineNumber, int index, Integer value) {
        HashMap<Integer, Integer> numbersInnerMap = new HashMap<>();
        numbersInnerMap.put(index, value);
        numbersMap.put(lineNumber, numbersInnerMap);
    }
}
