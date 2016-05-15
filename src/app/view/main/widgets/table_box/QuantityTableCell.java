package app.view.main.widgets.table_box;

import app.view.main.MainController;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.form_box.quantities.MeterSquareQuantityBox;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.regex.Pattern;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class QuantityTableCell extends TableCell<RowAdapter, Float> {

	protected MainController controller;
	protected Region quantityNode = new Region();

	protected QuantityTableCell(final MainController controller) {
		this.controller = controller;

		/*quantityButton.setOnMouseClicked(mouseEvent -> {
			controller.setClickedCell((QuantityTableCell) ((Button) mouseEvent.getSource()).getParent());
			controller.setClickedRowIndex(((TableRow) controller.getClickedCell().getParent()).getIndex());
			switch (controller.getClickedRowAdapter().getUnit()) {
				case "m": {
					final MeterSquareQuantityBox meterSquareQuantityFormBox = new MeterSquareQuantityBox(controller);
					controller.formScrollPane.setContent(meterSquareQuantityFormBox);
				}
				break;
			}
		});*/
	}

	private EventHandler<MouseEvent> mouseEventHandler = mouseEvent -> {
		controller.setClickedCell((QuantityTableCell) ((Node) mouseEvent.getSource()).getParent());
		controller.setClickedRowIndex(((TableRow) controller.getClickedCell().getParent()).getIndex());
		controller.setClickedRowAdapter(controller.getClickedCell().getTableView().getItems().get(controller.getClickedRowIndex()));

		switch (controller.getCurrentRowAdapter().getUnit()) {
			case "m": {
				final Pattern wholeNumberPattern = Pattern.compile("[0-9]*[.]?[0-9]*");

				((TextField) quantityNode).setPromptText("...");

				((TextField) quantityNode).textProperty().addListener((observable, oldValue, newValue) -> {
					if (!wholeNumberPattern.matcher(newValue).matches())
						((TextField) quantityNode).setText(oldValue);
				});
				quantityNode.setOnKeyReleased(event -> {
					final String text = ((TextField) quantityNode).getText();
					if (event.getCode() == KeyCode.ENTER && !Float.valueOf(text).isNaN()) {
						final Float quantity = Float.valueOf(text);
						controller.getClickedRowAdapter().setQuantity(quantity);
						((TextField) quantityNode).setText("");
					}
				});
			}
			break;
			case "m2": {
				quantityNode.setId("table_box_quatity_button");
				final MeterSquareQuantityBox meterSquareQuantityFormBox = new MeterSquareQuantityBox(controller);
				controller.formScrollPane.setContent(meterSquareQuantityFormBox);
			}
			break;
			default:
				setGraphic(quantityNode);
				break;
		}
	};

	/**
	 * places an add button in the row only if the row is not empty.
	 * <p>
	 * TODO bug : when adding new row all precedent quantityCol get emptied
	 */

	@Override
	protected void updateItem(Float item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			if (item <= 0.f) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				switch (this.controller.getCurrentRowAdapter().getUnit()) {
					case "m":
						quantityNode = new TextField();
						break;
					case "m2":
						quantityNode = new Button("Quantité");
						break;
				}
				quantityNode.setOnMouseClicked(mouseEventHandler);
				setGraphic(quantityNode);
			} else {
				setContentDisplay(ContentDisplay.TEXT_ONLY);
				this.setText(String.valueOf(item)); //not far from the solution WORK ON IT YOU FUCKER!!
			}
		} else {
			setGraphic(null);
		}
	}


}