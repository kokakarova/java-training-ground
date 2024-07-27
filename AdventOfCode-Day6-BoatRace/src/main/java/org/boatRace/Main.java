package org.boatRace;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BoatRace boatRace = new BoatRace(List.of(54, 94, 65, 92), List.of(302, 1476, 1029, 1404));
        final long start = System.currentTimeMillis();
        System.out.println("Final result: " + boatRace.getFinalResult());
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end-start) + "ms.");
    }
}