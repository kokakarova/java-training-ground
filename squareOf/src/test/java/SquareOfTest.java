import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareOfTest {

    @Test
    void shouldReturnListOfIntegers0012() {
        // Arrange
        int n = 2;
        Integer[] expectedResult = {0, 0, 0, 1};
        // Act & Assert
        assertEquals(4, SquareOf.squareOf(n).length);
    }

}