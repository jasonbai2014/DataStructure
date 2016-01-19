import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This is a undirected graph using adjacency list implementation. 
 * 
 * @author Qing Bai
 */
public class MyLinkedGraph {
	
	/**
	 * This keeps all nodes in this graph.
	 */
	private Map<String, Node> nodes; 
	
	/**
	 * This is a constructor of this class.
	 */
	public MyLinkedGraph() {
		nodes = new HashMap<String, Node>();
	}
	
	/**
	 * This adds one node into this graph.
	 * 
	 * @param content is the content saved in the node
	 */
	public void addNode(String content) {
		nodes.put(content, new Node(content));
	}
	
	/**
	 * This builds an edge for two given nodes.
	 * 
	 * @param content1 is the content of one node
	 * @param content2 is the content of another node
	 */
	public void addEdge(String content1, String content2) {
		Node node1 = nodes.get(content1);
		Node node2 = nodes.get(content2);
		
		if (node1 != null && node2 != null) {
			node1.neighbors.add(node2);
			node2.neighbors.add(node1);
		}
	}
	
	/**
	 * This outputs the graph in a String.
	 * 
	 * @return a String containing contents in the nodes
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		Collection<Node> allNodes = nodes.values();
		
		for (Node node : allNodes) {
			result.append(node.content);
			result.append(" neighbors: ");
			List<Node> neighbors = node.neighbors;
			for (Node neighbor : neighbors) {
				result.append("|");
				result.append(neighbor.content);
				result.append("|");
			}
			result.append("\n");
		}
		
		return result.toString();
	}
	
	/**
	 * This is a class for nodes in this graph.
	 */
	private class Node {
		
		/**
		 * This is a list holding all neighbors of this node.
		 */
		private List<Node> neighbors;
		
		/**
		 * This is the content saved in this node.
		 */
		private String content;
		
		/**
		 * This is a constructor of this class.
		 * 
		 * @param content is a String stored in this node
		 */
		private Node(String content) {
			this.content = content;
			neighbors = new ArrayList<Node>();
		}
	}
}
