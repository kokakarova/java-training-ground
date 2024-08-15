package org.camleCards;

public class Main {

    public static void main(String[] args) {
        CamelCards camelCards = new CamelCards();
        final long start = System.currentTimeMillis();
        String fileName = "puzzle.txt";
        camelCards.readFromFile(fileName, "part_1");
        System.out.println("Winnings: " + camelCards.calculateWinnings());
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end - start) + "ms.");
    }
}