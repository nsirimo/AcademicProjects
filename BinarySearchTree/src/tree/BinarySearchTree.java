
package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import exceptions.DuplicateElementException;


public class BinarySearchTree<E extends Comparable<E>> {
	public BSTNode<E> root;
	
	/**
	 * Null Constructor
	 */
	public BinarySearchTree(){
		root = null;
	}
	
	/**
	 * Generic array as constructor
	 * @param array input generic array
	 */
	public BinarySearchTree(E[] array){
		if(array.length == 0){
			throw new NullPointerException();
		}
		
		this.root = new BSTNode<E>(array[0]);
		arrayToBSTHelper(array, root);
		
	}
	
	/**
	 * Helper method for inserting the values inside the array to a binary search tree
	 * @param array input generic array
	 * @param node the first node (root)
	 */
	private void arrayToBSTHelper(E[] array, BSTNode<E> node){
		for(int i = 1; i <array.length; i++){
			System.out.println(array[i]);
			insert(array[i]);
		}
	}
	
	/**
	 * Preorder Traversal
	 * @return
	 */
	public ArrayList<BSTNode<E>> preorder(){
		return preorderHelper(root);
	}
	
	/**
	 * Helper method for preorder
	 * @param node starting node (parent node)
	 * @return an Array List of nodes that have been preorder traversed
	 */
	private ArrayList<BSTNode<E>> preorderHelper(BSTNode<E> node){
		ArrayList<BSTNode<E>> preorderArrayList = new ArrayList<>();
		if(node == null){
			return preorderArrayList;
		}
		
		preorderArrayList.add((BSTNode<E>) node);
		
		if(node.left != null){
			preorderArrayList.addAll((Collection<? extends BSTNode<E>>) preorderHelper(node.left));
		}
		
		if(node.right != null){
			preorderArrayList.addAll((Collection<? extends BSTNode<E>>) preorderHelper(node.right));
		}
		
		return preorderArrayList;
	}
	
	/** Inorder traversal
	 * 
	 * @return ArrayList of values
	 */
	public ArrayList<BSTNode<E>> inorder(){
		return inorderHelper(root);
	}
	
	/**
	 * Helper method for inorder
	 * @param node starting node (parent node)
	 * @return an Array List of nodes that have been inorder traversed
	 */
	private ArrayList<BSTNode<E>> inorderHelper(BSTNode<E> node){
		ArrayList<BSTNode<E>> inorderArrayList = new ArrayList<>();
		if(node == null){
			return inorderArrayList;
		}
		
		if(node.left != null){
			inorderArrayList.addAll(inorderHelper(node.left));
		}
		
		inorderArrayList.add((BSTNode<E>) node);
		
		if(node.right != null){
			inorderArrayList.addAll(inorderHelper(node.right));
		}
		
		return inorderArrayList;
	}
	
	/** Postorder traversal
	 * 
	 * @return ArrayList of values
	 */
	public ArrayList<BSTNode<E>> postorder(){
		return postorderHelper(root);
	}
	
	/**
	 * Helper method for postorder
	 * @param node starting node (parent node)
	 * @return an Array List of nodes that have been postOrder traversed
	 */
	private ArrayList<BSTNode<E>> postorderHelper(BSTNode<E> node){
		ArrayList<BSTNode<E>> postorderArrayList = new ArrayList<>();
		if(node == null){
			return postorderArrayList;
		}
		
		if(node.left != null){
			postorderArrayList.addAll(postorderHelper(node.left));
		}
		
		if(node.right != null){
			postorderArrayList.addAll(postorderHelper(node.right));
		}
		
		postorderArrayList.add((BSTNode<E>) node);
		
		return postorderArrayList;
	}
	
	/** BreadthFirst traversal
	 * 
	 * @return ArrayList of values
	 */
	public ArrayList<BSTNode<E>> breadthFirst(){
		return breadthFirstHelper(root);
	}
	
	/**
	 * Helper method for BreadthFirst
	 * @param node starting node (parent node)
	 * @return an Array List of nodes that have been BreadthFirst traversed
	 */
	private ArrayList<BSTNode<E>> breadthFirstHelper(BSTNode<E> node){
		ArrayList<BSTNode<E>> breadthFirstArrayList = new ArrayList<>();
		Queue<BSTNode<E>> breadthFirstQueue = new LinkedList<>();
		breadthFirstQueue.add(node);
		
		while(!breadthFirstQueue.isEmpty()){
			BSTNode<E> node2 = breadthFirstQueue.poll();
			if(node == null){
				return (ArrayList<BSTNode<E>>) breadthFirstArrayList;
			}
			
			breadthFirstArrayList.add((BSTNode<E>) node);
			
			if(node.left != null){
				breadthFirstArrayList.addAll((Collection<? extends BSTNode<E>>) postorderHelper(node.left));
			}
			
			if(node.right != null){
				breadthFirstArrayList.addAll((Collection<? extends BSTNode<E>>) postorderHelper(node.right));
			}
		}
		
		return breadthFirstArrayList;
	}
	
