package org.MirageMaintenance;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class Mirage {

    public int readFromFile(String fileName, int part) {
        int sum = 0;
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                List<Integer> currentSequence = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
                sum += getPrediction(currentSequence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int getPrediction(List<Integer> startingSequence) {
        Map<Integer, List<Integer>> sequencies = new HashMap<>();
        int index = 0;
        int prediction = 0;
        sequencies.put(index, startingSequence);
        boolean sequenceOfZeros = false;
        while (!sequenceOfZeros) {
            index++;
            List<Integer> nextSequence = getNextSequence(sequencies.get(index - 1));
            sequencies.put(index, nextSequence);
            sequenceOfZeros = checkLastSequence(sequencies.get(index));
        }

        return calculatePrediction(sequencies);
    }

    private boolean checkLastSequence(List<Integer> sequence) {
        return sequence.stream().allMatch(n -> n == 0);
//        return sequence.stream().reduce(0, Integer::sum) == 0;
    }

    private List<Integer> getNextSequence(List<Integer> startingSequence) {
        List<Integer> nextSequence = new ArrayList<>();
        for (int i = 0; i < startingSequence.size() - 1; i++) {
            nextSequence.add(startingSequence.get(i + 1) - startingSequence.get(i));
        }
        return nextSequence;
    }

    private int calculatePrediction(Map<Integer, List<Integer>> sequencesMap) {
        int prediction = 0;
        int zeroSequenceIndex = sequencesMap.size() - 1;
        for (int i = zeroSequenceIndex - 1; i >= 0; i--) {
            List<Integer> currentSequence = sequencesMap.get(i);
            prediction += currentSequence.getLast();
        }
        return prediction;
    }
}
