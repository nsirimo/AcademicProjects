package array2D;

public class Array2D<E> {
	int rows;
	int cols;
	public Array2DNode<E> head = null;

	/**
	 * Create an empty array.
	 */
	public Array2D() {
		this.rows = 0;
		this.cols = 0;
	}

	/**
	 * Creates an 2d linked list with empty nodes.
	 * 
	 * @param cols
	 *            column amount
	 * @param rows
	 *            row amount
	 */
	public Array2D(int cols, int rows) {
		this.rows = rows;
		this.cols = cols;
		this.head = new Array2DNode<>();
		Array2DNode<E> rowNode = head;
		Array2DNode<E> colNode = head;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (j != cols - 1) {
					colNode.right = new Array2DNode<>();
					colNode = colNode.right;
				}
			}
			if (i != rows - 1) {
				rowNode.down = new Array2DNode<>();
				rowNode = rowNode.down;
				colNode = rowNode;
			}
		}

		// Fixes all the connections
		Array2DNode<E> colNode1 = head;
		Array2DNode<E> colNode2 = head.down;
		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cols - 1; j++) {
				colNode1 = colNode1.right;
				colNode2 = colNode2.right;
				colNode1.down = colNode2;
			}
			colNode1 = head;
			colNode2 = head.down;
			if (i != rows - 2) {
				for (int j = 0; j < i + 1; j++) {
					colNode1 = colNode1.down;
					colNode2 = colNode2.down;
				}
			}
		}
	}

	/**
	 * Grab an array and transforms it into a 2D Linked List
	 * 
	 * @param arrayIn
	 *            Generic Array
	 */
	public Array2D(E[][] arrayIn) {
		this.rows = arrayIn.length;
		this.cols = arrayIn[0].length;
		this.head = new Array2DNode<>();
		Array2DNode<E> rowNode = head;
		Array2DNode<E> colNode1 = head;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				colNode1.setItem(arrayIn[i][j]);
				if (j != cols - 1) {
					colNode1.right = new Array2DNode<>();
					colNode1 = colNode1.right;
				}
			}
			if (i != rows - 1) {
				rowNode.down = new Array2DNode<>();
				rowNode = rowNode.down;
				colNode1 = rowNode;
			}
		}

		Array2DNode<E> colNode2 = head;
		Array2DNode<E> colNode3 = head.down;
		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cols - 1; j++) {
				colNode2 = colNode2.right;
				colNode3 = colNode3.right;
				colNode2.down = colNode3;
			}
			colNode2 = head;
			colNode3 = head.down;
			if (i != rows - 2) {
				for (int j = 0; j < i + 1; j++) {
					colNode2 = colNode2.down;
					colNode3 = colNode3.down;
				}
			}
		}
	}

	/**
	 * Deletes the column at the given index.
	 * 
	 * @param index
	 *            index of the column to delete
	 */
	public void deleteCol(int index) {
		if (index < 0 || index >= cols) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			deleteFirstCol();
		} else if (index == cols - 1) {
			deleteLastCol();
		} else {
			Array2DNode<E> currNode = head;
			for (int i = 0; i < index - 1; i++) {
				currNode = currNode.right;
			}
			currNode.right = currNode.right.right;
			while (currNode.down != null) {
				currNode = currNode.down;
				currNode.right = currNode.right.right;
			}
			cols--;
		}
	}

	/**
	 * Deletes the row at the given index.
	 * 
	 * @param index
	 *            index of the row to delete
	 */
	public void deleteRow(int index) {
		if (index < 0 || index >= rows) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			deleteFirstRow();
		} else if (index == rows - 1) {
			deleteLastRow();
		} else {
			Array2DNode<E> currNode = head;
			int counterRow = 0;
			while (counterRow != index - 1) {
				currNode = currNode.down;
				counterRow++;
			}
			currNode.down = currNode.down.down;
		}
		rows--;
	}

	/**
	 * Adds a column in the position of the index.
	 * 
	 * @param index
	 *            This is the index of the column to add
	 */
	public void addCol(int index) {
		if (index < 0 || index > cols) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirstCol();
		} else if (index == cols) {
			addLastCol();
		} else {
			Array2DNode<E> currNode = head;
			Array2DNode<E> colNode1 = new Array2DNode<>();
			for (int i = 0; i < index - 1; i++) {
				currNode = currNode.right;
			}
			colNode1.right = currNode.right;
			currNode.right = colNode1;
			while (currNode.down != null) {
				currNode = currNode.down;
				colNode1.down = new Array2DNode<>();
				colNode1 = colNode1.down;
				colNode1.right = currNode.right;
				currNode.right = colNode1;
			}
			cols++;
		}
	}

	/**
	 * Adds a row in the position of the index.
	 * 
	 * @param index
	 *            row index
	 */
	public void addRow(int index) {
		if (index < 0 || index > rows) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirstRow();
		} else if (index == rows) {
			addLastRow();
		} else {
			Array2DNode<E> currNode = head;
			Array2DNode<E> rowNode = new Array2DNode<>();
			for (int i = 0; i < index - 1; i++) {
				currNode = currNode.down;
			}
			rowNode.down = currNode.down;
			currNode.down = rowNode;
			while (currNode.right != null) {
				currNode = currNode.right;
				rowNode.right = new Array2DNode<>();
				rowNode = rowNode.right;
				rowNode.down = currNode.down;
				currNode.down = rowNode;
			}
			rows++;
		}
	}

	/**
	 * Takes the head, and moves it behind the new node and then fixes all the connections.
	 * 
	 * @param item
	 *            element that goes into the node.
	 */
	public void addFirstCol() {
		if (rows == 0) {
			rows = 1;
		}
		Array2DNode<E> newNode = new Array2DNode<>();
		Array2DNode<E> currNode = head;
		newNode.right = currNode;
		head = newNode;
		//Keeps adding till the end of the column size
		while (currNode.down != null) {
			newNode.down = new Array2DNode<>();
			newNode = newNode.down;
			currNode = currNode.down;
			newNode.right = currNode;
		}
		cols++;

	}

	/**
	 * Takes the head, and moves it behind the new node.
	 * 
	 * @param item
	 *            element that goes into the node.
	 */
	public void addFirstRow() {
		if (cols == 0) {
			cols = 1;
		}
		Array2DNode<E> newNode = new Array2DNode<>();
		Array2DNode<E> currNode = head;
		newNode.down = currNode;
		head = newNode;
		//Keeps adding till the end of the row size
		while (currNode.right != null) {
			newNode.right = new Array2DNode<>();
			newNode = newNode.right;
			currNode = currNode.right;
			newNode.down = currNode;
		}
		rows++;
	}

	/**
	 * Pointer hops until it hits the end, then adds the node to that spot.
	 * 
	 * @param item
	 */
	public void addLastCol() {
		if (rows == 0) {
			rows = 1;
		}
		Array2DNode<E> currNode = head;
		Array2DNode<E> newNode = new Array2DNode<>();
		while (currNode.right != null) {
			currNode = currNode.right;
		}
		currNode.right = newNode;
		while (currNode.down != null) {
			newNode.down = new Array2DNode<>();
			newNode = newNode.down;
			currNode = currNode.down;
			currNode.right = newNode;
		}
		cols++;
	}

	/**
	 * Pointer that hops the last row and then adds the node to that spot.
	 * 
	 * @param item
	 */
	public void addLastRow() {
		if (cols == 0) {
			cols = 1;
		}
		Array2DNode<E> currNode = head;
		Array2DNode<E> newNode = new Array2DNode<>();
		while (currNode.down != null) {
			currNode = currNode.down;
		}
		currNode.down = newNode;
		while (currNode.right != null) {
			newNode.right = new Array2DNode<>();
			newNode = newNode.right;
			currNode = currNode.right;
			currNode.down = newNode;
		}
		rows++;
	}

	/**
	 * Gets the item from the first node that is at the specific column.
	 * 
	 * @param colIndex
	 * @return Item of that node
	 */
	public Array2DNode<E> getCol(int colIndex) {
		Array2DNode<E> currNode = this.head;
		if (cols < colIndex) {
			return null;
		} else {
			int count = 0;
			while (count < colIndex - 1) {
				currNode = currNode.right;
				count++;
			}

			return currNode.right;
		}
	}

	/**
	 * Gets the item from that first node in that specific row.
	 * 
	 * @param rowIndex
	 * @return Item of that node
	 */
	public Array2DNode<E> getRow(int rowIndex) {
		Array2DNode<E> currNode = this.head;
		if (rows < rowIndex) {
			return null;
		} else {
			int count = 0;
			while (count < rowIndex - 1) {
				currNode = currNode.down;
				count++;
			}

			return currNode.down;
		}
	}

	/**
	 * Gets the item from the node from the specific column and specific row.
	 * 
	 * @param col
	 * @param row
	 * @return Item from that specific node
	 */
	public E get(int col, int row) {
		if (row < 0 || row >= this.rows || col >= this.cols || col < 0) {
			throw new IndexOutOfBoundsException();
		}
		Array2DNode<E> currNode = head;
		for (int i = 0; i < row; i++) {
			currNode = currNode.down;
		}
		for (int i = 0; i < col; i++) {
			currNode = currNode.right;
		}
		return currNode.getItem();
	}

	/**
	 * Pointer hops the columns, and then the rows, then replaces that specific
	 * node with a new node form the passed in item.
	 * 
	 * @param col
	 *            location
	 * @param row
	 *            location
	 * @param item
	 *            Put into the new Node
	 */
	public void set(int col, int row, E item) {
		Array2DNode<E> newNode = new Array2DNode<E>(item);

		if (col == 0) {
			addFirstCol();
		} else if (row == this.rows) {
			addLastRow();
		} else if (col == this.cols) {
			addLastCol();
		} else {
			Array2DNode<E> currNodeNode = this.head;
			int countRow = 0;
			int countCol = 0;

			while (countCol < col - 1) {
				currNodeNode = currNodeNode.right;
				countCol++;
			}

			while (countRow < row - 1) {
				currNodeNode = currNodeNode.down;
				countRow++;
			}
			currNodeNode = newNode;
		}
	}

	/**
	 * 
	 * @return Column Size
	 */
	public int colSize() {
		return cols;
	}

	/**
	 * 
	 * @return Row Size
	 */
	public int rowSize() {
		return rows;
	}

	/**
	 * Deletes the first node from the first column
	 */
	public void deleteFirstCol() {
		if (head == null) {
			System.out.println("THERE ARE NO ITEMS IN THIS ARRAY");
		}

		else {
			head = head.right;
			cols--;
		}
	}

	/**
	 * Deletes the first Node from the first row
	 */
	public void deleteFirstRow() {
		if (head == null) {
			System.out.println("THERE ARE NO ITEMS IN THIS ARRAY");
		}

		else {
			head = head.down;
			rows--;
		}
	}

	/**
	 * Deletes the last Node from the last column.
	 */
	public void deleteLastCol() {
		if (this.head == null || cols <= 0) {
			System.out.println("THERE ARE NO ITEMS IN THIS ARRAY");
		}

		else if (cols == 1) {
			this.head = null;
		}

		else {
			Array2DNode<E> currNodeNode = this.head;
			Array2DNode<E> previousNode = this.head;

			while (currNodeNode.right != null) {
				previousNode = currNodeNode;
				currNodeNode = currNodeNode.right;
			}

			previousNode.right = null;
			cols--;
		}
	}

	/**
	 * Deletes the last Node from the last row.
	 */
	public void deleteLastRow() {
		if (this.head == null || rows <= 0) {
			System.out.println("THERE ARE NO ITEMS IN THIS ARRAY");
		}

		else if (rows == 1) {
			this.head = null;
		}

		else {
			Array2DNode<E> currNodeNode = this.head;
			Array2DNode<E> previousNode = this.head;

			while (currNodeNode.down != null) {
				previousNode = currNodeNode;
				currNodeNode = currNodeNode.down;
			}

			previousNode.down = null;
			rows--;
		}
	}

}
