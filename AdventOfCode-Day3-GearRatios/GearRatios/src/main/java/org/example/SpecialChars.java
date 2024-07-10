package org.example;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class SpecialChars {
    // (k)line, (v)index
    private HashMap<Integer, List<Integer>> specialChars = new HashMap<>();
    // constant step - one place/line before or after the index
    private final int STEP = 1;

    public boolean isNextToSpecialChar(int lineNumber, int index) {
        // check previous line
        if (specialChars.containsKey(lineNumber - STEP)) {
            if (checkPreviousLine(lineNumber - STEP, index)) {
                return true;
            }
        }
        // check same line
        return checkSameLine(lineNumber, index);
    }

    public boolean checkPreviousLine(int lineNumber, int index) {
        List<Integer> indexListForLine = specialChars.get(lineNumber);
        return indexListForLine.contains(index)
                || indexListForLine.contains(index - STEP)
                || indexListForLine.contains(index + STEP);
    }

    public boolean checkSameLine(int lineNumber, int index) {
        List<Integer> indexListForLine = specialChars.get(lineNumber);
        return indexListForLine.contains(index + STEP);
    }
}
