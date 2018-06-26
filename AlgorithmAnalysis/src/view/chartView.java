package view;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import sorting.sorting;
import test.testModel;

public class chartView {

	public void view(Stage primaryStage){
		int arrLength = 500;
		testModel testModel = new testModel(arrLength);
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Size");
		yAxis.setLabel("Milliseconds");
		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

		lineChart.setTitle("Algorithm Analysis");

		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.setName("Bubble Sort");
		series1.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series1.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series1.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series1.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series1.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series1.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));
		
		arrLength = 500;
		testModel = new testModel(arrLength);
		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		series2.setName("Insertion Sort");
		series2.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series2.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series2.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series2.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series2.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series2.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));
		
		arrLength = 500;
		XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
		series3.setName("Selection Sort");
		series3.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series3.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series3.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series3.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series3.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series3.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));
		
		arrLength = 500;
		XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
		series4.setName("Merge Sort");
		series4.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series4.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series4.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series4.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series4.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series4.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));
		
		arrLength = 500;
		XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();
		series5.setName("Heap Sort");
		series5.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series5.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series5.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series5.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series5.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series5.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));

		arrLength = 500;
		XYChart.Series<String, Number> series6 = new XYChart.Series<String, Number>();
		series6.setName("Quick Sort");
		series6.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series6.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series6.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series6.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series6.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series6.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));
		
		arrLength = 500;
		XYChart.Series<String, Number> series7 = new XYChart.Series<String, Number>();
		series7.setName("Counting Sort");
		series7.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series7.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series7.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series7.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series7.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series7.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));
		
		arrLength = 500;
		XYChart.Series<String, Number> series8 = new XYChart.Series<String, Number>();
		series8.setName("Radix Sort");
		series8.getData().add(new XYChart.Data<String, Number>("50000", testModel.bubbleSortTest()));
		arrLength = 1000;
		testModel = new testModel(arrLength);
		series8.getData().add(new XYChart.Data<String, Number>("100000", testModel.bubbleSortTest()));
		arrLength = 1500;
		testModel = new testModel(arrLength);
		series8.getData().add(new XYChart.Data<String, Number>("150000", testModel.bubbleSortTest()));
		arrLength = 2000;
		testModel = new testModel(arrLength);
		series8.getData().add(new XYChart.Data<String, Number>("200000", testModel.bubbleSortTest()));
		arrLength = 2500;
		testModel = new testModel(arrLength);
		series8.getData().add(new XYChart.Data<String, Number>("250000", testModel.bubbleSortTest()));
		arrLength = 3000;
		testModel = new testModel(arrLength);
		series8.getData().add(new XYChart.Data<String, Number>("300000", testModel.bubbleSortTest()));


		
		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
