package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleProccessorTest {

    @Test
    void shouldReturn_111String_ForInput_int3_Char1() {
        PuzzleProccessor proccessor = new PuzzleProccessor();
        String expected = "111";
        assertEquals(expected, proccessor.addFileToString(3, '1'));
    }

    @Test
    void shouldReturn_ThreeDotstring_ForInput_int3() {
        PuzzleProccessor proccessor = new PuzzleProccessor();
        String expected = "...";
        assertEquals(expected, proccessor.addSpaceToString(3));
    }

}