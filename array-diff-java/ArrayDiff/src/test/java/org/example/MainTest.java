package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void firstTest() {
        // Arrange
        int[] arr1 = {1, 2};
        int[] arr2 = {1};
        // Act
        int[] arrResult = arrayDiff(arr1, arr2);
        // Assert
        assertEquals(new int[]{2}, arrResult)
    }


}