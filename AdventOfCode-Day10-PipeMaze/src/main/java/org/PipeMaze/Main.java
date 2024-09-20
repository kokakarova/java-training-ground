package org.PipeMaze;

public class Main {
    public static void main(String[] args) {
        PipeMaze pipeMaze = new PipeMaze();
        pipeMaze.readFromFile("puzzle.txt", 1);

        System.out.println("FURTHEST POINT: " + pipeMaze.getFurthestVertex());
    }
}