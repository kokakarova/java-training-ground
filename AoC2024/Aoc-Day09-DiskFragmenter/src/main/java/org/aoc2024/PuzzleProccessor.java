package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class PuzzleProccessor {

    public List<Integer> readFromInputFile(String fileName) {
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

    public List<Integer> processFileCompacting(List<Integer> diskList) {
        int endFileIndex = getIndexOfFileAtEnd(diskList);
        final int SPACE_MARK = -1;
        int indexOfFirstSpace = diskList.indexOf(SPACE_MARK);
        while (indexOfFirstSpace < endFileIndex) {
            diskList.set(indexOfFirstSpace, diskList.get(endFileIndex));
            diskList.set(endFileIndex, SPACE_MARK);
            endFileIndex = getIndexOfFileAtEnd(diskList);
            indexOfFirstSpace = diskList.indexOf(SPACE_MARK);
        }
        return diskList;
    }

    private int getIndexOfFileAtEnd(List<Integer> diskList) {
        for (int i = diskList.size() - 1; i >= 0; i--) {
            if (diskList.get(i) != -1) {
                return i;
            }
        }
        return -1;
    }

    public long getFilesystemCheckSum(List<Integer> diskList) {
        long checkSum = 0;
        for (int i = 0; i < diskList.size(); i++) {
            if (diskList.get(i) != -1) {
                checkSum += diskList.get(i) * i;
            }
        }
        return checkSum;
    }
}
