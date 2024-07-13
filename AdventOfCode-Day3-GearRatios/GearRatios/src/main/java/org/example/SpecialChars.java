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
        if (lineNumber > 1) {
            boolean testcheck = checkPreviousLine(lineNumber - STEP, index);
            if (testcheck) {
                return true;
            }
        }
        // check same line
        return checkSameLine(lineNumber, index);
    }

    public boolean checkPreviousLine(int lineNumber, int index) {
        if (specialChars.containsKey(lineNumber)) {
            List<Integer> charIndexListForLine = specialChars.get(lineNumber);
            return charIndexListForLine.contains(index)
                    || charIndexListForLine.contains(index - STEP)
                    || charIndexListForLine.contains(index + STEP);
        }
        return false;
    }

    public boolean checkSameLine(int lineNumber, int index) {
        if (!specialChars.isEmpty() && specialChars.containsKey(lineNumber)) {
            List<Integer> indexListForLine = specialChars.get(lineNumber);
            return indexListForLine.contains(index + STEP);
        }
        return false;
    }
}
