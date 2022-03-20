import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedStackTest {

    @Test
    public void testPush() {
    }

    @Test
    public void testPop() {
    }

    @Test
    public void testPeek() {
    }

    @Test
    public void testIsEmpty() {
    }

    @Test
    public void testClear() {
    }

    @Test
    public void convertToPostfix() {
    }

    /**
     * Tests the evaluatePostfix method to ensure we are properly parsing postfix expressions
     * Test Case 1: Postfix expression equal to 33
     * Test Case 2: Postfix expression equal to 16
     * Test Case 3: Invalid input
     */
    @Test
    void testEvaluatePostfix() {

        // Arrange
        ResizableArrayStack<Integer> arrayStack = new ResizableArrayStack<>();
        String postfixExpression = "2 3 * 4 2 - / 5 6 * +"; // Should be 33
        String postfixExpression2 = "2 5 3 6 + * * 5 / 2 -"; // Should be 16
        String postfixExpressionError = "";

        // Act
        float arrayResult = arrayStack.evaluatePostfix(postfixExpression);
        float arrayResult2 = arrayStack.evaluatePostfix(postfixExpression2);
        float errorResult = arrayStack.evaluatePostfix(postfixExpressionError);
        float expectedArrayResult = 33;
        float expectedArrayResult2 = 16;
        float expectedErrorResult = -404;

        // Assert
        assertEquals(arrayResult, expectedArrayResult);
        assertEquals(arrayResult2, expectedArrayResult2);
        assertEquals(errorResult, expectedErrorResult);
    }
}