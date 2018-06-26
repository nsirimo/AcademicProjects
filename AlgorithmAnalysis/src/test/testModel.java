package test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.PUBLIC_MEMBER;

import sorting.sorting;

public class testModel {
	static sorting<Integer> sort = null;
	static ArrayList<Integer> testAl1;
	static ArrayList<Integer> testAl2;
	static ArrayList<Integer> testAl3;
	static ArrayList<Integer> testAl4;
	static ArrayList<Integer> testAl5;
	static ArrayList<Integer> testAl6;
	static ArrayList<Integer> testAl7;
	static ArrayList<Integer> testAl8;
	
	public testModel(int testSize){
		sort = null;
		testAl1 = new ArrayList<Integer>();
		createTestData(testAl1, testSize);
		testAl2 = new ArrayList<Integer>(testAl1);
		testAl3 = new ArrayList<Integer>(testAl1);
		testAl4 = new ArrayList<Integer>(testAl1);
		testAl5 = new ArrayList<Integer>(testAl1);
		testAl6 = new ArrayList<Integer>(testAl1);
		testAl7 = new ArrayList<Integer>(testAl1);
		testAl8 = new ArrayList<Integer>(testAl1);
	}
	
	public long bubbleSortTest() {
		Instant start = Instant.now();
		sort.bubbleSort(testAl1);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long selectionSortTest() {
		Instant start = Instant.now();
		sort.selectionSort(testAl2);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long insertionSortTest() {
		Instant start = Instant.now();
		sort.insertionSort(testAl3);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long heapSortTest() {
		Instant start = Instant.now();
		sort.heapSort(testAl4);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long mergeSortTest() {
		Instant start = Instant.now();
		sort.mergeSort(testAl5);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long quickSortTest() {
		Instant start = Instant.now();
		sort.quickSort(testAl6);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long collectionSortTest() {
		Instant start = Instant.now();
		int[] testArr = sort.countingSort(testAl7);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	public long radixSortTest() {
		Instant start = Instant.now();
		int[] testArr1 = sort.convertIntegers(testAl8);
		sort.radixSort(testArr1);
		Instant end = Instant.now();
		return (Duration.between(start, end).toNanos());
	}

	/**
	 * Creates a random integer number between 0-999 and then adds it to the arrayList of integers.
	 * @param arrayList
	 * @param sizeTestData
	 */
	public static void createTestData(ArrayList<Integer> arrayList, int sizeTestData) {
		for (int testDataIter = 0; testDataIter < sizeTestData; testDataIter++) {
			Random ranNumber = new Random();
			arrayList.add(ranNumber.nextInt(999));
		}
	}

}
