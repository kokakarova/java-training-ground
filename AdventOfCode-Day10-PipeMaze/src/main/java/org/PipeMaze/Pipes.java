package org.PipeMaze;

import lombok.Data;

public enum Pipes {
    NORTH_SOUTH('|'),
    EAST_WEST('-'),
    NORTH_EAST('L'),
    NORTH_WEST('J'),
    SOUTH_WEST('7'),
    SOUTH_EAST('F'),
    STARTING_POSITION('S');

    private char pipe;
    Pipes(char pipe) {
        this.pipe = pipe;
    }
}
