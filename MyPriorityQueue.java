import java.util.Arrays;

/**
 * This is array based priority queue.
 * 
 * @author Qing Bai
 */
public class MyPriorityQueue {
	
	/**
	 * This is current size of the PriorityQueue
	 */
	private int size;
	
	/**
	 * This is capacity of the array
	 */
	private int capacity;
	
	/**
	 * This is an array storing data in this PriorityQueue
	 */
	private int[] internalArray;
	
	/**
	 * This is constructor of this PriorityQueue.
	 */
	public MyPriorityQueue() {
		this(16);
	}

	/**
	 * This is constructor of this PriorityQueue.
	 * 
	 * @param capacity is initial capacity of the array
	 */
	public MyPriorityQueue(int capacity) {
		size = 0;
		this.capacity = capacity;
		internalArray = new int[capacity];
	}
	
	/**
	 * This adds one value to this PriorityQueue.
	 * 
	 * @param val is a value added into this PriorityQueue
	 */
	public void add(int val) {
		if (size == capacity) {
			capacity *= 2;
			internalArray = Arrays.copyOf(internalArray, capacity);
		}
		
		internalArray[size++] = val;
		int childIndex = size - 1;
		int parentIndex = (childIndex - 1) / 2;
		while (parentIndex >= 0 && internalArray[childIndex] < internalArray[parentIndex]) {
			swap(parentIndex, childIndex);
			childIndex = parentIndex;
			parentIndex = (childIndex - 1) / 2;
		}
	}
	
	/**
	 * This removes and returns the minimum number of this priority queue.
	 * 
	 * @return the minimum number of this PriorityQueue or throw an exception
	 * when this PriorityQueue is empty
	 */
	public int poll() {
		if(isEmpty()) {
			throw new IllegalArgumentException("PriorityQueue Is Empty");
		}
		
		int result = internalArray[0];
		internalArray[0] = internalArray[--size];
		int parentIndex = 0;
		int leftChildIndex = 2 * parentIndex + 1;
		int rightChildIndex = 2 * parentIndex + 2;
		while (leftChildIndex < size) {
			int targetChildIndex = leftChildIndex;
			if (rightChildIndex < size && internalArray[leftChildIndex] > internalArray[rightChildIndex]) {
				targetChildIndex = rightChildIndex;
			}
			
			if (internalArray[parentIndex] > internalArray[targetChildIndex]) {
				swap(parentIndex, targetChildIndex);
				parentIndex = targetChildIndex;
				leftChildIndex = 2 * parentIndex + 1;
				rightChildIndex = 2 * parentIndex + 2;
			} else {
				break;
			}
		} 
		
		return result;
	}
	
	/**
	 * This is a helper method swapping value of a parent node with value of its child node.
	 * 
	 * @param parentIndex is index of the parent node in the array
	 * @param childIndex is index of the child node in the array
	 */
	private void swap(int parentIndex, int childIndex) {
		if (parentIndex < 0 || childIndex < 0 || parentIndex >= size || childIndex >= size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		
		int temp = internalArray[parentIndex];
		internalArray[parentIndex] = internalArray[childIndex];
		internalArray[childIndex] = temp;
	}
	
	/**
	 * This returns size of this PriorityQueue.
	 * 
	 * @return size of this PriorityQueue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This checks whether or not this PriorityQueue is empty. 
	 * 
	 * @return true if this PriorityQueue is empty. Otherwise, false
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns contents of this PriorityQueue in format of String.
	 * 
	 * @return a String showing contents in this PriorityQueue
	 */
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		
		for (int i = 0; i < size; i++) {
			result.append(internalArray[i]);
			result.append(",");
		}
		
		if (result.length() > 1) {
			result.setCharAt(result.length() - 1, ']');
		} else {
			result.append("]");
		}
		
		return result.toString();
	}
}
