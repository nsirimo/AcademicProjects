package array2D;

public class Main {
	public static void main(String[] args) {
		Integer[][] intArr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };

		System.out.println("====================CONSTRUCTOR TESTING====================\n");
		Array2D<Integer> arr2D = new Array2D<>(intArr);
		//Array2D<Integer> arr2D = new Array2D<>(10, 20);
		print2D(arr2D);
		System.out.println("==================END CONSTRUCTOR TESTING==================\n");

		System.out.println("====================ADD FUNCTION TESTING====================\n");

		System.out.println("ADD FIRST COL:");
		arr2D.addFirstCol();
		print2D(arr2D);

		System.out.println("ADD FIRST ROW:");
		arr2D.addFirstRow();
		print2D(arr2D);

		System.out.println("ADD LAST COL:");
		arr2D.addLastCol();
		print2D(arr2D);

		System.out.println("ADD LAST ROW:");
		arr2D.addLastRow();
		print2D(arr2D);

		System.out.println("ADD COL:");
		arr2D.addCol(2);
		print2D(arr2D);
//
		System.out.println("ADD ROW:");
		arr2D.addRow(3);
		print2D(arr2D);
//
		System.out.println("COL BY COL PRINT");
		print2DColByCol(arr2D);

		System.out.println("==================END ADD FUNCTION TESTING==================\n");

		System.out.println("==================GET/SET FUNCTION TESTING==================\n");
		System.out.println("SET TESTING:");
		arr2D.set(3, 3, 777);
		print2D(arr2D);

		System.out.println("GET TESTING: " + arr2D.get(4, 5));

		System.out.println("================END GET/SET FUNCTION TESTING================\n");

		System.out.println("====================DEL FUNCTION TESTING====================\n");

		System.out.println("DEL FIRST COL:");
		arr2D.deleteFirstCol();
		print2D(arr2D);

		System.out.println("DEL FIRST ROW:");
		arr2D.deleteFirstRow();
		print2D(arr2D);

		System.out.println("DEL LAST COL:");
		arr2D.deleteLastCol();
		print2D(arr2D);

		System.out.println("DEL LAST ROW:");
		arr2D.deleteLastRow();
		print2D(arr2D);

		System.out.println("DEL COL:");
		arr2D.deleteCol(2);
		print2D(arr2D);

		System.out.println("DEL ROW:");
		arr2D.deleteRow(2);
		print2D(arr2D);

		System.out.println("COL BY COL PRINT");
		print2DColByCol(arr2D);

		System.out.println("==================END DEL FUNCTION TESTING==================\n");

	}

	// public static void print2D(Array2D<Integer> m) {
	// Array2DNode<Integer> rowHead = m.head;
	//
	// while (rowHead != null) {
	// Array2DNode<Integer> colHead = rowHead;
	// while(colHead != null) {
	// System.out.print(colHead.getItem() +"\t");
	// colHead = colHead.right;
	// }
	// System.out.println();
	// rowHead = rowHead.down;
	// }
	// System.out.println();
	// }

	public static void print2D(Array2D<Integer> m) {
		for (int i = 0; i < m.rowSize() - 1; i++) {
			Array2DNode<Integer> rowHead = m.getRow(i);

			for (int j = 0; j < m.colSize() - 1; j++) {
				System.out.print(rowHead.getItem() + "\t");
				rowHead = rowHead.right;
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void print2DColByCol(Array2D<Integer> m) {
		for (int i = 0; i < m.colSize() - 1; i++) {
			System.out.print("col " + i + ":\t");
			Array2DNode<Integer> col = m.getCol(i);

			for (int j = 0; j < m.rowSize() - 1; j++) {
				System.out.print(col.getItem() + "\t");
				col = col.down;
			}
			System.out.println();
		}
		System.out.println();

	}

	// public static void print2DColByCol(Array2D<Integer> m) {
	// Array2DNode<Integer> colHead = m.head;
	//
	// int i = 0;
	// while (colHead != null) {
	// Array2DNode<Integer> row = colHead;
	//
	// System.out.print("col " + i + ":\t");
	//
	// for (int j = 0 ; j < m.rowSize(); j++) {
	// System.out.print(row.getItem() + "\t");
	// row = row.down;
	// }
	// ++i;
	// System.out.println();
	// }
	// System.out.println();
	// }
}
