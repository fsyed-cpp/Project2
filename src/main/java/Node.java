/**
 * The Node class is responsible for creating the chain for our Linked Stack
 * @param <T> Provides the data portion of our node
 */
public class Node<T> {

    // MARK: - Properties

    private T data;
    private Node next;

    // MARK: - Constructor

    /**
     * Creates a new Node object with a reference to null for the next item
     * @param dataPortion The data/value of the Node
     */
    public Node(T dataPortion) {
        this(dataPortion, null);
    }

    /**
     * Creates a new Node with data and a reference to the next node
     * @param dataPortion The value/data the Node holds
     * @param linkPortion The reference to the next node
     */
    public Node(T dataPortion, Node linkPortion) {
        data = dataPortion;
        next = linkPortion;
    }

    // MARK: - Getters / Setters

    /**
     * The data/value of the Node
     * @return The data of the Node
     */
    public T getData() {
        return data;
    }

    /**
     * Provides data for the given node
     * @param newData The data to be set for the Node
     */
    public void setData(T newData) {
        data = newData;
    }

    /**
     * Returns the reference of the next Node
     * @return The Node reference
     */
    public Node getNextNode() {
        return next;
    }

    /**
     * Sets the reference to the next Node in the chain
     * @param nextNode The Node to be added to the chain
     */
    public void setNextNode(Node nextNode) {
        next = nextNode;
    }
}