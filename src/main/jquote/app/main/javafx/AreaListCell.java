package app.main.javafx;

import app.main.adapters.*;
import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.controllers.widgets.list_cell.AreaListCellController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

/**
 * app.main.javafx Created by Pierre-Alexandre Adamski on 22/05/16.
 */
public class AreaListCell extends ListCell<AreaListCellAdapter> {

	AreaListCellController cellController;

	public AreaListCell() {
		initAreaNode();
	}

	private void initAreaNode() {
		cellController = new AreaListCellController();
	}


	/*UPGRADEME
	private EventHandler<MouseEvent> mouseEventHandler = mouseEvent -> {
		if (mouseEvent.getClickCount() >= 2) {
			controller.setDoubleClickedCell((AreaListCell) ((Node) mouseEvent.getSource()).getParent());
			controller.setDoubleClickedRowIndex(((TableRow) controller.getDoubleClickedCell().getParent()).getIndex());
			doubleClickedAdapter = controller.getDoubleClickedCell().getListView().getItems().get(controller.getDoubleClickedRowIndex());
		}
	};*/

	@Override
	protected void updateItem(AreaListCellAdapter item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			cellController.setStampLabel(item.getStamp());
			cellController.setTypeLabel(item.getType());
			cellController.setAreaLabel(String.valueOf(item.getArea()));
			cellController.setFormulaLabel(item.getFormula());
			//areaNode.setOnMouseClicked(mouseEventHandler);
			if (item.getArea() < 0.f)
				cellController.setStyle("-fx-background-color: rgba(153, 50, 44, 0.86)");
			else
				cellController.setStyle("-fx-background-color: rgba(42, 147, 39, 0.79)");

			setGraphic(cellController);

		} else {
			setGraphic(null);
		}
	}
}
