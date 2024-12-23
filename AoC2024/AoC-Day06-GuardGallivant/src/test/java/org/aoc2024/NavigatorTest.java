package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {

    Navigator nav = new Navigator(0, null, null);

    @Test
    void shouldReturn1_ForInputMovement0() {
        int expectedResult = 1;
        nav.setMovement(nav.updateMovementDirection(0));
        assertEquals(expectedResult, nav.getMovement());
    }

    @Test
    void shouldReturn2_ForInputMovement1() {
        int expectedResult = 2;
        nav.setMovement(nav.updateMovementDirection(1));
        assertEquals(expectedResult, nav.getMovement());
    }

    @Test
    void shouldReturn3_ForInputMovement2() {
        int expectedResult = 3;
        nav.setMovement(nav.updateMovementDirection(2));
        assertEquals(expectedResult, nav.getMovement());
    }

    @Test
    void shouldReturn0_ForInputMovement3() {
        int expectedResult = 0;
        nav.setMovement(nav.updateMovementDirection(3));
        assertEquals(expectedResult, nav.getMovement());
    }

    @Test
    void shouldUpdateNextStepTo_0And3() {
        nav.setCurrentStep(new int[]{0,2});
        nav.setMovement(0);
        nav.setMovement(nav.updateMovementDirection(nav.getMovement()));
        nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), (nav.getMovement())));
        assertEquals(0, nav.getNextStep()[0]);
        assertEquals(3, nav.getNextStep()[1]);
    }

    @Test
    void shouldUpdateNextStepTo_1And2() {
        nav.setCurrentStep(new int[]{0,2});
        nav.setMovement(1);
        nav.setMovement(nav.updateMovementDirection(nav.getMovement()));
        nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), (nav.getMovement())));
        assertEquals(1, nav.getNextStep()[0]);
        assertEquals(2, nav.getNextStep()[1]);
    }

    @Test
    void shouldUpdateNextStepTo_1And1() {
        nav.setCurrentStep(new int[]{1,2});
        nav.setMovement(2);
        nav.setMovement(nav.updateMovementDirection(nav.getMovement()));
        System.out.println("nav.getMovement() = " + nav.getMovement());
        nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), (nav.getMovement())));
        assertEquals(1, nav.getNextStep()[0]);
        assertEquals(1, nav.getNextStep()[1]);
    }

}