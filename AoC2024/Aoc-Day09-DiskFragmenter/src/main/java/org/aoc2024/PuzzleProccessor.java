package org.aoc2024;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class PuzzleProccessor {

    public List<Integer> readAntennasFromInput(String fileName) {
        List<Integer> disk = new ArrayList<>();
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for (int i = 0, j = 0; i < line.length(); i++, j++) {
                    int charAsInt = Character.getNumericValue(line.charAt(i));
                    // add charAsInt to places as id
                    disk.addAll(addFileToString(j, charAsInt));
                    i++;
                    // add -1 for empty space
                    if (i < line.length() - 1) {
                        int nextCharAsInt = Character.getNumericValue(line.charAt(i));
                        if (nextCharAsInt > 0) {
                            disk.addAll(addFileToString(-1, nextCharAsInt));
                        }
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return disk;
    }

    public List<Integer> addFileToString(int numberToAdd, int timesToRepeat) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < timesToRepeat; i++) {
            newList.add(numberToAdd);
        }
        return newList;
    }

}
