/**
 * This is a self-balancing binary search tree
 * 
 * @author Qing Bai
 */
public class MyBinarySearchTree {
	
	/**
	 * This is the root of this BST.
	 */
	private Node root;
	
	/**
	 * This is a constructor of this BST.
	 */
	public MyBinarySearchTree() {
		this.root = null;	
	}
	
	/**
	 * This adds one given value into this BST.
	 * 
	 * @param val is the given value
	 */
	public void add(int val) {
		this.root = add(val, this.root);
	}
	
	/**
	 * This is the helper method of add().
	 * 
	 * @param val is a given value added into this BST.
	 * @param root is root of the tree.
	 * @return root of the tree with newly added node
	 */
	private Node add(int val, Node root) {
		if (root == null) {
			root = new Node(val);
		} else if (root.val > val) {
			root.leftChild = add(val, root.leftChild);
			updateNode(root);
			root = rebalance(root);
		} else {
			root.rightChild = add(val, root.rightChild);
			updateNode(root);
			root = rebalance(root);
		}
		
		return root;
	}
	
	/**
	 * This removes a given value in this BST if the value exists.
	 * 
	 * @param val is the value
	 */
	public void remove(int val) {
		root = remove(val, this.root);
	}
	
	/**
	 * This is a helper method of remove().
	 * 
	 * @param val is the target value
	 * @param root is the root of the BST
	 * @return root of a BST where the value has been removed
	 */
	private Node remove(int val, Node root) {
		if (root == null) {
			return null;
		}
		
		if (root.val == val) {
			if (root.leftChild == null && root.rightChild == null) {
				return null;
			} else if (root.leftChild == null) {
				return root.rightChild;
			} else if (root.rightChild == null) {
				return root.leftChild;
			} else {
				root.val = findMax(root.leftChild);
				root.leftChild = removeMax(root.leftChild);
			}
		} else if (root.val < val) {
			root.rightChild = remove(val, root.rightChild);
			updateNode(root);
			root = rebalance(root);
		} else {
			root.leftChild = remove(val, root.leftChild);
			updateNode(root);
			root = rebalance(root);
		}
		
		return root;
	}
	
	/**
	 * This finds out the maximum value in the BST.
	 * 
	 * @param root is the root of the BST
	 * @return maximum value found in the tree
	 */
	private int findMax(Node root) {
		if (root.rightChild == null) {
			return root.val;
		} else {
			return findMax(root.rightChild);
		}
	}
	
	/**
	 * This removes the maximum value from the BST.
	 * 
	 * @param root is the root of the BST
	 * @return root of the BST with one node removed
	 */
	private Node removeMax(Node root) {
		if (root.rightChild == null) {
			return root.leftChild;
		} else {
			root.rightChild = removeMax(root.rightChild);
			updateNode(root);
			return rebalance(root);
		}
	}
	
	/**
	 * This checks whether or not a given value exists in the BST.
	 * 
	 * @param val is the given value
	 * @return true if the value exists in the tree, otherwise, false
	 */
	public boolean contains(int val) {
		return contains(val, this.root);
	}
	
	/**
	 * This is a helper method of contains().
	 * 
	 * @param val is the target value
	 * @param root is the root of the BST
	 * @return true if the value is found in the tree, otherwise, false
	 */
	private boolean contains(int val, Node root) {
		if (root == null) {
			return false;
		} else if (root.val == val) {
			return true;
		} else {
			return contains(val, root.leftChild) || contains(val, root.rightChild);
		}
	}
	
	/**
	 * This updates balance factor and height of a given node in the BST.
	 * 
	 * @param node is the given node
	 */
	private void updateNode(Node node) {
		int leftHeight = node.leftChild == null ? -1 : node.leftChild.height;
		int rightHeight = node.rightChild == null ? -1 : node.rightChild.height;
		node.height = Math.max(leftHeight, rightHeight) + 1;
		node.balanceFactor = leftHeight - rightHeight;
	}
	
	/**
	 * This rotates the tree to the left.
	 * 	
	 * @param root is the root of the tree
	 * @return the root of rotated tree
	 */
	private Node rotateLeft(Node root) {
		Node temp = root.rightChild;
		root.rightChild = temp.leftChild;
		updateNode(root);
		temp.leftChild = root;
		updateNode(temp);
		return temp;
	}
	
	/**
	 * This rotates the tree to the right.
	 * 
	 * @param root is the root of the tree
	 * @return the root of rotated tree
	 */
	private Node rotateRight(Node root) {
		Node temp = root.leftChild;
		root.leftChild = temp.rightChild;
		updateNode(root);
		temp.rightChild = root;
		updateNode(temp);
		return temp;
	}
	
	/**
	 * This rebalances a given BST.
	 * 
	 * @param root is the root of the given BST
	 * @return root of a balanced BST.
	 */
	private Node rebalance(Node root) {
		if (root.balanceFactor < -1) {
			if (root.rightChild.balanceFactor > 0) {
				root.rightChild = rotateRight(root.rightChild);
			}
			
			root = rotateLeft(root);
		} else if (root.balanceFactor > 1) {
			if (root.leftChild.balanceFactor < 0) {
				root.leftChild = rotateLeft(root.leftChild);
			}
			
			root = rotateRight(root);
		}
		
		return root;
	}
	
	/**
	 * This finds the depth of this BST.
	 * 
	 * @return an integer for the depth of this BST.
	 */
	public int depth() {
		return depth(this.root);
	}
	
	/**
	 * This is a helper method of depth().
	 * 
	 * @param root is the root of a BST
	 * @return depth of the BST
	 */
	private int depth(Node root) {
		if (root == null) {
			return 0;
		} else {
			return Math.max(depth(root.leftChild), depth(root.rightChild)) + 1;
		}
	}
	
	/**
	 * This returns contents in this BST using inorder.
	 * 
	 * @return a String containing values in this BST
	 */
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		toString(this.root, result);
		
		if (result.length() > 1) {
			result.setCharAt(result.length() - 1, ']');
		} else {
			result.append("]");
		}
		
		return result.toString();
	}
	
	/**
	 * This is a helper method of toString().
	 * 
	 * @param root is the root of a BST
	 */
	private void toString(Node root, StringBuilder result) {
		if (root == null) {
			result.append("");
		} else {
			toString(root.leftChild, result);
			result.append(root.val + ",");
			toString(root.rightChild, result);
		}
	}
	
	/**
	 * This is a private class used for creating nodes of this BST.
	 */
	private class Node {
		
		/**
		 * This is a value saved in this node.
		 */
		private int val;
		
		/**
		 * This is the height of this node in the tree.
		 */
		private int height;
		
		/**
		 * This is the balance factor of this node.
		 */
		private int balanceFactor;
		
		/**
		 * This is left child of this node.
		 */
		private Node leftChild;
		
		/**
		 * This is right child of this node.
		 */
		private Node rightChild;
		
		/**
		 * This is a constructor of this node.
		 * 
		 * @param val is a value stored in the node
		 */
		private Node(int val) {
			this(val, null, null);
		}
		
		/**
		 * This is a constructor of this node.
		 * 
		 * @param val is a value stored in the node
		 * @param leftChild is left child of the node
		 * @param rightChild is right child of the node
		 */
		private Node(int val, Node leftChild, Node rightChild) {
			this.val = val;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			balanceFactor = 0;
			height = 0;
		}
	}
}
