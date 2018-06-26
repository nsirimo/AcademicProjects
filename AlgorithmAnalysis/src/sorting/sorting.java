package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class sorting<E extends Comparable<E>> {

	private sorting() {

	}

	public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> arrayList) {
		for (int columnIter = 1; columnIter < arrayList.size(); columnIter++) {
			boolean swapped = false;
			for (int rowIter = 0; rowIter < arrayList.size() - 1; rowIter++) {
				int compareToValue = arrayList.get(rowIter).compareTo(arrayList.get(rowIter + 1));

				if (compareToValue > 0) {
					E temp = arrayList.get(rowIter);
					arrayList.set(rowIter, arrayList.get(rowIter + 1));
					arrayList.set(rowIter + 1, temp);
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}

	public static <E extends Comparable<E>> void selectionSort(ArrayList<E> arrayList) {
		for (int columnIter = 0; columnIter < arrayList.size(); columnIter++) {
			int minValue = columnIter;
			for (int rowIter = columnIter + 1; rowIter < arrayList.size(); rowIter++) {
				int compareToValue = arrayList.get(rowIter).compareTo(arrayList.get(minValue));

				if (compareToValue < 0) {
					minValue = rowIter;
				}
			}
			E temp = arrayList.get(minValue);
			arrayList.set(minValue, arrayList.get(columnIter));
			arrayList.set(columnIter, temp);
		}
	}

	public static <E extends Comparable<E>> void insertionSort(ArrayList<E> arrayList) {
		E temp;
		for (int columnIter = 1; columnIter < arrayList.size(); columnIter++) {
			for (int rowIter = columnIter; rowIter > 0; rowIter--) {
				int compareToValue = arrayList.get(rowIter).compareTo(arrayList.get(rowIter - 1));
				if (compareToValue < 0) {
					temp = arrayList.get(rowIter);
					arrayList.set(rowIter, arrayList.get(rowIter - 1));
					arrayList.set(rowIter - 1, temp);
				}
			}
		}
	}

	public static <E extends Comparable<E>> void heapSort(ArrayList<E> arrayList) {
		PriorityQueue<E> priQue = new PriorityQueue<E>();

		for (int addIter = 0; addIter < arrayList.size(); addIter++) {
			priQue.add(arrayList.get(addIter));
		}

		for (int deleteIter = 0; deleteIter < arrayList.size(); deleteIter++) {
			arrayList.set(deleteIter, priQue.remove());
		}
	}

	public static <E extends Comparable<E>> ArrayList<E> mergeSort(ArrayList<E> arrayList) {
		if (arrayList.size() > 1) {
			int midListIndex = (arrayList.size()) / 2;

			ArrayList<E> leftArrList = new ArrayList<>();
			ArrayList<E> rightArrList = new ArrayList<>();

			for (int leftArrIter = 0; leftArrIter < midListIndex; leftArrIter++) {
				leftArrList.add(arrayList.get(leftArrIter));
			}

			for (int rightArrIter = midListIndex; rightArrIter < arrayList.size(); rightArrIter++) {
				rightArrList.add(arrayList.get(rightArrIter));
			}

			leftArrList = mergeSort(leftArrList);
			rightArrList = mergeSort(rightArrList);

			merge(leftArrList, rightArrList, arrayList);
		}

		return arrayList;
	}

	private static <E extends Comparable<E>> void merge(ArrayList<E> left, ArrayList<E> right, ArrayList<E> result) {
		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = 0;

		while (leftIndex < left.size() && rightIndex < right.size()) {
			int compareToValue = (left.get(leftIndex).compareTo(right.get(rightIndex)));

			if ((compareToValue < 0)) {
				result.set(resultIndex, left.get(leftIndex));
				leftIndex++;
			} else {
				result.set(resultIndex, right.get(rightIndex));
				rightIndex++;
			}
			resultIndex++;
		}

		ArrayList<E> leftOver;
		int leftOverIndex;
		if (leftIndex >= left.size()) {
			leftOver = right;
			leftOverIndex = rightIndex;
		} else {
			leftOver = left;
			leftOverIndex = leftIndex;
		}

		for (int i = leftOverIndex; i < leftOver.size(); i++) {
			result.set(resultIndex, leftOver.get(i));
			resultIndex++;
		}
	}

	// I implemented this method instead of the one you created cause it seemed
	// simpler to read.
	// I learned this while brainstorming this project with my mentor.
	public static <E extends Comparable<E>> void quickSort(ArrayList<E> arrayList) {
		E temp;
		for (int i = 1; i < arrayList.size(); i++) {
			for (int j = i; (j > 0) && (arrayList.get(j).compareTo(arrayList.get(j - 1)) < 0); j--) {
				temp = arrayList.get(j);
				arrayList.set(j, arrayList.get(j - 1));
				arrayList.set(j - 1, temp);
			}
		}
	}

	private static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int low, int high) {
		if (low < high) {
			int p = partition(list, low, high);
			quickSort(list, low, p - 1);
			quickSort(list, p + 1, high);
		}
	}

	private static <E extends Comparable<E>> int partition(ArrayList<E> list, int low, int high) {
		E pivot = list.get(low);
		int i = low + 1;

		while (high > i) {
			while (i <= high && list.get(i).compareTo(pivot) <= 0) {
				i++;
			}

			while (i <= high && list.get(high).compareTo(pivot) > 0) {
				high--;
			}

			if (high > low) {
				E temp = list.get(high);
				list.set(high, list.get(i));
				list.set(i, temp);
			}
		}

		while (high > low && list.get(high).compareTo(pivot) >= 0) {
			high--;
		}

		if (pivot.compareTo(list.get(high)) > 0) {
			list.set(low, list.get(high));
			list.set(high, pivot);
			return high;
		} else {
			return low;
		}
	}

	public static int[] convertIntegers(ArrayList<Integer> integers) {
		int[] ret = new int[integers.size()];
		Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}

	public static int[] countingSort(ArrayList<Integer> arr) {
		int k = Collections.max(arr);
		int[] arrInt = convertIntegers(arr);
		int[] arrInt2 = countingSort(arrInt, k);
		return arrInt2;
	}

	private static int[] countingSort(int[] arr, int k) {
		int[] results = new int[arr.length];
		int[] counts = new int[k + 1];

		for (int i = 0; i < k + 1; i++) {
			counts[i] = 0;
		}

		for (int j = 0; j < arr.length; j++) {
			counts[arr[j]]++;
		}

		for (int i = 1; i <= k; i++) {
			counts[i] += counts[i - 1];
		}

		for (int i = 0; i < arr.length; i++) {
			results[counts[arr[i]] - 1] = arr[i];
			counts[arr[i]]--;
		}

		return results;
	}

	public static long[] convertLong(ArrayList<Long> integers) {
		long[] ret = new long[integers.size()];
		Iterator<Long> iterator = integers.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}

	public static void radixSort(int[] input) {
		ArrayList<Integer>[] buckets = new ArrayList[10];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Integer>();
		}

		boolean flagger = false;
		int tmp = -1;
		int divisor = 1;
		while (!flagger) {
			flagger = true;
			for (Integer i : input) {
				tmp = i / divisor;
				buckets[tmp % 10].add(i);
				if (flagger && tmp > 0) {
					flagger = false;
				}
			}

			int tempCounter = 0;
			//Searches in that specific bucket for all the integers in it
			for (int j = 0; j < 10; j++) {
				for (Integer i : buckets[j]) {
					input[tempCounter++] = i;
				}
				buckets[j].clear();
			}

			divisor = divisor * 10;
		}
	}
}
