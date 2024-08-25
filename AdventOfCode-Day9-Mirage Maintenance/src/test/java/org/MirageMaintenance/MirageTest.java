package org.MirageMaintenance;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MirageTest {

    Mirage mirage = new Mirage();

    @Test
    void shouldReturn18_ForList_0_3_6_9_12_15() {
        List<Integer> testList = List.of(0, 3, 6, 9, 12, 15);
        int actualResult = mirage.getPrediction(testList);
        int expectedResult = 18;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn28_ForList_1_3_6_10_15_21() {
        List<Integer> testList = List.of(1, 3, 6, 10, 15, 21);
        int actualResult = mirage.getPrediction(testList);
        int expectedResult = 28;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn68_ForList_10_13_16_21_30_45() {
        List<Integer> testList = List.of(10, 13, 16, 21, 30, 45);
        int actualResult = mirage.getPrediction(testList);
        int expectedResult = 68;
        assertEquals(expectedResult, actualResult);
    }
}