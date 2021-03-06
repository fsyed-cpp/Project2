import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for the Linked Stack implementation
 * @param <T> Generic type T
 */
public class LinkedStackTest<T> {

    /**
     * Tests the push() method
     */
    @Test
    public void testPush() {

        // Arrange
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        // Act
        linkedStack.push((int) 'x');

        // Assert
        assertFalse(linkedStack.isEmpty());
    }

    /**
     * Tests the pop() method
     */
    @Test
    public void testPop() {

        // Arrange
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push((int) 'x');
        linkedStack.push((int) 'y');

        // Act
        linkedStack.pop();
        linkedStack.pop();

        // Assert
        assertTrue(linkedStack.isEmpty());
    }

    /**
     * Tests peek() method
     */
    @Test
    public void testPeek() {

        // Arrange
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push((int) 'x');
        linkedStack.push((int) 'y');
        linkedStack.push((int) 'z');
        linkedStack.push((int) '*');

        // Act
        linkedStack.pop();
        linkedStack.peek();

        // Assert
        assertEquals((int) 'z', linkedStack.peek());
    }

    /**
     * Tests isEmpty()
     */
    @Test
    public void testIsEmpty() {

        // Arrange
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push((int) 'x');
        linkedStack.push((int) 'y');
        linkedStack.push((int) 'z');
        linkedStack.push((int) '*');

        // Act
        linkedStack.clear();

        // Assert
        assertTrue(linkedStack.isEmpty());
    }

    /**
     * Tests the clear() method
     */
    @Test
    public void testClear() {

        // Arrange
        LinkedStack<Object> charLinkedStack = new LinkedStack<Object>();
        charLinkedStack.push((int) 'x');
        charLinkedStack.push((int) 'y');
        charLinkedStack.push((int) 'z');
        charLinkedStack.push((int) '*');

        // Act
        charLinkedStack.clear();

        // Assert
        assertTrue(charLinkedStack.isEmpty());
    }

    /**
     * Tests convertToPostfix()
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