import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author 
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
	private int modCount;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
	}

	public void expandIfNecessary(){
		if (rear == size()){
			expandCapacity();
		}
	}

	//TODO: Redo add to front so that rear gets set in the right spot and
	@Override
	public void addToFront(T element) {
		expandIfNecessary();
		//shift elements
		for (int i = rear; i > 0; i--) {
			array[i] = array[i-1];
		}
		rear++;
		array[0] = element;
		// array[rear] = null;
		modCount++;
		
	}

	@Override
	public void addToRear(T element) {
		expandIfNecessary();
        array[rear] = element;
		rear++;
		modCount++;
	}

	@Override
	public void add(T element) {
		expandIfNecessary();
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		expandIfNecessary();
		if(!contains(target)){
			throw new NoSuchElementException();
		}

		int index = indexOf(target);

		rear++;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i+1] = array[i];
		}
		array[index + 1] = element;

		modCount++;
		
	}

	@Override
	public void add(int index, T element) {
		expandIfNecessary();
		if(index < 0 || index > rear){
			throw new IndexOutOfBoundsException();
		}

		rear++;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i+1] = array[i];
		}
		array[index] = element;

		modCount++;

	}

	@Override
	public T removeFirst() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return remove(0);
	}

	@Override
	public T removeLast() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return remove(rear-1);
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= rear){
			throw new IndexOutOfBoundsException();
		}
		array[index] = element;
		modCount++;
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= rear){
			throw new IndexOutOfBoundsException();
		}
        return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}
		
		return index;
	}

	@Override
	public T first() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public T last() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return array[rear-1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		if(array[0] == null){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return rear;
	}

	@Override
	public String toString(){
		String val = new String("[");
		for (T t : array){
			if (!(t == null)){
				val += t + " ";
			}
		}
		val += "]";
		return val;
	}

	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private boolean canRemove;
		
		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount){
				throw new ConcurrentModificationException();
			}
            return nextIndex < rear;
		}

		@Override
		public T next() {
			// if (iterModCount != modCount){
			// 	throw new ConcurrentModificationException();
			// }
			if(!hasNext()){
                throw new NoSuchElementException();
            }
            nextIndex++;
			canRemove = true;
            return array[nextIndex-1];
		}
		
		@Override
		public void remove() {
			if (iterModCount != modCount){
				throw new ConcurrentModificationException();
			}
			// if (isEmpty()){
			// 	throw new NoSuchElementException();
			// }
			if(!canRemove){
				throw new IllegalStateException();
			}
			canRemove = false;
			for(int i = nextIndex - 1; i < rear-1; i++){
				array[i] = array[i+1];
			}
			array[rear-1] = null;
			rear--;
			modCount++;
			iterModCount++;
			nextIndex--;
		}
	}
}