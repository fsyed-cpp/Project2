import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.lang.reflect.Array;

/** A class of stacks whose entries are stored in an array. */
public abstract class ResizableArrayStack <T> implements StackInterface<T> {

    private T[] stack; // Array of stack entries
    private int topIndex; //Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayStack() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

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

    @Override
    public void push(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } //end push

    @Override
    public T pop()
    {
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
        {
            T top = stack[topIndex] = null;
            topIndex--;
            return top;
        } // end if
    } // end pop

    @Override
    public T peek()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } //end peek

    @Override
    public boolean isEmpty()
    {
        return topIndex < 0;
    } // end isEmpty

    @Override
    public void clear()
    {
        checkIntegrity();

        // Remove references to the object in the stack,
        // but do not deallocate the array
        while (topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        } // end loop
        // Assertion: topIndex is -1
    } // end clear

    /**
     * Need to be implemented
     */
    public void convertToPostfix(){}

    // other useful methods:
    private void ensureCapacity(){
        if(topIndex >= stack.length -1) // if array is full, double its size
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        } // end if
    } // end ensureCapacity

    private void checkCapacity (int capacity){
        if (capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException
                    ("Requested capacity exceeds maximum of " + MAX_CAPACITY + ".");
        }
    }

    private void checkIntegrity(){
        if(!integrityOK)
        {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }
}
