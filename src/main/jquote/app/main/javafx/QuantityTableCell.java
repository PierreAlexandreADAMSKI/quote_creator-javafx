package app.main.javafx;

import app.main.adapters.TableRowAdapter;
import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.controllers.MainStageController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class QuantityTableCell extends TableCell<TableRowAdapter, Float> {

	private MainStageController controller;
	private FloatTextField casText = new FloatTextField();
	private Button caseButton = new Button("Quantité");


	public QuantityTableCell(final MainStageController controller) {
		this.controller = controller;
	}

	private EventHandler<MouseEvent> mouseEventHandler = mouseEvent -> {
		controller.setClickedCell((QuantityTableCell) ((Node) mouseEvent.getSource()).getParent());
		controller.setClickedRowIndex(((TableRow) controller.getClickedCell().getParent()).getIndex());
		controller.setClickedTableRowAdapter(controller.getClickedCell().getTableView().getItems().get(controller.getClickedRowIndex()));

		switch (controller.getCurrentTableRowAdapter().getUnit()) {
			case "m": {
				casText.setPromptText("...");
				casText.setOnKeyReleased(event -> {
					final String text = casText.getText();
					if (event.getCode() == KeyCode.ENTER && !Float.valueOf(text).isNaN()) {
						final Float quantity = Float.valueOf(text);
						controller.getClickedTableRowAdapter().setQuantity(quantity);
						casText.setText("");
					}
				});
			}
			break;
			case "m2": {
				caseButton.setId("table_box_quatity_button");
				final MeterSquareQuantityFormBoxController meterSquareQuantityFormBox = new MeterSquareQuantityFormBoxController(controller);
				controller.formScrollPane.setContent(meterSquareQuantityFormBox);
			}
			break;
		}
	};

	/**
	 * places an add button in the row only if the row is not empty.
	 * <p>
	 */

	@Override
	protected void updateItem(Float item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			addContent(item);
		} else {
			setText(null);
			setGraphic(null);
		}
	}

	private void addContent(Float item) {
		if (item <= 0.f) {
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			switch (this.controller.getCurrentTableRowAdapter().getUnit()) {
				case "m": {
					casText.setOnMouseClicked(mouseEventHandler);
					setGraphic(casText);
				}
				break;
				case "m2": {
					caseButton.setOnMouseClicked(mouseEventHandler);
					setGraphic(caseButton);
				}
				break;
			}
		} else {
			setContentDisplay(ContentDisplay.TEXT_ONLY);
			this.setText(String.valueOf(item)); //not far from the solution WORK ON IT YOU FUCKER!!
		}
	}

}