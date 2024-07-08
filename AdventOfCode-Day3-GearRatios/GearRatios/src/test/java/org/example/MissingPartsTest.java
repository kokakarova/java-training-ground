package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingPartsTest {

    MissingParts missingParts = new MissingParts();

    @Test
    void shouldRetrun7() {
        int value = 7;
        int decaPlace = 1;
        assertEquals(7, MissingParts.decaMultiplier(value, decaPlace));
    }

    @Test
    void shouldRetrun70() {
        int value = 7;
        int decaPlace = 2;
        assertEquals(70, MissingParts.decaMultiplier(value, decaPlace));
    }

//    @Test
//    void shouldRetrun0() {
//        String testString = "..38.143...1.......";
//        assertEquals(3, missingParts.);
//    }

}