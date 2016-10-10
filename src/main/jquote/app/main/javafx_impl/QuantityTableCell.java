package app.main.javafx_impl;

import app.main.adapters.TableRowAdapter;
import app.main.controllers.widgets.form_box.MeterCubeQuantityFormBoxController;
import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.controllers.MainStageController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

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

		String unit = controller.getClickedTableRowAdapter().getUnit();
		switch (unit) {
			case "U":
			case "m":
			case "ENS": {
				casText.setPromptText("...");
				casText.setOnKeyReleased(event -> {
					String text = casText.getText();
					if (event.getCode() == KeyCode.ENTER && !Float.valueOf(text).isNaN()) {
						final Float quantity = Float.valueOf(text);
						controller.getClickedTableRowAdapter().setQuantity(quantity);
						final Float unitTvaPrice = controller.getClickedTableRowAdapter().getTvaPriceWrite();
						controller.getClickedTableRowAdapter().setPriceGen(unitTvaPrice * quantity);
						casText.setText("");
					}
				});
			}
			break;
			case "m2":
			case "m3": {
				handleFormByName(unit);
			}
		}
	};

	private void handleFormByName(String name){
		caseButton.setId("table_box_quatity_button");
		final FormBox formBox;
		if (name.equals("m2"))
			formBox = new MeterSquareQuantityFormBoxController(controller);
		else
			formBox = new MeterCubeQuantityFormBoxController(controller);
		controller.formScrollPane.setContent(null);
		controller.formScrollPane.setContent(formBox);
	}

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
		if (item == 0.f) {
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			switch (this.controller.tableViewBoxController.tableView.getItems().get(getIndex()).getUnit()) {
				case "U":
				case "m":
				case "ENS": {
					casText.setOnMouseClicked(mouseEventHandler);
					setGraphic(casText);
				}
				break;
				case "m2":
				case "m3": {
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