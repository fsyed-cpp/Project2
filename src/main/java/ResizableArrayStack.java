import java.lang.SuppressWarnings;
import java.util.Arrays;

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
        //checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
      //  @SuppressWarnings("unchecked")
       // T[] tempStack = (T[])new Object(InitialCapacity);
       // stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } // end constructor

    @Override
    public void push(T newEntry)
    {

    }

    @Override
    public T pop()
    {
        return null;
    }

    @Override
    public T peek()
    {
        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public void clear()
    {

    }
}
