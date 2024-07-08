package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @Test
    void twoSumHashMapShouldReturn0And2() {
        int[] arr = {5, 1, 5, 0};
        int target = 10;
        int[] expectedResult = {0, 2};
        int[] actualResult = TwoSum.twoSumHashMap(arr, target);
        assertArrayEquals(expectedResult, actualResult);
    }
    @Test
    void twoSumShouldReturn0And2() {
        int[] arr = {5, 1, 5, 0};
        int target = 10;
        int[] expectedResult = {0, 2};
        int[] actualResult = TwoSum.twoSum(arr, target);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void twoSumHashMapShouldReturn13And14() {
        int[] arr = {5, 7, 5, 1, 3, 4, 6, 8, 10, 44, 3, 9, 11, 0, 2};
        int target = 2;
        int[] expectedResult = {13, 14};
        int[] actualResult = TwoSum.twoSumHashMap(arr, target);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void twoSumShouldReturn1And5() {
        int[] arr = {5, 7, 5, 1, 3, 4, 6, 8, 10, 44, 3, 9, 11, 0, 2};
        int target = 2;
        int[] expectedResult = {13, 14};
        int[] actualResult = TwoSum.twoSum(arr, target);
        assertArrayEquals(expectedResult, actualResult);
    }

}