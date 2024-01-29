import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeConundrumTest {

    @Test
    void firstTest() {
        // Arrange
        String fileName = "puzzleInput.txt";
        // Act
        int actualResult = CubeConundrum.readPuzzle(fileName);
        // Assert
        assertEquals(4, actualResult);
    }
}