import java.util.ConcurrentModificationException;
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
        
        size++;
        modCount++;


    }

    @Override
    public void add(int index, T element) {
        ListIterator<T> lit = listIterator(index);
        lit.add(element);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        ListIterator<T> lit = listIterator();
        T retVal = lit.next();
        lit.remove();
        return retVal;
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
        if (index < 0 || index >= size){ // valid remove index range != list iterator's range
            throw new IndexOutOfBoundsException();
        }
        ListIterator<T> lit = listIterator(index);
        T retVal = lit.next();
        lit.remove();
        return retVal;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        currentNode.setElement(element);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getElement();
    }

    @Override
    public int indexOf(T element) {
        Node<T> currentNode = head;
        int currentIndex = 0;
        int returnIndex = -1;
        while (currentNode != null && returnIndex < 0) {
            if (currentNode.getElement().equals(element)) {
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
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
        return new IUDLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new IUDLLIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new IUDLLIterator(startingIndex);
    }
    
    //don't duplicate code for iterator and list iterator

    /** ListIterator (and basic Iterator) for IUDoubleLinkedList */
    private class IUDLLIterator implements ListIterator<T> {
        
        private Node<T> nextNode;
        private int nextIndex;
        private int iterModCount;
        private Node<T> lastReturnedNode;

        /** Initialize ListIterator before first element */
        public IUDLLIterator(){
            nextNode = head;
            nextIndex = 0;
            iterModCount = modCount;
            lastReturnedNode = null;

        }

        /** Intitialize ListIterator before startingIndex
         * @param startingIndex index of elementthat would be next
         */
        public IUDLLIterator(int startingIndex){
            if (startingIndex < 0 || startingIndex > size){
                throw new IndexOutOfBoundsException();
            }
            nextNode = head;
            for (int i = 0; i < startingIndex; i++){ //it's fine in the first half, but you should figure out how to start from the back if it's in the second half
                nextNode = nextNode.getNextNode();
            }
            nextIndex = startingIndex;
            iterModCount = modCount;
            lastReturnedNode = null;

        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount){
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            lastReturnedNode = nextNode;
            nextNode = nextNode.getNextNode();
            nextIndex++;
            return retVal;
        }

        @Override
        public boolean hasPrevious() {
            if (iterModCount != modCount){
                throw new ConcurrentModificationException();
            }
            // return nextNode.getPreviousNode() != null; //nextIndex > 0
            return nextNode != head;
        }

        @Override
        public T previous() {
            if (!hasPrevious()){
                throw new NoSuchElementException();
            }
            if(nextNode != null){
                nextNode = nextNode.getPreviousNode();
            } else {
                nextNode = tail;
            }
            lastReturnedNode = nextNode;
            nextIndex--;
            return nextNode.getElement();

        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            //enum, pair of booleans, check for last removed values
            if (iterModCount != modCount){ //is the iterator still valid
                throw new ConcurrentModificationException();
            }
            if (lastReturnedNode == null){ //am I allowed to remove
                throw new IllegalStateException();
            }
            if (lastReturnedNode == head) {
                head = head.getNextNode();
            } else {
                lastReturnedNode.getPreviousNode().setNextNode(lastReturnedNode.getNextNode());
            }
            if (lastReturnedNode == tail) {
                tail = tail.getPreviousNode();
            } else {
                lastReturnedNode.getNextNode().setPreviousNode(lastReturnedNode.getPreviousNode());
            }
            if (nextNode == lastReturnedNode){ //last move was previous
                nextNode = nextNode.getNextNode();
            } else { //last move was next
                nextIndex--;
            }

            lastReturnedNode = null;
            size--;
            modCount++;
            iterModCount++;

        }

        @Override
        public void set(T e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'set'");
        }

        @Override
        public void add(T e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }
        
    }
}
