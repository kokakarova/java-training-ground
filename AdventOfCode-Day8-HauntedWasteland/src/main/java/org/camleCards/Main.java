package org.camleCards;

public class Main {
    public static void main(String[] args) {
        Wasteland w = new Wasteland();
        final long start = System.currentTimeMillis();
        w.readFromFile("puzzle.txt", 2);
//        System.out.println("ZZZ was found in " + w.findZZZ() + " steps");
        System.out.println("ZZZ was found in " + w.findAllZNodes() + " steps");
        final long end = System.currentTimeMillis();
        System.out.println("* * * * * * * * * * * * * * * * ");
        System.out.println("The program was running: " + (end - start) + "ms.");
    }
}