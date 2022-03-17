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

    /**
     * Evaluates a Postfix expression using a Stack built from a Linked Chain
     * @param expression The postfix expression in String representation
     * @return a Float (Result) of the final mathematical calculation
     */
    @Override
    public float evaluatePostfix(String expression) {

        // Keep track of expression using a stack
        StackInterface<Integer> stack = new LinkedStack<Integer>();

        // Iterate through the characters in the string
        for (int i = 0; i < expression.length(); i++)
        {
            char currentChar = expression.charAt(i);

            // ignore whitespace
            if (currentChar == ' ') {
                continue;
            }

            // Push numbers to the stack
            if(Character.isDigit(currentChar)) {
                stack.push(currentChar - '0');
            }
            // Whenever we see an operator, pop the first two elements from the stack and
            // evaluate the expression
            else {
                int first = stack.pop();
                int second = stack.pop();

                // Addition
                if (currentChar == '+') {
                    int sum = second + first;
                    stack.push(sum);
                }
                // Subtraction
                else if (currentChar == '-') {
                    int difference = second - first;
                    stack.push(difference);
                }
                // Division
                else if (currentChar == '/') {
                    int quotient = second / first;
                    stack.push(quotient);
                }
                else if (currentChar == '*') {
                    int product = second * first;
                    stack.push(product);
                } else {
                    System.out.println("Error - found unexpected operator");
                }
            }
        }

        // The last/topmost item in the stack is the result
        Integer finalResult = stack.pop();
        return finalResult;
    }
}

