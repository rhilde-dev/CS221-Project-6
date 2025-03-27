import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double-linked-node-based implementation of IndexedUnsortedList
 * @author rhilde-dev
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    /** Initialize brand new empty list. */
    public IUDoubleLinkedList() {
        head = tail = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public void addToFront(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToFront'");
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);
        if (size == 0){ //isEmpty or tail == null
            head = tail = newNode;
        } else {
            tail.setNextNode(newNode);
            newNode.setPreviousNode(tail);
            tail = newNode;
        }
        size++;
        modCount++;

    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        Node<T> targetNode = head;
        while(targetNode != null && !targetNode.getElement().equals(target)) {
            targetNode = targetNode.getNextNode();
        }
        if(targetNode == null){
            throw new NoSuchElementException();
        }
        Node<T> newNode = new Node<T>(element);
        newNode.setNextNode(targetNode.getNextNode());
        newNode.setPreviousNode(targetNode);
        targetNode.setNextNode(newNode);
        if(targetNode == tail){
            tail = newNode;
        } else {
            targetNode.getNextNode().setPreviousNode(newNode);
        }


        // if (size == 0){
        //     throw new NoSuchElementException();
        // }
        // //set next node to the current node
        // Node<T> newNode = new Node<T>(element);
        // Node<T> currentNode = head;
        // while(!currentNode.getElement().equals(target)){
        //     if(currentNode.getNextNode() == null){
        //         throw new NoSuchElementException();
        //     }
        //     currentNode = currentNode.getNextNode();
        // }
        // if (currentNode == tail){
        //     tail.setNextNode(newNode);
        //     newNode.setPreviousNode(tail);
        //     tail = newNode;
        // } else {
        //     newNode.setNextNode(currentNode.getNextNode());
        //     newNode.setPreviousNode(currentNode);
        //     currentNode.getNextNode().setPreviousNode(newNode);
        //     currentNode.setNextNode(newNode);
        // }

        size++;
        modCount++;

    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T retVal = tail.getElement();
        tail = tail.getPreviousNode();
        if (size == 1){ //tail == null
            head = null;
        } else {
            tail.setNextNode(null);
        }
        size--;
        modCount++;
        return retVal;

    }

    @Override
    public T remove(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int indexOf(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public T first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    @Override
    public T last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
		str.append("[");
		for(T element : this){
			str.append(element.toString());
			str.append(",");
		}
		if (size() > 0){
			str.delete(str.length() - 2, str.length());
		}
		str.append("]");
		return str.toString();
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }
    
}
