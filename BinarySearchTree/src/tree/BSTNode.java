package tree;

public class BSTNode<E extends Comparable<E>> {
	private E data;
	public BSTNode<E> parent;
	public BSTNode<E> left;
	public BSTNode<E> right;
	
	/**
	 * Constructor with null pointers but a in data for that node
	 * @param data
	 */
	public BSTNode(E data) {
		this.data = data;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Returns the data in the node
	 * @return
	 */
	public E getData(){
		return this.data;
	}
	
	/**
	 * Sets the data in the node
	 * @param key
	 */
	public void setData(E key){
		this.data = key;
	}
	
}
