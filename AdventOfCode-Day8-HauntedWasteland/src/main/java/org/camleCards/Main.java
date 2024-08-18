package org.camleCards;

public class Main {
    public static void main(String[] args) {
        Wasteland w = new Wasteland();
        w.readFromFile("puzzle.txt");
        System.out.println("ZZZ was found in " + w.findZZZ() + " steps");
    }
}