package app.main.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static app.main.services.ReflectionService.getResourceFromBox;
/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public abstract class Box extends VBox {
	public Box(String fxmlName) {
		FXMLLoader loader = new FXMLLoader(getResourceFromBox(fxmlName, this));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
			comportment();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected abstract void comportment();
}