	/**
	 * Finds the node that is appropriate to delete.
	 * @param key value of the node to delete
	 * @return null
	 */
	private BSTNode<E> nodeToDelete(E key){
		BSTNode<E> currentNode = root;
		while (currentNode != null){
			int compareToValue = key.compareTo(currentNode.getData());
			
			if(key == currentNode.getData()){
				return currentNode;
			}
			
			else if(compareToValue < 0){
				currentNode = currentNode.left;
			}
			
			else if(compareToValue > 0){
				currentNode = currentNode.right;
			}
		}
		
		return null;
	}
	
	/**
	 * Starter delete, first finds the node to delete, then throws it into overloaded method delete. 
	 * @param key value
	 */
	public void delete(E key){
		delete(nodeToDelete(key));
	}
	
	/**
	 * Helper method for the actual delete. Takes the node found to be deleted properly depending on how many children that node has, then reassigns the children properly. 
	 * @param node
	 */
	private void delete(BSTNode<E> node){
		if(isLeaf(node)){
			if(isLeftChild(node)){
				node.parent.left = null;
			}
			else if(isRightChild(node)){
				node.parent.right = null;
			}
		}
		
		else if(numChildren(node) == 1){
			BSTNode<E> childNode = null; 
			if(node.left == null){
				childNode = node.right;
			}
			
			if(node.right == null){
				childNode = node.left;
			}
			
			if(isLeftChild(node)){
				node.parent.left = childNode;
			}
			
			else if(isRightChild(node)){
				node.parent.right = childNode;
			}
		}
		
		else if(numChildren(node) == 2){
			BSTNode<E> max = maxSubtree(node.left); 
			node.setData(max.getData());
			delete(max);
		}
		
	}
	
	/**
	 * Helper for finding maximum value in the tree.
	 * @param node
	 * @return Node that contains the highest value element. 
	 */
	private BSTNode<E> maxSubtree(BSTNode<E> node){
	        BSTNode<E> currentLeftNode = node;
	        BSTNode<E> currentRightNode = node; 
	        BSTNode<E> maxNode = node;
	 

	        while (currentLeftNode.left != null) {
	        	int compareValue = currentLeftNode.getData().compareTo(maxNode.getData());
	 	        
	 	        if(compareValue > 0){
	 	        	maxNode = currentLeftNode;
	 	        }
	 	        
	 	       currentLeftNode = currentLeftNode.left;
	        }

	        currentRightNode = currentLeftNode;
	        while (currentRightNode.right != null){
	        	int compareValue = currentRightNode.right.getData().compareTo(maxNode.getData());
	 	        
	 	        if(compareValue > 0){
	 	        	maxNode = currentRightNode.right;
	 	        }

	        	currentRightNode = currentRightNode.right;
	        }
	        
	        return maxNode;
	        
	}
	
	/**
	 * Finds number of children for that node. 
	 * @param node
	 * @return returns the int of children counted.
	 */
	private int numChildren(BSTNode<E> node){
		int childrenCount = 0;
		
		if(node.left != null){
			childrenCount++;
		}
		
		if(node.right != null){
			childrenCount++;
		}
		
		return childrenCount;
	}

	/**
	 * Finds the count of all the children contained underneath a node, basicall counts the whole subtree underneath. 
	 * @param node
	 * @return how many children were counted. 
	 */
	private int childrenCounter(BSTNode<E> node){
		if(node == null){
			return 0;
		}
		
		return 1 + numChildren(node.left) + numChildren(node.right);
	}
	
	/**
	 * Find the depth of that node
	 * @param node
	 * @return count of depth
	 */
	public int depth(BSTNode<E> node){
		if(node == root){
			return 0;
		}
		else{
			return 1 + depth(node.parent);
		}
	}
	
