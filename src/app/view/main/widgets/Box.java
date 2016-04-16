package app.view.main.widgets;

import app.view.main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class Box extends VBox {

	protected MainController mController;

	public Box(String fxmlName, MainController controller) {
		this.mController = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName+".fxml"));
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
