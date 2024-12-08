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

    public int readFromFile(String fileName) {
//        int lineNumber = 0;
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            List<Integer> leftUnsorted = new ArrayList<>();
            List<Integer> rightUnsorted = new ArrayList<>();
            if (file == null) {
                System.out.println("file is null");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(file));
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    String[] bothColumns = line.split(" ");
                    leftUnsorted.add(Integer.parseInt(bothColumns[0]));
                    rightUnsorted.add(Integer.parseInt(bothColumns[bothColumns.length - 1]));
                }
                leftColumn = leftUnsorted.stream().sorted().toList();
                rightColumn = rightUnsorted.stream().sorted().toList();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calculateDistances();
    }

    private int calculateDistances() {
       int sum = 0;
       for (int i = 0; i < leftColumn.size(); i++) {
           sum += Math.abs(leftColumn.get(i) - rightColumn.get(i));
       }
       return sum;
    }

}
