import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeConundrumTest {

    @Test
    void shouldReturn1forGame_1() {
        // Arrange
        String[] testArr = {"Game 1"};
        // Act
        int actualResult = CubeConundrum.getGameNumber(testArr);
        // Assert
        assertEquals(1, actualResult);
    }
}