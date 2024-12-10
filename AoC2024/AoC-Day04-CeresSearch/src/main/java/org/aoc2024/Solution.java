package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class Solution {
    public static int matchedWordsCount = 0;
//    public static int masAndSamMatches = 0;

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

    private int search2DGrid(char[][] puzzleGrid, int row, int col, String word, int partNumber) {
        if (puzzleGrid[row][col] != word.charAt(0)) {
            return 0;
        }
        if (partNumber == 1) {
            return searchGridPart1(puzzleGrid, row, col, word);

        } else {
            return searchGridPart2(puzzleGrid, row, col, word);
        }
    }

    private int searchGridPart2(char[][] puzzleGrid, int row, int col, String word) {
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        int matches = 0;
        // check two steps back, if in bound && 'S' or 'M' -> check that direction
        // check two steps ahead, if in bound && 'S' or 'M' -> check that direction
        boolean checkTwoStepsAhead = isOption(row, col + 2, m, n, puzzleGrid);
        boolean checkTwoStepsBack = isOption(row, col - 2, m, n, puzzleGrid);
        if (checkTwoStepsAhead) {
            int[] x1 = new int[]{-1, 1};
            int[] y1 = new int[]{1, 1};
            int[] x2 = new int[]{-1, 1};
            int[] y2 = new int[]{-1, -1};
            matches += getMatches(row, col, puzzleGrid, word, x1, y1, x2, y2);
        }
        if (checkTwoStepsBack) {
            int[] x1 = new int[]{-1, 1};
            int[] y1 = new int[]{-1, -1};
            int[] x2 = new int[]{-1, 1};
            int[] y2 = new int[]{1, 1};
            matches += getMatches(row, col, puzzleGrid, word, x1, y1, x2, y2);
        }
        return matches;
    }

    private int getMatches(int row, int col, char[][] puzzleGrid, String word, int[] x1, int[] y1, int[] x2, int[] y2) {
        int directions = 2;
        int matches = 0;
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        int col2;
        if (y1[0] > 0) {
            col2 = 2;
        } else {
            col2 = -2;
        }
        char thirdCharOfPairingMas = getThirdCharOfPairingMas(puzzleGrid, row, (col + col2));
        if (thirdCharOfPairingMas == 'X') {
            return matches;
        }
        int len = word.length();
        for (int dir = 0; dir < directions; dir++) {
            int k;
            int currX = row + x1[dir];
            int currY = col + y1[dir];
            int currX2 = row + x2[dir];
            int currY2 = col + col2 + y2[dir];
            for (k = 1; k < len; k++) {
                if (isOutOfBounds(currX, currY, m, n)) {
                    break;
                }
                if (puzzleGrid[currX][currY] != word.charAt(k)) {
                    break;
                }
                if (k == len - 1 && puzzleGrid[currX2][currY2] != thirdCharOfPairingMas) {
                    break;
                }
                currX += x1[dir];
                currY += y1[dir];
                currX2 += x2[dir];
                currY2 += y2[dir];
            }
            if (k == len) {
                matches++;
            }
        }
        return matches;
    }

    private boolean isOption(int row, int col, int m, int n, char[][] puzzleGrid) {
        if (isOutOfBounds(row, col, m, n)) {
            return false;
        }
        return puzzleGrid[row][col] == 'M' || puzzleGrid[row][col] == 'S';
    }

    private char getThirdCharOfPairingMas(char[][] puzzleGrid, int currX2, int currY2) {
        return puzzleGrid[currX2][currY2] == 'M'
                ? 'S' : puzzleGrid[currX2][currY2] == 'S'
                ? 'M' : 'X';
    }

    private int searchGridPart1(char[][] puzzleGrid, int row, int col, String word) {
        int[] x = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] y = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        int len = word.length();
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        int directions = 8;
        int wordsFound = 0;
        for (int dir = 0; dir < directions; dir++) {
            int k;
            int currX = row + x[dir];
            int currY = col + y[dir];
            for (k = 1; k < len; k++) {
                if (isOutOfBounds(currX, currY, m, n)) {
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

    private boolean isOutOfBounds(int currX, int currY, int m, int n) {
        return currX >= m || currX < 0 || currY >= n || currY < 0;
    }

    public void searchWord(char[][] puzzleGrid, String word, int partNumber) {
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matchedWordsCount += search2DGrid(puzzleGrid, i, j, word, partNumber);
            }
        }

    }
}
