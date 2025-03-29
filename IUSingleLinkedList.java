import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount = 0;   

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
        modCount++;
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
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node<T> newNode = new Node<T>(element);
        Node<T> currentNode = head;
        Node<T> nextNode = currentNode.getNextNode();
        
        
        if (size == 0){

            addToRear(element);

        } else if (size == 1){

            addToRear(element);

        } else if(currentNode.getElement().equals(element)){
            currentNode.setNextNode(newNode);
            newNode.setNextNode(nextNode);
        } else { //general case

            while (currentNode != null && !currentNode.getElement().equals(element)){
                currentNode = currentNode.getNextNode();
            }
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            if (currentNode == tail){
                currentNode.setNextNode(newNode);
                tail = newNode;
            } else {
                currentNode.setNextNode(newNode);
                newNode.setNextNode(nextNode);
            }
        }
        
        size++;
        modCount++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<T>(element);
        
        if(size == 0){
            addToRear(element);
        } 
        
        Node<T> currentNode = head;
        Node<T> nextNode = currentNode.getNextNode();

        if(size == 1){
            addToRear(element);
        } else { //general case
            //find index node
            for (int i = 0; i < index; i++){
                currentNode = currentNode.getNextNode();
            }
            // if (currentNode == null){
            //     throw 
            // }
            newNode.setNextNode(nextNode);
            currentNode.setNextNode(newNode);
        }
        
        size++;
        modCount++;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T retVal = head.getElement();
        head = head.getNextNode();
        if (size == 1){ //or head == null
            tail = null;
        }
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T retVal = tail.getElement();
        if (size == 1){
            head = tail = null;
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != tail) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(null);
            tail = currentNode;
        }
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T remove(T element) {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        Node<T> currentNode = head;
        Node<T> nextNode = currentNode.getNextNode();
        if (currentNode.getElement().equals(element))
        {
            return removeFirst();
        }

        while (currentNode != null && !currentNode.getElement().equals(element)) {
            currentNode = currentNode.getNextNode();
            // nextNode = nextNode.getNextNode();
        }

        if (currentNode == null) {
            throw new NoSuchElementException();
        }
        // if (next == tail) {
        //     tail = currentNode;
        // }

        currentNode.setNextNode(nextNode.getNextNode());

        size--;
        modCount++;
        return nextNode.getElement();

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        T retVal = null;
        Node<T> currentNode = head;
        if(size == 1){
            retVal = currentNode.getElement();
            head = tail = null;
        } else {
            //general case
            for (int i = 0; i < index; i++){
                currentNode = currentNode.getNextNode();
            }
            retVal = currentNode.getElement();
            //shift operation
    
            Node<T> nextNode = currentNode.getNextNode();
    
            // for (int i = index; i < size; i++){
            //     if (nextNode == tail){
            //         currentNode.setElement(nextNode.getElement());
            //         tail = currentNode;
            //         currentNode.setNextNode(null);
            //     }
            //     currentNode.setElement(nextNode.getElement());
            // }
            //TODO: fix this ^^^ make connections, no shift operations should occur. Reference remove(element)

        }

        size--;
        modCount++;

        return retVal;
        
        // throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++){
            currentNode = currentNode.getNextNode();
        }
        currentNode.setElement(element);
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
        return new SLLIterator();
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

    /**
     * Basic Iterator for IUSingleLinkedList
     */
    private class SLLIterator implements Iterator<T> {
        private Node<T> nextNode;
        private int iterModCount;
        private boolean canRemove = false; //maybe remove false

        /**
         * initalize Iterator in front of first element
         */
        public SLLIterator(){
            nextNode = head;
            iterModCount = modCount;
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
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            nextNode = nextNode.getNextNode();
            canRemove = true;
            return retVal;
        }

        @Override
        public void remove(){
            if (modCount != iterModCount){
                throw new ConcurrentModificationException();
            }
            if (!canRemove){
                throw new IllegalStateException();
            }
            canRemove = false;

            if(head.getNextNode() == nextNode){ //removing head
                //if head == tail
                    //head = tail = null
                
                head = nextNode; // or head = head.getNextNode();
                if (head == null){ //or size == 1
                    tail = null;
                }
            } else {
                //general case
                Node<T> prevPrevNode = head;
                while (prevPrevNode.getNextNode().getNextNode() != nextNode){
                    prevPrevNode = prevPrevNode.getNextNode();
                }
                //if pp.getnext().getnext() == null -> update tail
                prevPrevNode.setNextNode(nextNode);
                if(nextNode == null){ //remove tail
                    tail = prevPrevNode;
                }
            }
            size--;
            iterModCount++;
            modCount++;
        }
    }

}
