package org.PipeMaze;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PipeMazeTest {

    String fileName = "puzzleExample.txt";
    PipeMaze pipeMaze = new PipeMaze();

    @Test
    void shouldSave_7Pipe_5Dash_4Ls_2Fs_4Sevens_2Js() {
        int expectedPipes = 7;
        int expectedDashes = 5;
        int expectedLs = 4;
        int expectedFs = 2;
        int expectedSevens = 4;
        int expectedJs = 2;
        pipeMaze.readFromFile(fileName, 1);
        int actualPipes = pipeMaze.pipeSigns.values().stream().mapToInt(List::size).sum();
        int actualDashes = pipeMaze.dashSigns.values().stream().mapToInt(List::size).sum();
        int actualLs = pipeMaze.lSigns.values().stream().mapToInt(List::size).sum();
        int actualFs = pipeMaze.fSigns.values().stream().mapToInt(List::size).sum();
        int actualSevens = pipeMaze.sevenSigns.values().stream().mapToInt(List::size).sum();
        int actualJs = pipeMaze.jSigns.values().stream().mapToInt(List::size).sum();
        assertEquals(expectedPipes, actualPipes);
        assertEquals(expectedDashes, actualDashes);
        assertEquals(expectedLs, actualLs);
        assertEquals(expectedFs, actualFs);
        assertEquals(expectedSevens, actualSevens);
        assertEquals(expectedJs, actualJs);
    }
}