package org.example;

import lombok.Data;

@Data
public class BrokenGears {
    private final char GEAR_SYMBOL = '*';
    private final int STEP = 1;
    private double totalSum = 0;
    public SpecialChars specialChars = new SpecialChars();
    public Numbers numbers = new Numbers();

    public void processLine(String stringLine, int lineNumber) {
        char[] lineToCharsArray = stringLine.toCharArray();
        System.out.println("line -------------- : " + lineNumber);
        System.out.println("current sum: " + totalSum);
        for (int i = lineToCharsArray.length - 1; i >= 0; i--) {
            if (lineToCharsArray[i] == '.') {
                continue;
            }
            if (Character.isDigit(lineToCharsArray[i])) {
                System.out.println("handleDigits(lineNumber, i, lineToCharsArray[i])");
            } else {
                System.out.println("handleSpecialCharacters(lineNumber, i)");
            }
        }
    }
}
