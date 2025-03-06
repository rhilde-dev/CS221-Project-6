
/**
 * A node for building linked data structures.
 * @author rhilde-dev
 */
public class Node<T> {
    private T element;
    private Node<T> nextNode;

    public Node(T element){
        this.element = element;
        nextNode = null;
    }

    // public Node(T element, Node<T> nextNode){
    //     this.element = element;
    //     this.nextNode = nextNode;
    // }

    public T getElement() {
        return element;
    }
    
    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    

    
}
