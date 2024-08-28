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

    @Test
    void shouldReturn26970666_ForList_Neg5_0_14_35_64_120_276_742_2049_5429_13539_31751_70366_148419_300461_589269_1130567_2141638_4035732_7600527_14324252() {
        List<Integer> testList = List.of(-5, 0, 14, 35, 64, 120, 276, 742, 2049, 5429, 13539, 31751, 70366, 148419, 300461, 589269, 1130567, 2141638, 4035732, 7600527, 14324252);
        int actualResult = mirage.getPrediction(testList);
        int expectedResult = 26970666;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNeg17_ForList_4_3_2_1_0_Neg1_Neg2_Neg3_Neg4_Neg5_Neg6_Neg7_Neg8_Neg9_Neg10_Neg11_Neg12_Neg13_Neg14_Neg15_Neg16() {
        List<Integer> testList = List.of(4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16);
        int actualResult = mirage.getPrediction(testList);
        int expectedResult = -17;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNeg582454_ForList_SeeInTextBody() {
        List<Integer> testList = List.of(14, 34, 67, 110, 153, 178, 157, 45, -236, -826, -1973, -4100, -7897, -14442, -25355, -42989, -70662, -112934, -175933, -267734, -398795);
        int actualResult = mirage.getPrediction(testList);
        int expectedResult = -582454;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn114_ForPuzzleExample() {
        int actualResult = mirage.readFromFile("puzzleExample.txt", 1);
        int expectedResult = 114;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn26970649_ForPuzzleExample_2() {
        int actualResult = mirage.readFromFile("puzzleExample_2.txt", 1);
        int expectedResult = 26970649;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn26388195_ForPuzzleExample_3() {
        int actualResult = mirage.readFromFile("puzzleExample_3.txt", 1);
        int expectedResult = 26388195;
        assertEquals(expectedResult, actualResult);
    }

}