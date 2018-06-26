package test;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import tree.BSTNode;
import tree.BSTUnitTester;
import tree.BinarySearchTree;
import view.BinarySearchTreeAnimation;


public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		BinarySearchTreeAnimation bsta = new BinarySearchTreeAnimation();
		bsta.start(primaryStage);
		
	}
	
	public static void main(String[] args) {
		//launch(args);
		BSTUnitTester bstUnitTester = new BSTUnitTester(true);
		
		bstUnitTester.runUnitTests();
		
	}

}
