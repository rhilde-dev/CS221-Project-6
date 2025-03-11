import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /** Initialize brand new empty list. */
    public IUSingleLinkedList(){
        head = tail = null;
        size = 0;
    }

    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<T>(element);
        if (size == 0){
            tail = newNode;
        }
        newNode.setNextNode(head);
        head = newNode;
        size++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<T>(element);
        if (size > 0){
            tail.setNextNode(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
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
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++){
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getElement();
    }

    @Override
    public int indexOf(T element) {
        Node<T> currentNode = head;
        int currentIndex = 0;
        int returnIndex = -1;
        while(currentNode != null && returnIndex < 0){
            if (currentNode.getElement().equals(element)){
                returnIndex = currentIndex;
            } else {
                currentIndex++;
                currentNode = currentNode.getNextNode();
            }
        } 
        return returnIndex;
    }

    @Override
    public T first() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        return indexOf(target) > -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
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
