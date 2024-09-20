package org.PipeMaze;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PipeMazeTest {


    PipeMaze pipeMaze = new PipeMaze();

    @Test
    void shouldSave_7Pipe_5Dash_4Ls_2Fs_4Sevens_2Js() {
        String fileName = "puzzleExample.txt";
        int expectedPipes = 7;
        int expectedDashes = 5;
        int expectedLs = 4;
        int expectedFs = 2;
        int expectedSevens = 4;
        int expectedJs = 2;
        pipeMaze.readFromFile(fileName, 1);
        int actualPipes = pipeMaze.pipeVertexes.values().stream().mapToInt(List::size).sum();
        int actualDashes = pipeMaze.dashVertexes.values().stream().mapToInt(List::size).sum();
        int actualLs = pipeMaze.lSignVertexes.values().stream().mapToInt(List::size).sum();
        int actualFs = pipeMaze.fSignVertexes.values().stream().mapToInt(List::size).sum();
        int actualSevens = pipeMaze.sevenSignVertexes.values().stream().mapToInt(List::size).sum();
        int actualJs = pipeMaze.jSignVertexes.values().stream().mapToInt(List::size).sum();
        assertEquals(expectedPipes, actualPipes);
        assertEquals(expectedDashes, actualDashes);
        assertEquals(expectedLs, actualLs);
        assertEquals(expectedFs, actualFs);
        assertEquals(expectedSevens, actualSevens);
        assertEquals(expectedJs, actualJs);
    }

    @Test
    void shouldReturn_4_For_PuzzleExample() {
        String fileName = "puzzleExample.txt";
        long expectedResult = 4;
        pipeMaze.readFromFile(fileName, 1);
        long actualResult = pipeMaze.getFurthestVertex();
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void shouldReturn_8_For_PuzzleExample2() {
        String fileName = "puzzleExample2.txt";
        long expectedResult = 8;
        pipeMaze.readFromFile(fileName, 1);
        long actualResult = pipeMaze.getFurthestVertex();
        assertEquals(expectedResult, actualResult);
    }
}