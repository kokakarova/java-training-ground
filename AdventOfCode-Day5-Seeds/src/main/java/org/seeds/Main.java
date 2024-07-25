package org.seeds;

public class Main {

    public static void main(String[] args) {
        Garden garden = new Garden();
        final long start = System.currentTimeMillis();
        System.out.println(garden.readFromFile("puzzle.txt"));
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end-start) + "ms.");
    }
}