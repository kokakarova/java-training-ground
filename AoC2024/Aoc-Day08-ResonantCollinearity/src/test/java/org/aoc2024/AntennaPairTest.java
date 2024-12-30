package org.aoc2024;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AntennaPairTest {

    // do parameterized test
    @ParameterizedTest
    @CsvSource({
            "15,39,20,30,5,-9",
            "20,30,21,40,1,10"
    })
    void shouldSetRelationCoords(int a1X, int a1Y, int a2X, int a2Y, int expectedX, int expectedY) {
        int[] a1 = new int[]{a1X, a1Y};
        int[] a2 = new int[]{a2X, a2Y};
        int[] expectedResult = new int[]{expectedX, expectedY};
        AntennaPair aPair = new AntennaPair(a1, a2);
        assertEquals(expectedResult[0], aPair.getRelationCoords()[0]);
        assertEquals(expectedResult[1], aPair.getRelationCoords()[1]);
    }
}