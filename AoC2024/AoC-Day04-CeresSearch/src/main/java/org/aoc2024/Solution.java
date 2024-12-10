package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class Solution {
    public static int matchedWordsCount = 0;

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

    private int search2DGrid(char[][] puzzleGrid, int row, int col, String word) {
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        int wordsFound = 0;
        if (puzzleGrid[row][col] != word.charAt(0)) {
            return 0;
        }
        int len = word.length();
        int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
        int directions = 8;
        for (int dir = 0; dir < directions; dir++) {
            int k;
            int currX = row + x[dir];
            int currY = col + y[dir];
            for (k = 1; k < len; k++) {
                if (currX >= m || currX < 0 || currY >= n || currY < 0) {
                    break;
                }
                if (puzzleGrid[currX][currY] != word.charAt(k)) {
                    break;
                }
                currX += x[dir];
                currY += y[dir];
            }
            if (k == len) {
                wordsFound++;
            }
        }
        return wordsFound;
    }

    public void searchWord(char[][] puzzleGrid, String word) {
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matchedWordsCount += search2DGrid(puzzleGrid, i, j, word);
            }
        }

    }
}
