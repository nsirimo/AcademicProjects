package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tree.BinarySearchTree;

public class BinarySearchTreeAnimation extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		BorderPane pane = new BorderPane();
		BTView view = new BTView(tree);
		pane.setCenter(view);
		
		TextField tfKey = new TextField();
		tfKey.setPrefColumnCount(3);
		tfKey.setAlignment(Pos.BASELINE_RIGHT);
		Button btInsert = new Button("Insert");
		Button btDelete = new Button("Delete");
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsert, btDelete);
		hBox.setAlignment(Pos.CENTER);;
		pane.setBottom(hBox);
		
		btInsert.setOnAction(EventHandler -> {
			int key = Integer.parseInt(tfKey.getText());
			if(tree.find(key)){
				view.displayTree();
				view.setStatus(key + " is already in the tree");
			}
			else{
				tree.insert(key);
				view.displayTree();
				view.setStatus(key + " is inserted in the tree");;
			}
		});
		
		btDelete.setOnAction(EventHandler -> {
			int key = Integer.parseInt(tfKey.getText());
			if(!tree.find(key)){
				view.displayTree();
				view.setStatus(key + " is not in the tree");
			}
			else{
				tree.delete(key);
				view.displayTree();
				view.setStatus(key + " is deleted from the tree");;
			}
		});
		
		Scene scene = new Scene(pane, 450, 250);
		primaryStage.setTitle("BinarySeachTreeAnimation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}
