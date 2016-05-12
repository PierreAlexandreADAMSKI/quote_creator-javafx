package app.view.main.widgets.table_box;

import app.view.main.MainController;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.form_box.quantities.MeterSquareQuantityBox;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.text.Text;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class QuantityTableCell extends TableCell<RowAdapter, Float> {

	final Button quantityButton = new Button("Quantité");
	public String text = "0.0";

	QuantityTableCell(final MainController controller) {
		/* no need for a button
		this.setOnMouseClicked(mouseEvent -> {
			final MeterSquareQuantityBox quantityFormBox = new MeterSquareQuantityBox(controller);
			controller.formScrollPane.setContent(null);
			controller.formScrollPane.setContent(quantityFormBox);
			quantityFormBox.init();
		});
		*/
		quantityButton.setId("table_box_quatity_button");
		quantityButton.setOnMouseClicked(mouseEvent -> {
			controller.setCurrentClickedCell((QuantityTableCell) ((Button) mouseEvent.getSource()).getParent());
			controller.setCurrentClickedRowIndex(((TableRow) controller.getCurrentClickedCell().getParent()).getIndex());
			switch (controller.currentRowAdapter().getUnit()) {
				case "m": {
					final MeterSquareQuantityBox meterSquareQuantityFormBox = new MeterSquareQuantityBox(controller);
					controller.formScrollPane.setContent(meterSquareQuantityFormBox);
				}
				break;
			}
		});
	}

	/**
	 * places an add button in the row only if the row is not empty.
	 *
	 * TODO bug : when adding new row all precedent quantityCol get emptied
	 */

	@Override
	protected void updateItem(Float item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			if (item <= 0.f) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(quantityButton);
			} else {
				setContentDisplay(ContentDisplay.TEXT_ONLY);
				this.setText(text); //not far from the solution WORK ON IT YOU FUCKER!!
			}
		} else {
			setGraphic(null);
		}
	}


}