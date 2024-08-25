package org.MirageMaintenance;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Mirage {

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
        return sequence.stream().reduce(0, Integer::sum) == 0;
    }

    private List<Integer> getNextSequence(List<Integer> startingSequence) {
        List<Integer> nextSequence = new ArrayList<>();
        for (int i = 0; i < startingSequence.size() - 1; i++) {
            nextSequence.add(startingSequence.get(i + 1) - startingSequence.get(i));
        }
        System.out.println("nextSequence = " + nextSequence);
        return nextSequence;
    }

    private int calculatePrediction(Map<Integer, List<Integer>> sequences) {
        int prediction = 0;
        int lastIndex = sequences.size() - 1;
        for (int i = lastIndex - 1; i >= 0; i--) {
            List<Integer> currentSequence = sequences.get(i);
            prediction += currentSequence.getLast();
        }
        return prediction;
    }
}
