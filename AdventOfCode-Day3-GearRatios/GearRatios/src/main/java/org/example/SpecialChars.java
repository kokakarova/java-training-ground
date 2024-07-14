package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class SpecialChars {
    // (k)line, (v)index
    private HashMap<Integer, List<Integer>> specialChars = new HashMap<>();
    // constant step - one place/line before or after the index
    private final int STEP = 1;

    public boolean isNextToSpecialChar(int lineNumber, int index) {
        boolean charInPrevLine = false;
        if (lineNumber > 1) {
            charInPrevLine = checkPreviousLine(lineNumber - STEP, index);
        }
        return checkSameLine(lineNumber, index) || charInPrevLine;
    }

    public boolean checkPreviousLine(int lineNumber, int index) {
        if (specialChars.containsKey(lineNumber)) {
            List<Integer> specialCharsValueList = specialChars.get(lineNumber);
            return specialCharsValueList.contains(index)
                    || specialCharsValueList.contains(index - STEP)
                    || specialCharsValueList.contains(index + STEP);
        }
        return false;
    }

    public boolean checkSameLine(int lineNumber, int index) {
        if (!specialChars.isEmpty() && specialChars.containsKey(lineNumber)) {
            List<Integer> charsIndexList = specialChars.get(lineNumber);
            return charsIndexList.contains(index + STEP);
        }
        return false;
    }

    public void addSpecialCharacterToMap(int lineNumber, int index) {
        if (specialChars.containsKey(lineNumber)) {
            specialChars.get(lineNumber).add(index);
        } else {
            List<Integer> innerList = new ArrayList<>();
            innerList.add(index);
            specialChars.put(lineNumber, innerList);
        }
    }
}
