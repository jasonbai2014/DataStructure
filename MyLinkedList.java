/**
 * This is an LinkedList only for integers
 * 
 * @author Qing Bai
 */
public class MyLinkedList {
	
	/**
	 * This is the size of this LinkedList
	 */
	private int size;
	
	/**
	 * This is the head of this LinkedList
	 */
	private Node head;
	
	/**
	 * This is the tail of this LinkedList
	 */
	private Node tail;
	
	/**
	 * This is a constructor of this class
	 */
	public MyLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * This adds one integer to this LinkedList
	 * 
	 * @param num is the integer added to this LinkedList
	 */
	public void add(int num) {
		if (size == 0) {
			head = new Node(num);
			tail = head;
		} else {
			tail.next = new Node(num);
			tail = tail.next;
		}
		
		size++;
	}
	
	/**
	 * This adds one integer to a specific index
	 * 
	 * @param index is the specific index
	 * @param num is the integer
	 */
	public void add(int index, int num) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			Node node = new Node(num);
			node.next = head;
			head = node;
		} else {
			int count = 0;
			Node curNode = head;
			
			while (count < index - 1) {
				curNode = curNode.next;
				count++;
			}
			
			Node node = new Node(num);
			node.next = curNode.next;
			curNode.next = node;
		}
		
		size++;
	}
	
	/**
	 * This returns integer at a specific index
	 * 
	 * @param index is the specific index
	 * @return an integer at this specific index
	 */
	public int get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			Node curNode = head;
			
			for (int i = 0; i < index; i++) {
				curNode = curNode.next;
			}
			
			return curNode.value;
		}
	}
	
	/**
	 * This remove an integer from a specific index
	 * 
	 * @param index in the specific index
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			head = head.next;
		} else {
			int count = 0;
			Node curNode = head;
			
			while (count < index - 1) {
				curNode = curNode.next;
				count++;
			}
			
			curNode.next = curNode.next.next;
			size--;
		}
	}
	
	/**
	 * This checks whether or not a given number exists in this LinkedList
	 * 
	 * @param num is the given number
	 * @return true if the number is found. Otherwise, false
	 */
	public boolean contains(int num) {
		Node curNode = head;
		boolean result = false;
		
		while (curNode != null) {
			if (curNode.value == num) {
				result = true;
				break;
			}
			
			curNode = curNode.next;
		}
		
		return result;
	}
	
	/**
	 * This finds first index of a given number 
	 * 
	 * @param num is the given number
	 * @return index of the number or -1 if this number doesn't exist in this ArrayList
	 */
	public int indexOf(int num) {
		int index = -1;
		int count = 0;
		Node curNode = head;
		
		while (curNode != null) {
			if (curNode.value == num) {
				index = count;
				break;
			}
			
			count++;
			curNode = curNode.next;
		}
		
		return index;
	}
	
	/**
	 * Returns size of this LinkedList
	 * 
	 * @return an integer for size of this LinkedList
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns contents of this LinkedList in format of String
	 * 
	 * @return a String showing contents in this LinkedList
	 */
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		Node curNode = head;
		
		while (curNode != null) {
			result.append(curNode.value);
			result.append(",");
			curNode = curNode.next;
		}
		
		if (result.length() > 1) {
			result.setCharAt(result.length() - 1, ']');
		} else {
			result.append("]");
		}
		
		return result.toString();
	}
	
	
	/**
	 * This is a class used to create nodes in this LinkedList
	 */
	private class Node {
		
		/**
		 * This is the value stored in this node
		 */
		private int value;
		
		/**
		 * This keeps reference to next node
		 */
		private Node next;
		
		/**
		 * This is a constructor of this class
		 * 
		 * @param num is a number stored in this node
		 */
		private Node(int num) {
			value = num;
			next = null;
		}
	}
}
