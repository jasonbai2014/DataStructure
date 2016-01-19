import java.util.NoSuchElementException;

/**
 * This is an node-based queue
 * 
 * @author Qing Bai
 */
public class MyQueue {
	
	/**
	 * This keeps the reference of the first node in this queue
	 */
	private Node front;
	
	/**
	 * This keeps the reference of the last node in this queue
	 */
	private Node end;
	
	/**
	 * This is size of this queue
	 */
	private int size;
	
	/**
	 * This is constructor of this queue
	 */
	public MyQueue() {
		front = null;
		end = null;
		size = 0;
	}
	
	/**
	 * Add an integer to the end of this queue
	 * 
	 * @param num is the integer
	 */
	public void add(int num) {
		if (front == null) {
			front = new Node(num);
			end = front;
		} else {
			end.next = new Node(num);
			end = end.next;
		}
		
		size++;
	}
	
	/**
	 * Removes the first integer in this queue
	 * 
	 * @return the integer at the head of this queue
	 */
	public int remove() {
		if (front == null) {
			throw new NoSuchElementException();
		}
		
		int result = front.val;
		front = front.next;
		size--;
		return result;
	}
	
	/**
	 * Retrieves, but does not remove, the head of this queue
	 * 
	 * @return the integer at the head of this queue
	 */
	public int peek() {
		if (front == null) {
			throw new NoSuchElementException();
		}
		
		return front.val;
	}
	
	/**
	 * Checks whether or not this queue is empty
	 * 
	 * @return true if this queue is empty. Otherwise, false
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Return size of this queue
	 * 
	 * @return size of this queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This is a class used to create nodes in this queue
	 */
	private class Node {
		
		/**
		 * value stored in this node
		 */
		private int val;
		
		/**
		 * next node of this node
		 */
		private Node next;
		
		/**
		 * This is constructor of this node
		 * 
		 * @param num is value saved in this node
		 */
		private Node(int num) {
			val = num;
		}
	}
}
