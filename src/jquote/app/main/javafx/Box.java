package app.main.javafx;

import app.main.controllers.MainStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected abstract void comportment(MainStageController controller);
}
