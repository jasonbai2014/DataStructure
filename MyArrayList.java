import java.util.Arrays;

/**
 * This is an ArrayList only for integers
 * 
 * @author Qing Bai
 */
public class MyArrayList {
	
	/**
	 * size of the ArrayList
	 */
	private int size;
	
	/**
	 * length of the internal array
	 */
	private int capacity;
	
	/**
	 * this is an array storing data in this ArrayList
	 */
	private int[] internalArray;
	
	/**
	 * This is a constructor of this ArrayList.
	 */
	public MyArrayList() {
		this(16);
	}
	
	/**
	 * This is a constructor of this ArrayList.
	 *  
	 * @param initialCapacity is initial capacity of this ArrayList
	 */
	public MyArrayList(int initialCapacity) {
		size = 0;
		capacity = initialCapacity;
		internalArray = new int[initialCapacity];
	}
	
	/**
	 * This adds one integer to this ArrayList
	 * 
	 * @param num is the integer added to this ArrayList
	 */
	public void add(int num) {
		if (size < capacity) {
			internalArray[size] = num;
			size++;
		} else {
			// double the size of the internalArray
			capacity *= 2;
			internalArray = Arrays.copyOf(internalArray, capacity);
			internalArray[size] = num;
			size++;
		}
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
			return internalArray[index];
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
		} else {
			for (int i = index + 1; i < size; i++) {
				internalArray[i - 1] = internalArray[i];
			}
			
			size--;
		}
	}
	
	/**
	 * This finds first index of a given number 
	 * 
	 * @param num is the given number
	 * @return index of the number or -1 if this number doesn't exist in this ArrayList
	 */
	public int indexOf(int num) {
		int index = -1;
		
		for (int i = 0; i < size; i++) {
			if (internalArray[i] == num) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	/**
	 * This checks whether or not a given number exists in this ArrayList
	 * 
	 * @param num is the given number
	 * @return true if the number is found. Otherwise, false
	 */
	public boolean contains(int num) {
		boolean result = false;
		
		for (int i = 0; i < size; i++) {
			if (internalArray[i] == num) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Returns size of this ArrayList
	 * 
	 * @return an integer for size of this ArrayList
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns contents of this ArrayList in format of String
	 * 
	 * @return a String showing contents in this ArrayList
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
