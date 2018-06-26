package view;

import java.awt.Color;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import tree.BSTNode;
import tree.BinarySearchTree;

public class BTView extends Pane {
	private BinarySearchTree<Integer> tree = new BinarySearchTree<>();
	private double radius = 15;
	private double vGap = 50;
	
	BTView(BinarySearchTree<Integer> tree){
		this.tree = tree;
		//setStatus
	}
	
	public void setStatus(String message){
		getChildren().add(new Text(20, 20, message));
	}
	
	public void displayTree(){
		this.getChildren().clear();
		if(tree.root != null){
			displayTree(tree.root, getWidth() / 2, this.vGap, getWidth() / 4);
		}
	}
	
	private void displayTree(BSTNode<Integer> node, double x, double y, double hGap){
		if(node.left != null){
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			displayTree(node.left, x - hGap, y + vGap, hGap / 2);
		}
		
		if(node.right != null){
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			displayTree(node.right, x + hGap, y + vGap, hGap / 2);
		}
		
		Circle circle = new Circle(x, y, radius);
		circle.setFill(Paint.valueOf("WHITE"));
		circle.setStroke(Paint.valueOf("BLACK"));
		getChildren().addAll(circle, new Text(x - 4, y + 4, node.getData() + ""));
		
	}
}
