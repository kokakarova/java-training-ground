package org.scratchcard;

public class Main {
    public static void main(String[] args) {
    Winnings winnings = new Winnings();
        winnings.readFromFile("puzzle.txt");
        System.out.println("Winning is: " + Winnings.totalWinnings);
        System.out.println("Winnings.getTotalNumberOfCards() = " + Winnings.getTotalNumberOfCards());
    }
}