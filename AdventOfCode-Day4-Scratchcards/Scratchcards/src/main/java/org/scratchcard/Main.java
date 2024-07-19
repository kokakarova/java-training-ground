package org.scratchcard;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    Winnings winnings = new Winnings();
        winnings.readFromFile("puzzle.txt");
        System.out.println("Winning is: " + Winnings.totalWinnings);
    }
}