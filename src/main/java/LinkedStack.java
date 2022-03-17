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


    /**
     * Converts an Infix expression to a Postfix expression
     * Handles the following operators: +, -, *, /
     * Expects a String expression with a single whitespace between operators and values
     * Ex: "2 3 * 4 2 - / 5 6 * +"
     * @param expression The infix expression as a String
     * @return a 'String' expression in postfix notation
     * @throws Exception Throws an exception if we have a leftover opening parenthesis
     */
    public String convertToPostfix(String expression) throws Exception {

        // To be returned at the end
        String postfixString = new String("");

        // Solve the expression using our Linked Chain Stack
        LinkedStack<Character> linkedStack = new LinkedStack<Character>();

        for (int i = 0; i< expression.length(); i++) {

            char currChar = expression.charAt(i);

            // Ignore whitespace
            if (currChar == ' ') {
                continue;
            }

            // Numbers automatically get added to the stack
            if (Character.isDigit(currChar)) {
                postfixString += currChar;
                postfixString += " ";
            }

            // Add open parenthesis
            else if (currChar == '(') {
                linkedStack.push(currChar);
            }

            // Every time we see a closing parenthesis,
            // we need to solve the inner problem inside until we see '('
            else if (currChar == ')') {
                while (!linkedStack.isEmpty() && linkedStack.peek() != '(') {
                    postfixString += linkedStack.pop();
                    postfixString += " ";
                }
                // Pop remaining parenthesis
                linkedStack.pop();
            }
            // We have an operator. Based on its precedent,
            // we will decide if we pop from the stack using
            // the operand, or add it to the current stack.
            // We check using the current operator in the expression
            // and compare it to the operator at the top of the stack
            else {
                while (!linkedStack.isEmpty() && getPrecedence(currChar) <= getPrecedence(linkedStack.peek())){
                    postfixString += linkedStack.pop();
                    postfixString += " ";
                }
                linkedStack.push(currChar);
            }
        }

        // At this point, we only have leftover operators with the
        // highest precedence. Now we pop them all from the stack
        while (!linkedStack.isEmpty()) {
            if (linkedStack.peek() == '(') {
                throw new Exception("Unexpected open parenthesis when all mini-problems are solved");
            }
            postfixString += linkedStack.pop();
            postfixString += " ";
        }

        // Remove the trailing space at the end
        postfixString = postfixString.substring(0, postfixString.length() - 1);
        return postfixString;
    }

    // MARK: - Helpers

    /**
     * Calculates precedence using the rules of PEMDAS
     * @param operator Can be +, -, /, *
     * @return The priority defined as an integer
     */
    private int getPrecedence(char operator) {
        if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else {
            // Unknown operator
            return 0;
        }
    }
}

