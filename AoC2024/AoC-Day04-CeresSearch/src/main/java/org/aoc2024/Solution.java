package org.aoc2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Solution {
    private static int matchedWordsCount = 0;

    public char[][] getPuzzleGridFromInput(String fileName, int rows, int cols) {
        char[][] puzzle = new char[rows][cols];
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            int rowNumber = 0;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                char[] chars = line.toCharArray();
                System.arraycopy(chars, 0, puzzle[rowNumber], 0, chars.length);
                rowNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return puzzle;
    }
}
