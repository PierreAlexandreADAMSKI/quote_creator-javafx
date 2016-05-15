package app.view.main.events;

import app.view.main.MainController;
import app.view.main.widgets.form_box.quantities.MeterSquareQuantityBox;
import app.view.main.widgets.table_box.QuantityTableCell;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.regex.Pattern;

/**
 * app.view.main.events Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public class QuantityRegionMouseEvent extends QuantityTableCell implements EventHandler<KeyEvent> {
	QuantityRegionMouseEvent(MainController controller) {
		super(controller);
	}

	@Override
	public void handle(KeyEvent mouseEvent) {
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
					final Float quantity = Float.valueOf(((TextField) quantityNode).getText());
					if (event.getCode() == KeyCode.ENTER && !quantity.isNaN())
						controller.getClickedRowAdapter().setQuantity(quantity);
					((TextField) quantityNode).setText("");
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
	}
}
