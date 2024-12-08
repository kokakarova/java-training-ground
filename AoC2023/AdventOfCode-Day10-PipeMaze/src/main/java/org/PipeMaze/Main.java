package org.PipeMaze;

public class Main {
    public static void main(String[] args) {
        printMemoryUsage();
        PipeMaze pipeMaze = new PipeMaze();
        pipeMaze.readFromFile("puzzle.txt", 1);

        System.out.println("FURTHEST POINT: " + pipeMaze.getFurthestVertex());
        printMemoryUsage();
    }

    private static void printMemoryUsage() {
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();

        // Run garbage collection (optional, for more accurate results)
        runtime.gc();

        // Calculate memory usage
        long totalMemory = runtime.totalMemory(); // Total memory in the JVM
        long freeMemory = runtime.freeMemory();   // Free memory in the JVM
        long usedMemory = totalMemory - freeMemory; // Used memory in the JVM

        System.out.println("Total Memory (bytes): " + totalMemory);
        System.out.println("Free Memory (bytes): " + freeMemory);
        System.out.println("Used Memory (bytes): " + usedMemory);
    }
}