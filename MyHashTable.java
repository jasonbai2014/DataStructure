import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This is a hash table.
 * 
 * @author Qing Bai
 * @version May 16 2015
 */
public class MyHashTable<K, V> {
	
	/**
	 * This is an array used to store nodes in this hash table.
	 */
	private final MyHashTable<K, V>.Node[] myNodes;
	
	/**
	 * This is the size of the arrays.
	 */
	private final int myCapacity;
	
	/**
	 * This is histogram of probes.
	 */
	private final Map<Integer, Integer> myHistogram;
	
	/**
	 * This records number of entries.
	 */
	private int myEntryNum;
	
	/**
	 * This records the maxmium probe length spent on one entry.
	 */
	private int myProbMax;
	
	/**
	 * This records sum of probe lengthes of all entries.
	 */
	private int myProbSum;
	
	
	/**
	 * This builds a hash table with a given capacity.
	 * 
	 * @param theCapacity is the given capacity
	 */
	@SuppressWarnings("unchecked")
	public MyHashTable(final int theCapacity) {
		if (theCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		myCapacity = theCapacity;
		myNodes = new MyHashTable.Node[myCapacity];
		myHistogram = new HashMap<Integer, Integer>();
		myEntryNum = 0;
		myProbMax = 0;
		myProbSum = 0;
	}
	
	/**
	 * This adds or updates a new value for a given key.
	 * 
	 * @param theKey is the given key
	 * @param theValue is the value
	 */
	public void put(final K theKey, final V theValue) {
		if (theKey == null || theValue == null) {
			throw new IllegalArgumentException();
		}
		
		int i = hash(theKey);
		int probTimes = 1;
		Node node = myNodes[i];
		
		// This searches an index to place the key and value
		// also avoids infinite loop when the array is full
		while (node != null && !node.myKey.equals(theKey) && probTimes <= myCapacity) {
			i = (i + 1) % myCapacity;
			node = myNodes[i];
			probTimes++;
		}
		
		if (probTimes <= myCapacity) {		
			recordStats(node, probTimes);
			myNodes[i] = new Node(theKey, theValue);
		}
	}
	
	/**
	 * This records and updates number of entries, max probe length, sum of
	 * probes, and histrogram of probes.
	 * 
	 * @param theKey is the key
	 * @param theProbeTimes is number of probes spent on finding a location for
	 * the key 
	 */
	private void recordStats(final Node theNode, final int theProbeTimes) {
		//only works when adding a new key to this hash table
		if (theNode == null) {
			myEntryNum++;
			myProbSum += theProbeTimes;
			
			if (theProbeTimes > myProbMax) {
				myProbMax = theProbeTimes;
			}
			
			if (myHistogram.containsKey(theProbeTimes)) {
				myHistogram.put(theProbeTimes, myHistogram.get(theProbeTimes) + 1);
			} else {
				myHistogram.put(theProbeTimes, 1);
			}
		}
	}
	
	/**
	 * This searches a given key and returns a value for the key. if the key 
	 * doesn't exist, null is returned.
	 * 
	 * @param theKey is the given key
	 * @return a value for the key or null if the key doesn't exist
	 */
	public V get(final K theKey) {
		if (theKey == null) {
			return null;
		}
		
		int i = hash(theKey);
		int probTimes = 1;
		Node node = myNodes[i];
		
		// This stops when it finds the key, encounters null or has searched all the keys
		while (node != null && !node.myKey.equals(theKey) && probTimes <= myCapacity) {
			i = (i + 1) % myCapacity;
			node = myNodes[i];
			probTimes++;
		}
		
		return node != null && probTimes <= myCapacity ? node.myValue : null;
	}
	
	/**
	 * This checks whether or not a given key exists in this hash table.
	 * 
	 * @param theKey is the given key
	 * @return true if exists. Otherwise, false.
	 */
	public boolean containsKey(final K theKey) {
		return get(theKey) != null;
	}
	
	/**
	 * This builds a set of keys.
	 * 
	 * @return the set of keys
	 */
	public Set<K> keySet() {
		final Set<K> set = new HashSet<K>();
		Node node;
		
		for (int i = 0; i < myCapacity; i++) {
			node = myNodes[i];
			
			if (node != null) {
				set.add(node.myKey);
			}
		}
		
		return set;
	}

	/**
	 * This displays statistics for the data in the hash table.
	 */
	public void stats() {
		System.out.println("Hash Table Stats");
		System.out.println("================================");
		System.out.printf("Number of Entries: %d\n", myEntryNum);
		System.out.printf("Number of Buckets: %d\n", myCapacity);
		System.out.print("Histogram of Probes: ");
		
		final int[] histogram = new int[myProbMax];
		int probeLength;
		final Iterator<Integer> iterator = myHistogram.keySet().iterator();
		
		while (iterator.hasNext()) {
			probeLength = iterator.next();
			histogram[probeLength - 1] = myHistogram.get(probeLength);
		}
		
		System.out.println(Arrays.toString(histogram));
		System.out.printf("Fill Percentage: %.6f%%\n", myEntryNum * 100.0 / myCapacity);
		System.out.printf("Max Linear Prob: %d\n", myProbMax);
		System.out.printf("Average Linear Prob: %.6f\n", myProbSum * 1.0 / myEntryNum);
	}

	/**
	 * This determines where the key should be placed in the array.
	 * 
	 * @param theKey is the key
	 * @return index of the array in where this key should be placed
	 */
	private int hash(final K theKey) {
		return Math.abs(theKey.hashCode() % myCapacity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		final StringBuilder result = new StringBuilder();
		result.append("[");
		int commaNum = myEntryNum - 1;
		Node node;
	
		for (int i = 0; i < myCapacity; i++) {
			node = myNodes[i];
			
			if (node != null) {
				result.append(node.toString());
			
				if (commaNum > 0) {
					result.append(", ");
					commaNum--;
				}
			}
		}
		
		result.append("]");
		return result.toString();
	}
	
	/**
	 * This is a private class for Node.
	 */
	private class Node {
		
		/**
		 * This is a key stored in this node.
		 */
		public K myKey;
		
		/**
		 * This is a value stored in this node.
		 */
		public V myValue;
		
		/**
		 * This creates a node with a given key and value.
		 * 
		 * @param theKey is the given key
		 * @param theValue is the given value
		 */
		public Node(final K theKey, final V theValue) {
			myKey = theKey;
			myValue = theValue;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			final StringBuilder result = new StringBuilder();
			result.append("(");
			result.append(myKey);
			result.append(", ");
			result.append(myValue);
			result.append(")");
			return result.toString();
		}
	}
}
