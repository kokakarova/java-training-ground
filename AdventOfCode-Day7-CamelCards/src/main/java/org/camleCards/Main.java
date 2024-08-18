package org.camleCards;

public class Main {

    public static void main(String[] args) {
        CamelCards camelCardsPart1 = new CamelCards();
        CamelCards camelCardsPart2 = new CamelCards();
        final long start = System.currentTimeMillis();
        String fileName = "puzzle.txt";

        camelCardsPart1.readFromFile(fileName, "part_1");
        camelCardsPart2.readFromFile(fileName, "part_2");

        System.out.println("Winnings Part_1: " + camelCardsPart1.calculateWinnings());
        System.out.println("Winnings Part_2: " + camelCardsPart2.calculateWinnings());
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end - start) + "ms.");
    }
}