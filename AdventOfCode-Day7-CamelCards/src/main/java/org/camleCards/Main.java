package org.camleCards;

public class Main {

    public static void main(String[] args) {
        CamelCards camelCardsPart1 = new CamelCards();
        CamelCards camelCardsPart2 = new CamelCards();
        final long start = System.currentTimeMillis();
        String fileName = "puzzle.txt";

        camelCardsPart1.readFromFile(fileName, "part_1");
        camelCardsPart2.readFromFile(fileName, "part_2");

        System.out.println("Part_1, HIGH_CARD: " + camelCardsPart1.highCards.size());
        System.out.println("Part_1, ONE_PAIR: " + camelCardsPart1.onePairs.size());
        System.out.println("Part_1, TWO_PAIRS: " + camelCardsPart1.twoPairs.size());
        System.out.println("Part_1, THREE_OF_KIND: " + camelCardsPart1.kind3s.size());
        System.out.println("Part_1, FULL: " + camelCardsPart1.fulls.size());
        System.out.println("Part_1, FOUR_OF_KIND: " + camelCardsPart1.kind4s.size());
        System.out.println("Part_1, FIVE_OF_KIND: " + camelCardsPart1.kind5s.size());

        System.out.println("Part_2, HIGH_CARD: " + camelCardsPart2.highCards.size());
        System.out.println("Part_2, ONE_PAIR: " + camelCardsPart2.onePairs.size());
        System.out.println("Part_2, TWO_PAIRS: " + camelCardsPart2.twoPairs.size());
        System.out.println("Part_2, THREE_OF_KIND: " + camelCardsPart2.kind3s.size());
        System.out.println("Part_2, FULL: " + camelCardsPart2.fulls.size());
        System.out.println("Part_2, FOUR_OF_KIND: " + camelCardsPart2.kind4s.size());
        System.out.println("Part_2, FIVE_OF_KIND: " + camelCardsPart2.kind5s.size());

        System.out.println("Winnings Part_1: " + camelCardsPart1.calculateWinnings());
        System.out.println("Winnings Part_2: " + camelCardsPart2.calculateWinnings());
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end - start) + "ms.");
    }
}