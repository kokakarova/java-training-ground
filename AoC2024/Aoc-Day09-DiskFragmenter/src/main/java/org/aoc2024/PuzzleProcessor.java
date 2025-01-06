package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class PuzzleProcessor {

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

    public List<Integer> processFileCompactingPart1(List<Integer> diskList) {
        int endFileIndex = getIndexOfOneFilePartAtEnd(diskList);
        final int SPACE_MARK = -1;
        int indexOfFirstSpace = diskList.indexOf(SPACE_MARK);
        while (indexOfFirstSpace < endFileIndex) {
            diskList.set(indexOfFirstSpace, diskList.get(endFileIndex));
            diskList.set(endFileIndex, SPACE_MARK);
            endFileIndex = getIndexOfOneFilePartAtEnd(diskList);
            indexOfFirstSpace = diskList.indexOf(SPACE_MARK);
        }
        return diskList;
    }

    private int getIndexOfOneFilePartAtEnd(List<Integer> diskList) {
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

    public List<Integer> processFileCompactingPart2(List<Integer> diskList) {
        int rightMostFileId = getIndexOfOneFilePartAtEnd(diskList);
        while (rightMostFileId >= 0) {
//             diskList = exploreCompactingForFileId(rightMostFileId);
            // get right-most file chunk (indexes)
            List<Integer> wholeFileIndexes = getIndexesForFileId(diskList, rightMostFileId);
            // find fitting free space chunk from left to right (indexes)
            List<Integer> freeSpaceChunks = getAvailableFreeSpaceChunk(diskList, wholeFileIndexes.size(), diskList.indexOf(rightMostFileId));
            // rearrange file chunk and free space chunk
            if (!freeSpaceChunks.isEmpty() && freeSpaceChunks.getFirst() < wholeFileIndexes.getFirst()) {
                diskList = replaceSpaceWithFile(diskList, rightMostFileId, freeSpaceChunks);
                diskList = replaceFileIndexesWithSpace(diskList, wholeFileIndexes);
            }
            rightMostFileId--;
        }
        return diskList;
    }

    public List<Integer> replaceFileIndexesWithSpace(List<Integer> diskList, List<Integer> wholeFileIndexes) {
        for (Integer wholeFileIndex : wholeFileIndexes) {
            diskList.set(wholeFileIndex, -1);
        }
        return diskList;
    }

    public List<Integer> replaceSpaceWithFile(List<Integer> diskList, int rightMostFileId, List<Integer> freeSpaceChunks) {
        for (Integer freeSpaceChunk : freeSpaceChunks) {
            diskList.set(freeSpaceChunk, rightMostFileId);
        }
        return diskList;
    }

    public List<Integer> getAvailableFreeSpaceChunk(List<Integer> diskList, int size, int fileIdIndex) {
        int index = diskList.indexOf(-1);
        List<Integer> freeSpaceChunks = new ArrayList<>();
        while (freeSpaceChunks.size() < size && index != -1 && index < fileIdIndex) {
            freeSpaceChunks.add(index);
            index++;
            if (diskList.get(index) != -1 && freeSpaceChunks.size() < size) {
                freeSpaceChunks.clear();
                int indexOfSpaceInSublist = diskList.subList(index, diskList.size()).indexOf(-1);
                index = indexOfSpaceInSublist == -1 ? indexOfSpaceInSublist : indexOfSpaceInSublist + index;
            }
        }
        return freeSpaceChunks.size() == size ? freeSpaceChunks : new ArrayList<>();
    }

    public List<Integer> getIndexesForFileId(List<Integer> diskList, int fileId) {
        int index = diskList.indexOf(fileId);
        List<Integer> fileIndexes = new ArrayList<>();
        while (index != -1) {
            fileIndexes.add(index);
            if (index + 1 < diskList.size() && diskList.get(index + 1) == fileId) {
                index++;
            } else {
                index = -1;
            }
        }
        return fileIndexes;
    }
}