	/**
	 * Finds the height of the tree from that node
	 * @param node
	 * @return count of the height
	 */
	public int height(BSTNode<E> node){ 
		if(node == null){
			return 0;
		}
		
		else{
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}
	
	/**
	 * Finds the uncle of the inputed node
	 * @param node
	 * @return the uncle node
	 */
	public BSTNode<E> uncle(BSTNode<E> node){
		if(node == null){
			return null;
		}
		
		else{
			if(isLeftChild(node.parent)){
				return node.parent.parent.right;
			}
			
			else if(isRightChild(node.parent)){
				return node.parent.parent.left;
			}
			
			else{
				return null;
			}
		}
	}
	
	/**
	 * Searches through the BST to find the node who stores that value.
	 * @param key
	 * @return true if that key value exists in one of the nodes in the BST 
	 */
	public boolean find(E key){
		BSTNode<E> currentNode = root;
		while (currentNode != null){
			int compareToValue = key.compareTo(currentNode.getData());
			
			if(key == currentNode.getData()){
				return true;
			}
			
			else if(compareToValue < 0){
				currentNode = currentNode.left;
			}
			
			else if(compareToValue > 0){
				currentNode = currentNode.right;
			}
		}
		
		return false;
	}
	
	/**
	 * Finds the grandparent node of the input node if it exists 
	 * @param node
	 * @return grandParent node
	 */
	public BSTNode<E> grandParent(BSTNode<E> node){
		if(node.parent.parent == null){
			return null;
		}
		else{
			return node.parent.parent;
		}
	}
	
	/**
	 * Finds the sibling node of the input node if it exists 
	 * @param node
	 * @return sibling node
	 */
	public BSTNode<E> sibling(BSTNode<E> node){
		if(isRightChild(node)){
			return node.parent.left;
		}
		
		else if(isLeftChild(node)){
			return node.parent.right;
		}
		
		else{
			return null;
		}
	}
	
	/**
	 * Checks if the node is a left child
	 * @param node
	 * @return true if node is left child
	 */
	public boolean isLeftChild(BSTNode<E> node){
		boolean leftChild = false;

		if(node.parent.left == node){
			leftChild = true;
		}
		
		return leftChild;
	}
	
	/**
	 * Checks if the node is a right child
	 * @param node
	 * @return true if node is right child
	 */
	public boolean isRightChild(BSTNode<E> node){
		boolean rightChild = false;
		if(node.parent.right == node){
			rightChild = true;
		}
		
		return rightChild;
	}
	
	/**
	 * Checks if the node is a leaf
	 * @param node
	 * @return true if node is leaf
	 */
	public boolean isLeaf(BSTNode<E> node){
		boolean leafBool = false;
		if(node.left == null && node.right == null){
			leafBool = true;
		}
		return leafBool;
	}
	
	/**
	 * Checks if the tree is empty
	 * @return true if tree is empty
	 */
	public boolean isEmpty(){
		boolean treeEmpty = true;
		if(root != null){
			treeEmpty = false;
		}
		return treeEmpty;
	}
	
	/**
	 * Helper method for finding where to insert the value in the binary search tree
	 * @param key
	 * @return the node in the correct place to insert
	 */
	protected BSTNode<E> insertionPoint(E key){
		BSTNode<E> currentNode = root;
		BSTNode<E> parentNode = null;
		
		while (currentNode != null){
			int compareToValue = key.compareTo(currentNode.getData());
			
			if(key == currentNode.getData()){
				throw new DuplicateElementException();
			}
			
			else if(compareToValue < 0){
				parentNode = currentNode;
				currentNode = currentNode.left;
			}
			
			else if(compareToValue > 0){
				parentNode = currentNode;
				currentNode = currentNode.right;
			}
		}
		
		
		return parentNode;
	}
	
	/**
	 * Method to try to insert the key into a new node, which will be placed appropriately into the BST.
	 * @param key
	 */
	public void insert(E key){
		BSTNode<E> childNode = new BSTNode<>(key);
		
		if(root == null){
			root = childNode;
		}
		
		else{
			try{
				BSTNode<E> parentNode = insertionPoint(key);
				int compareToValue = key.compareTo(parentNode.getData());
				if(compareToValue < 0){
					parentNode.left = childNode;
					childNode.parent = parentNode;
				}
				else if(compareToValue > 0){
					parentNode.right = childNode;
					childNode.parent = parentNode;
				}
				
			}catch(DuplicateElementException e){
				throw e;
			}
		}
	}
	
	/**
	 * Print Tree
	 */
	public void printTree() {

	    if (this.root.right != null) {
	        this.printTree(this.root.right, true, "");
	    }

	    printNodeValue(this.root);

	    if (this.root.left != null) {
	        this.printTree(this.root.left, false, "");
	    }
	}

	/**
	 * Print Tree Helper
	 * @param node
	 * @param isRight
	 * @param indent
	 */
	public void printTree(BSTNode<E> node, boolean isRight, String indent) {
	    if (node.right != null) {
	        printTree(node.right, true, indent + (isRight ? "        " : " |      "));
	    }

	    System.out.print(indent);

	    if (isRight) {
	        System.out.print(" /");
	    }
	    else {
	        System.out.print(" \\");
	    }
	    System.out.print("----- ");
	    printNodeValue(node);
	    if (node.left != null) {
	        printTree(node.left, false, indent + (isRight ? " |      " : "        "));
	    }
	}

	/**
	 * Print Node Value
	 * @param node
	 */
	private void printNodeValue(BSTNode<E> node) {
	    if (node == null) {
	        System.out.print("<null>");
	    }
	    else {
	        System.out.print(node.getData());
	    }
	    System.out.println();
	}

}
