package app.view.main.widgets.table_box;

import app.view.main.MainController;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.form_box.quantities.QuantityBox;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class QuantityTableCell extends TableCell<RowAdapter, Boolean> {

	final Button addButton = new Button("Add");
	// pads and centers the add button in the cell.
	final StackPane paddedButton = new StackPane();
	// records the y pos of the last button press so that the add person dialog can be shown next to the cell.
	final DoubleProperty buttonY = new SimpleDoubleProperty();

	QuantityTableCell(final MainController controller) {

		paddedButton.setPadding(new Insets(3));
		paddedButton.getChildren().add(addButton);
		addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				buttonY.set(mouseEvent.getScreenY());
			}
		});
		addButton.setOnAction(actionEvent -> {
			final QuantityBox quantityFormBox = new QuantityBox(controller);
			controller.formScrollPane.setContent(null);
			controller.formScrollPane.setContent(quantityFormBox);
			quantityFormBox.init();
		});
	}


	/**
	 * places an add button in the row only if the row is not empty.
	 */
	@Override
	protected void updateItem(Boolean item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			setGraphic(paddedButton);
		} else {
			setGraphic(null);
		}
	}

}