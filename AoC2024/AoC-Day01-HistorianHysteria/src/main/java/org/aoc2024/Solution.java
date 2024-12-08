package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class Solution {

    private List<Integer> leftColumn = new ArrayList<>();
    private List<Integer> rightColumn = new ArrayList<>();

    public int getSolution(String fileName, int partNumber) {
        sortPuzzleInput(fileName);
        if (partNumber == 1) {
            return calculateDistances();
        } else if (partNumber == 2) {
            return getSimilarityScore();
        } else {
            throw new IllegalArgumentException("Invalid part number: " + partNumber + ". Part number can only be 1 or 2");
        }
    }

    public void sortPuzzleInput(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            List<Integer> leftUnsorted = new ArrayList<>();
            List<Integer> rightUnsorted = new ArrayList<>();
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] bothColumns = line.split(" ");
                leftUnsorted.add(Integer.parseInt(bothColumns[0]));
                rightUnsorted.add(Integer.parseInt(bothColumns[bothColumns.length - 1]));
            }
                leftColumn = leftUnsorted.stream().sorted().toList();
                rightColumn = rightUnsorted.stream().sorted().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateDistances() {
        int sum = 0;
        for (int i = 0; i < leftColumn.size(); i++) {
            sum += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }
        return sum;
    }

    private int getSimilarityScore() {
        int sum = 0;
        int prevNumber = -1;
        int prevNumberMultiplied = -1;
        List<Integer> rightColumnMutable = new ArrayList<>(rightColumn);
        for (Integer integer : leftColumn) {
            if (integer != prevNumber) {
                prevNumber = integer;
                int startRightColumnSize = rightColumnMutable.size();
                List<Integer> numbersToRemove = List.of(integer);
                rightColumnMutable.removeAll(numbersToRemove);
                prevNumberMultiplied = integer * (startRightColumnSize - rightColumnMutable.size());
            }
            sum += prevNumberMultiplied;
        }
        return sum;
    }
}
