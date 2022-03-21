import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResizableArrayStackTest {

    ResizableArrayStack<String> stringResizableArrayStack;

    @BeforeEach
    void setUp() {
        stringResizableArrayStack = new ResizableArrayStack<String>(15);
    }

    @org.junit.jupiter.api.Test
    void test_push() {

        // Act
        stringResizableArrayStack.push(String.valueOf('a'));
        stringResizableArrayStack.push(String.valueOf('b'));
        stringResizableArrayStack.push(String.valueOf('c'));

        //Assert
        assertEquals(false,stringResizableArrayStack.isEmpty());
        assertEquals(String.valueOf('c'), stringResizableArrayStack.peek());
    }

    @org.junit.jupiter.api.Test
    void test_pop() {

        // Arrange
        stringResizableArrayStack.push(String.valueOf('e'));
        stringResizableArrayStack.push(String.valueOf('f'));


        // Act
        //stringResizableArrayStack.pop();
        stringResizableArrayStack.pop();

        //Assert
        assertEquals(true, stringResizableArrayStack.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void test_peek() {

        // Arrange
        stringResizableArrayStack.push(String.valueOf('a'));
        stringResizableArrayStack.push(String.valueOf('b'));
        stringResizableArrayStack.push(String.valueOf('c'));
        stringResizableArrayStack.push(String.valueOf('d'));

        // Act
        Object result = stringResizableArrayStack.peek();

        // Assert
        assertEquals(String.valueOf('d'), result);
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {

        // Arrange
        stringResizableArrayStack.push(String.valueOf('e'));
        stringResizableArrayStack.push(String.valueOf('f'));

        // Act
        stringResizableArrayStack.pop();
        stringResizableArrayStack.pop();

        // Assert
        assertTrue(stringResizableArrayStack.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void clear() {

        // Arrange
        stringResizableArrayStack.push(String.valueOf('a'));
        stringResizableArrayStack.push(String.valueOf('b'));
        stringResizableArrayStack.push(String.valueOf('c'));
        stringResizableArrayStack.push(String.valueOf('d'));

        // Act
        stringResizableArrayStack.clear();

        // Assert
        assertTrue(stringResizableArrayStack.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void evaluatePostfix() {

        //Arrange
        ResizableArrayStack<Integer> valueStackTest = new ResizableArrayStack<Integer>();

        //Act
        // We can see this excercise on the question 7 on the textbook, about evaluating Postfix Expression
        // for example we passed ae+bd−/ where a=2, b=3, c=4,d=5, and e=6
        float result1 = valueStackTest.evaluatePostfix("26+35-/");

        // for example we passed abc*d*−
        float result2 = valueStackTest.evaluatePostfix("234*5*-");

        // another test cases with the different expresion: abc−/d*
        float result3 = valueStackTest.evaluatePostfix("234-/5*");

        //Assert
        assertEquals(-4, result1);
        assertEquals(-58, result2);
        assertEquals(-10, result3);
    }
}