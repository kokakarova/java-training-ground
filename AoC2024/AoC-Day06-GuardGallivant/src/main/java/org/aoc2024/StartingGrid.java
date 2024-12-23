package org.aoc2024;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartingGrid {
    private char[][] grid;
    private int[] startingPosition;
}
