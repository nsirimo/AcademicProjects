
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.sun.javafx.image.IntPixelGetter;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PhotoshopView {
	PPMImageV2 ppmImageV2;
	File selectedFile;

	/**
	 * Constructor that creates the view.
	 * @param primaryStage
	 */
	public PhotoshopView(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 600, 400);
		GridPane gPane = new GridPane();
		setUpBtns(primaryStage, root, gPane);

		root.setBottom(gPane);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Sets up all the buttons and handles their mouse clicked events to the appropriate methods. 
	 * @param primaryStage stage
	 * @param root borderpane
	 * @param gPane gridpane
	 */
	public void setUpBtns(Stage primaryStage, BorderPane root, GridPane gPane) {
		Button selectImage = new Button("Select Image");
		selectImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				try {
					importPhoto(primaryStage, root);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		Button greyScale = new Button("Grey Scale");
		greyScale.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				ppmImageV2.greyscale();
			}
		});

		Button sepiaTone = new Button("Sepia Tone");
		sepiaTone.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				ppmImageV2.sepia();
			}
		});

		Button negativeButton = new Button("Negative");
		negativeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				ppmImageV2.negative();
			}
		});
		
		Button hideMessageBtn = new Button("Encrypt Message");
		hideMessageBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				String hiddenMessage = userInputMessage();
				ppmImageV2.hideMessage(hiddenMessage);
			}
		});
		
		Button writeToImageBtn = new Button("Save Changes");
		writeToImageBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				ppmImageV2.writeImage(selectedFile.toString());
				savedMessagePopUp();
			}
		});
		
		Button recoverMessageBtn = new Button("Recover Message");
		recoverMessageBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				Alert messageConfirmation = new Alert(AlertType.INFORMATION);
			    messageConfirmation.setContentText("Hidden Message: " + ppmImageV2.recoverMessage());
			    messageConfirmation.show();
			}
		});

		gPane.add(selectImage, 0, 0);
		gPane.add(greyScale, 1, 0);
		gPane.add(sepiaTone, 2, 0);
		gPane.add(negativeButton, 3, 0);
		gPane.add(hideMessageBtn, 4, 0);
		gPane.add(writeToImageBtn, 5, 0);
		gPane.add(recoverMessageBtn, 6, 0);
	}
	
	/**
	 * Takes in the user input and stores it. 
	 * @return
	 */
	public static String userInputMessage(){
	    TextInputDialog message = new TextInputDialog("Message Input");
	    message.setTitle("Message");
	    message.setHeaderText(null);
	    message.setContentText("Message Input");
	    ButtonType enterBtn = new ButtonType("Enter", ButtonData.OK_DONE);
	    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	    message.getDialogPane().getButtonTypes().setAll(enterBtn, cancelBtn);
	    Optional<String> result = message.showAndWait();
	    if(result.isPresent()){
	        return result.get();
	    }else{
	        return null;
	    }
	}
	
	/**
	 * Pop up to confirm that the message was saved. 
	 */
	public static void savedMessagePopUp(){
	    Alert messageConfirmation = new Alert(AlertType.CONFIRMATION);
	    messageConfirmation.setContentText("Saved To Image");
	    messageConfirmation.show();
	}

	/**
	 * Displays the photo on the borderpane
	 * @param bPane borderpane
	 * @param image Image
	 * @param primaryStage Stage
	 */
	public void displayPhoto(BorderPane bPane, Image image, Stage primaryStage) {
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(250);
		imageView.setFitWidth(250);
		imageView.setPreserveRatio(true);

		bPane.setCenter(imageView);
	}

	/**
	 * Takes the file path and loads it into an image, then creates the PPMImageV2 object. Then displays the photo on the main stage. 
	 * @param primaryStage Stage
	 * @param bPane Border Pane
	 * @throws IOException
	 */
	public void importPhoto(Stage primaryStage, BorderPane bPane) throws IOException {
		selectedFile = fileSelection(primaryStage);
		Image image = SwingFXUtils.toFXImage(ImageIO.read(selectedFile), null);
		ppmImageV2 = new PPMImageV2(selectedFile, image);
		
		//ppmImageV2.hideMessage("Hi Keenan, please give me a 5 on this project!");
		displayPhoto(bPane, image, primaryStage);
	}

	/**
	 * File selection method.
	 * @param primaryStage
	 * @return File path of the selected file
	 */
	public File fileSelection(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PPM Images", "*.ppm"));
		File selectedFile = fileChooser.showOpenDialog(primaryStage);

		return selectedFile;
	}

}
