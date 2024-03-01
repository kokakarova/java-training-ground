import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FewestCubesTest {
    @Test
    void shouldReturn37() {
        String fileName = "testFile.txt";
        int expectedResult = FewestCubes.readPuzzle(fileName);
        assertEquals(37,expectedResult);
    }
}