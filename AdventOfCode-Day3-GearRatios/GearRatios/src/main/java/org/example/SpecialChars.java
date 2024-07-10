package org.example;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class SpecialChars {
    // (k)line, (v)index
    private HashMap<Integer, List<Integer>> specialChars = new HashMap<>();

    public boolean isNextToSpecialChar(int lineNumber, int index) {
        // check previous line
        if (specialChars.containsKey(lineNumber - 1)) {
            if (checkThreeIndexes(lineNumber - 1, index)) {
                return true;
            }
        }
        // check same line
        return checkThreeIndexes(lineNumber, index);
    }

    public boolean checkThreeIndexes(int lineNumber, int index) {
        List<Integer> indexListForLine = specialChars.get(lineNumber);
        return indexListForLine.contains(index)
                || indexListForLine.contains(index - 1)
                || indexListForLine.contains(index + 1);
    }
}
