package array2D;

public class Array2DNode <E>{
	private E item;
	protected Array2DNode<E> down;
	protected Array2DNode<E> right;
	
	/**
	 * Default Constructor
	 */
	public Array2DNode() {
	}
	
	/**
	 * Constructor that takes in a passed generic.
	 * @param item passed in generic type
	 */
	public Array2DNode(E item){
		this.item = item;
		this.down = null;
		this.right = null;
	}
	
	/**
	 * Gets the item from this node.
	 * @return the item inside this node.
	 */
	public E getItem(){
			return this.item;
	}
	
	/**
	 * Sets the item in this node
	 * @param item New item that goes into this node.
	 */
	public void setItem(E item){
		this.item = item;
	}

}
