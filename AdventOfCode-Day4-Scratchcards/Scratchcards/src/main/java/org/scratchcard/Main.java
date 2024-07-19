package org.scratchcard;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    Winnings winnings = new Winnings();
        System.out.println("Hello world!");
        winnings.readFromFile("puzzleExample.txt");
    }
}