package app.view.main.widgets.table_box;

import app.view.main.MainController;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.form_box.quantities.QuantityBox;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javax.swing.text.View;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class QuantityTableCell extends TableCell<RowAdapter, Float> {

	final Button quantityButton = new Button("Quantité");

	QuantityTableCell(final MainController controller) {
		/* no need for a button
		this.setOnMouseClicked(mouseEvent -> {
			final QuantityBox quantityFormBox = new QuantityBox(controller);
			controller.formScrollPane.setContent(null);
			controller.formScrollPane.setContent(quantityFormBox);
			quantityFormBox.init();
		});
		*/
		quantityButton.setId("table_box_quatity_button");
		quantityButton.setOnMouseClicked(mouseEvent -> {
			final QuantityBox quantityFormBox = new QuantityBox(controller);
			controller.formScrollPane.setContent(quantityFormBox);
			quantityFormBox.init();
			MainController.currentClickedCell = (QuantityTableCell) ((Button) mouseEvent.getSource()).getParent();
		});
	}

	/**
	 * places an add button in the row only if the row is not empty.
	 */
	@Override
	protected void updateItem(Float item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			if (item == -1f) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(quantityButton);
			}else {
				setContentDisplay(ContentDisplay.TEXT_ONLY);
				setGraphic(new Text(item.toString()));
			}
		} else {
			setGraphic(null);
		}
	}


}