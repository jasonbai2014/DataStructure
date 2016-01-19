import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * This is an array-based stack
 * 
 * @author Qing Bai
 */
public class MyStack {
	
	/**
	 * This keeps track of the index for top of this stack
	 */
	private int top;
	
	/**
	 * This is the length of internal array
	 */
	private int capacity;
	
	/**
	 * This is the array that stores data in this stack
	 */
	private int[] internalArray;
	
	/**
	 * This is constructor of this stack
	 */
	public MyStack() {
		top = -1;
		capacity = 8;
		internalArray = new int[capacity];
	}
	
	/**
	 * This checks whether or not this stack is empty
	 * 
	 * @return true if this stack is empty. Otherwise, false.
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * Looks at the integer at the top of this stack without removing it from the stack.
	 * 
	 * @return the integer on the top of this stack
	 */
	public int peek() {
		if (top == -1) {
			throw new EmptyStackException();
		}
		
		return internalArray[top];
	}
	
	/**
	 * Removes the integer at the top of this stack and returns that integer
	 * 
	 * @return the integer on top of this stack
	 */
	public int pop() {
		if (top == -1) {
			throw new EmptyStackException();
		}
		
		return internalArray[top--];	
	}
	
	/**
	 * Return size of this stack
	 * 
	 * @return size of this stack
	 */
	public int size() {
		return top + 1;
	}
	
	/**
	 * Pushes an integer onto the top of this stack.
	 * 
	 * @param num is the integer
	 */
	public void push(int num) {
		if (top + 1 == capacity) {
			capacity *= 2;
			internalArray = Arrays.copyOf(internalArray, capacity);
		}

		internalArray[++top] = num;
	}
}
