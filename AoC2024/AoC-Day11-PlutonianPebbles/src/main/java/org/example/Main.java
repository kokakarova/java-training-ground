package org.example;

public class Main {
    public static void main(String[] args) {
        PuzzleProcessor pp = new PuzzleProcessor();
        int blinks = 25;
        String fileName = "puzzleInput.txt";
        pp.readFromInputFile(fileName);
        pp.blinkAtTheStones(blinks);
        int totalStones = PuzzleProcessor.stonesList.size();
        System.out.println("After " + blinks + " blinks there will be " + totalStones + " stones");
    }
}