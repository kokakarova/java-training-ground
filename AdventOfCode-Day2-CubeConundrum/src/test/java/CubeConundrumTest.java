import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeConundrumTest {

    @Test
    void shouldReturn1forGame_1() {
        // Arrange
        String testArr = "Game 1";
        // Act
        int actualResult = PossibleGame.getGameNumber(testArr);
        // Assert
        assertEquals(1, actualResult);
    }
    @Test
    void shouldReturn5() {
        // Arrange
        String fileName = "testFile.txt";
        // Act
        int actualResult = PossibleGame.readPuzzle(fileName);
        // Assert
        assertEquals(5, actualResult);
    }
}