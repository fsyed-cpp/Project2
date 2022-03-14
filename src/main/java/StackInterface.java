/** An interface that describes the operation of stack.*/
public interface StackInterface<T>
{
    /**
     * Adds a new entry to the top of this stack.
     * @param newEntry An object to be added to the stack **/
    public void push(T newEntry);

/** Removes and returns this stack's top entry.
 * @return The object at the top of the stack.
 * @throws java.util.EmptyStackException if the stack is empty before the operation
 */
    public T pop();

    /** Retrieves this stack's top entry.
     @return  The object at the top of the stack.
     @throws  java.util.EmptyStackException if the stack is empty. */
    public T peek();

    /** Detects whether this stack is empty.
     @return  True if the stack is empty. */
    public boolean isEmpty();

    /** Removes all entries from this stack. */
    public void clear();

    /**
     * convertToPostfix
     * Convert an infix expression to an equivalent postfix expression
     */
    public StackInterface<T> convertToPostfix(StackInterface<T> infix);

    /**
     * evaluatePostfix
     * Evaluates a postfix expression
     */
    public StackInterface<T> evaluatePostfix(StackInterface<T> postfix);

}
