package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingPartsTest {

    @Test
    void should() {
        String testString = "..38.143...1.......";
        int[][] expectedResult = new int[][]{{2, 3}, {5, 6, 7}, {11}};
        List<Integer> actualResult = MissingParts.getNumberIndexes(testString);
        assertEquals(2, actualResult.get(0));
    }
}