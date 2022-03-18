import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode;

    public LinkedStack(int i) {
        topNode = null;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node linkPortion) {
            data = dataPortion;
            next = linkPortion;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }

    public void push (T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    public T pop() {
        T top = peek();
        topNode = topNode.getNextNode();
        return top;
    }
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    }

    public boolean isEmpty() {
        return topNode == null;
    }
    public void clear() {
        topNode = null;
    }

    @Override
    /**
     * Need to be implemented...
     */
    public StackInterface<T> convertToPostfix(StackInterface<T> infix)
    {
        return null;
    }

    @Override
    public StackInterface<T> evaluatePostfix(StackInterface<T> postfix)
    {
        return null;
    }

}

