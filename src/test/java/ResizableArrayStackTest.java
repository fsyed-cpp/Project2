import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResizableArrayStackTest {

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

    /**
     * Tests the convertToPostfix() method
     * Test Case 1: Valid Infix Expression
     * Test Case 2: Invalid Infix Expression
     */
    @Test
    public void testConvertToPostfix() {

        // Arrange
        LinkedStack<Integer> linkedStack = new LinkedStack<Integer>();
        String infixExpression = "2 * 3 / (4 - 2) + 5 * 6";
        String emptyInfixExpression = "";

        // Act
        try {
            String postfixResult = linkedStack.convertToPostfix(infixExpression);
            String expectedPostfixResult = "2 3 * 4 2 - / 5 6 * +";
            String postfixEmptyResult = linkedStack.convertToPostfix(emptyInfixExpression);
            assertEquals(postfixResult, expectedPostfixResult);
            assertEquals(postfixEmptyResult, "INVALID EXPRESSION");
        } catch (Exception exception) {
            System.out.println("Error when attempting to convert to postfix: " + exception);
            fail();
        }
    }
}