import java.util.EmptyStackException;
import java.util.*;

/**
 * Creates a Stack using a Linked List (Linked Chain) of Node objects
 * @param <T> By Providing type 'T' we create a generic implementation of our Linked Stack
 */
public class LinkedStack<T> implements StackInterface<T> {

    // MARK: - Properties

    private Node topNode;

    // MARK: - Constructor

    public LinkedStack() {
        topNode = null;
    }

    // MARK: - Implementation

    /**
     * Pushes a new Node onto the Stack
     * @param newEntry An object to be added to the stack
     */
    public void push (T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    /**
     * Pops off the top most node on the stack
     * @return The value/data portion of the Node that is to be popped
     */
    public T pop() {
        try {
            T top = peek();
            topNode = topNode.getNextNode();
            return top;
        } catch (EmptyStackException exception) {
            System.out.println("Error when popping - found an empty stack");
            return null;
        }
    }

    /**
     * Peeks into the top most Node in the stack
     * @return The value of the top most Node in the stack without popping it off
     * @throws EmptyStackException If the stack is empty, throws an exception
     */
    public T peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return (T) topNode.getData();
    }

    /**
     * Checks to see if we have a single node in our stack
     * @return True if we have at least one Node, false if we don't
     */
    public boolean isEmpty() {
        return topNode == null;
    }

    /**
     * Removes the top node from the stack, the rest will be cleared by the GC
     */
    public void clear() {
        topNode = null;
    }

    // MARK: TODO

    @Override
    public StackInterface<T> convertToPostfix(StackInterface<T> infix)
    {
        Stack<Character> operatorStack = new Stack<Character>();
        String postfix = "";
        int i = 0;
        while (infix.isEmpty())
        {
            char nextCharacter = postfix.charAt(i);
            i++;
            switch (nextCharacter)
            {
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!operatorStack.isEmpty() && nextCharacter < operatorStack.peek())
                    {
                        operatorStack.pop();
                    }
            }
            return null;
        }
        return null;
    }


    @Override
    public StackInterface<T> evaluatePostfix(StackInterface<T> postfix) {

        // We need 2 stacks:
        // First one is for the values
        // Second one is for the operators
        StackInterface<T> values = new LinkedStack<T>();
        StackInterface<T> operators = new LinkedStack<>();

        while (!postfix.isEmpty()) {

            // First determine what type of char is at the top of the stack
            // We should cast our type to char so that we can read it
            T top = postfix.peek();
            char charTop = (char)top;

            // Numbers go onto the values stack
            if (charTop >= 0 && charTop <= 9) {
                values.push(top);
            }

            // Catch opening parenthesis
            else if (charTop == '(') {
                operators.push(top);
            }

            // Solve mini-expression with closing parenthesis
            else if (charTop == ')') {
                while ((char)operators.peek() != '(') {
                    T firstVal = values.pop();
                    T secondVal = values.pop();
                    T operator = operators.pop();
                    try {
                        Integer result = evaluateNumericExpression((char) operator, (int) firstVal, (int) secondVal);
                        values.push((T)result);
                        operators.pop();
                    } catch (Exception exception) {
                        System.out.println("Error when evaluating expression - found division by 0");
                    }
                }
            }

            // TODO: Evaluate operators, check for precedence, and apply evaluation when entire expression has been evaluated

        }

        return null;
    }

    // MARK: - Helpers

    /**
     * Help us determine which operators take precedent over others
     * @param firstOperator The first operator in the stack
     * @param secondOperator The second operator from the stack
     * @return True if the second operator is >= precedence of first operator, otherwise false
     */
    private boolean hasPrecedence(char firstOperator, char secondOperator) {

        // Parentheses always take precedent
        if (secondOperator == ')' || secondOperator == '(') {
            return false;
        }

        // Multiplication and Division take precedence over Addition and Subtraction
        if ((firstOperator == '*' || firstOperator == '/') &&
                (secondOperator == '+' || secondOperator == '-')) {
            return false;
        }

        return true;
    }

    /**
     * Evaluates a numerical expression with two values and one operators
     * @param operator The operator popped off the stack
     * @param first The first value off of the values stack
     * @param second The second value off of the values stack
     * @return
     */
    private Integer evaluateNumericExpression(char operator, int second, int first) throws Exception {
        if (operator == '+') {
            return first + second;
        }
        else if (operator == '-') {
            return first - second;
        }
        else if (operator == '*') {
            return first * second;
        }
        // For division, we should watch for divides by 0
        if (operator == '/') {
            if (second == 0) {
                throw new Exception("Divide by 0 error");
            } else {
                return first / second;
            }
        }
        return 0;
    }
}

