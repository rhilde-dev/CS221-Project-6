/**
 * A node for building linked data structures.
 * 
 * @author rhilde-dev
 */
public class Node<T> {
    private T element;
    private Node<T> nextNode;
    private Node<T> previousNode;

    /**
     * Initializes a brand new node
     * 
     * @param element The element that is stored inside of the node
     */
    public Node(T element) {
        this.element = element;
        nextNode = null;
    }

    /**
     * Returns the element inside of the node
     * 
     * @return The element inside of the node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element inside of the node
     * 
     * @param element The element that the node will be set to
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Returns the next node from it's current position
     * 
     * @return The next node
     */
    public Node<T> getNextNode() {
        return nextNode;
    }

    /**
     * Sets the next node from it's current position
     * 
     * @param nextNode The node that will be next to the current node
     */
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Returns the previous node from it's current position
     * 
     * @return The previous node
     */
    public Node<T> getPreviousNode() {
        return previousNode;
    }

    /**
     * Sets the previous node from it's current position
     * 
     * @param previousNode The node that will be previous to the current node
     */
    public void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

}
