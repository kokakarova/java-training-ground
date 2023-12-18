import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    @DisplayName("True for ab#c equals ad#c")
    void shouldReturnTrueForAbHashcAndAdHasC() {
        Solution solution = new Solution();
        assertTrue(solution.backspaceCompare("ab#c", "ad#c"));
    }
    @Test
    @DisplayName("True for ab## equals c#d#")
    void shouldReturnTrueAbHashHashAndCHashDHash() {
        Solution solution = new Solution();
        assertTrue(solution.backspaceCompare("ab##", "c#d#"));
    }

    @Test
    @DisplayName("False for a#c equals b")
    void shouldReturnFalseForAHashCAndB() {
        Solution solution = new Solution();
        assertFalse(solution.backspaceCompare("a#c", "b"));
    }

}