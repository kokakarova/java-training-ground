package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {

    Navigator navigator = new Navigator();
    @Test
    void shouldReturn1_ForInputMovement0() {
        int expectedResult = 1;
        int movement = 0;
        assertEquals(expectedResult, navigator.updateMovementDirection(movement));
    }

}