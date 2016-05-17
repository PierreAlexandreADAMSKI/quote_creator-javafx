package jquote.app.main.javafx;

import jquote.app.App;
import jquote.app.main.controllers.MainStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class Box extends VBox {

	protected MainStageController mController;

	public Box(String prefix, String fxmlName, MainStageController controller) {
		this.mController = controller;
		String file = "main/views/" + prefix + fxmlName + ".fxml";
		URL fileURL = App.class.getResource(file); //this.getClass().getResource(file).;
		FXMLLoader loader = new FXMLLoader(fileURL);
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initForForm(Button addButton){
		this.setOnMouseMoved(event -> {
			if (this.getChildren().stream().filter(textField ->
					textField instanceof TextField).allMatch(predicate ->
					!((TextField) predicate).getText().isEmpty() && !((TextField) predicate).getText().equals(""))) {
				addButton.setDisable(false);
			}
		});
	}
}
