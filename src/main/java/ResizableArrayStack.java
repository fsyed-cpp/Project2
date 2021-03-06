import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.EmptyStackException;

/** A class of stacks whose entries are stored in an array. */
public class ResizableArrayStack <T> implements StackInterface<T> {

    // MARK: - Properties

    private T[] stack; // Array of stack entries
    private int topIndex; //Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    private static final int INVALID_POSTFIX_EXPRESSION = -404;

    // MARK: - Constructors

    /**
     * Creates an empty Resizable Array Stack with no entries, but with a default capacity
     */
    public ResizableArrayStack() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /**
     * Creates an empty Resizable Array with a set amount of entries
     * @param initialCapacity: The initial size of the array stack
     */
    public ResizableArrayStack(int initialCapacity) {
        integrityOK = false;
        checkCapacity(initialCapacity);

       //The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } // end constructor

    // MARK: - Implementation

    /**
     * Pushes a new item onto the Array Stack
     * @param newEntry An object to be added to the stack
     */
    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } //end push

    /**
     * Pops an element off of the Array Stack
     * @return The data portion of the element that is to be popped off
     */
    @Override
    public T pop() throws EmptyStackException {
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        } // end if
    } // end pop

    /**
     * Reveals the top most value of the stack
     * @return The value/data portion of the top-most item in the stack
     */
    @Override
    public T peek() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            return stack[topIndex];
        }
    } //end peek

    /**
     * Checks to see if the stack is empty
     * @return True if empty, False if not
     * Since the base is index -1, we must check for less than 0 to see if we are empty
     */
    @Override
    public boolean isEmpty()
    {
        return topIndex < 0;
    } // end isEmpty

    /**
     * Removes every reference to each item in the array without deallocating the array
     */
    @Override
    public void clear() {
        checkIntegrity();

        // Remove references to the object in the stack,
        // but do not deallocate the array
        while (topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        } // end loop
        // Assertion: topIndex is -1
    } // end clear

    /**
     * Double the array size if the current size is full
     */
    private void ensureCapacity() {
        if(topIndex >= stack.length -1) {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        } // end if
    } // end ensureCapacity

    /**
     * Ensure we are not adding more items to the stack
     * @param capacity The total amount of items the array can hold
     */
    private void checkCapacity (int capacity) throws IllegalStateException {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException
                    ("Requested capacity exceeds maximum of " + MAX_CAPACITY + ".");
        }
    }

    /**
     * Ensure we do not have an uninitialized Stack
     */
    private void checkIntegrity() throws SecurityException {
        if(!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }

    // MARK: - Postfix / Infix

    /**
     * Evaluates a Postfix expression using a Stack built from a Resizable Array
     * @param expression The postfix expression in String representation
     * @return a Float (Result) of the final mathematical calculation
     */
    public float evaluatePostfix(String expression) {

        // Sanitize user input
        if (expression == " " || expression == "" || expression == null) {
            System.out.println("Error: Empty expression given");
            return INVALID_POSTFIX_EXPRESSION;
        } else {
            // Keep track of expression using a stack
            StackInterface<Integer> stack = new ResizableArrayStack<Integer>();

            // Iterate through the characters in the string
            for (int i = 0; i < expression.length(); i++) {
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
}
