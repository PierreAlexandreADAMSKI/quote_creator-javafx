package app.main.controllers.widgets.list_cell;

import app.main.javafx.ChildBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * app.main.controllers.widgets.list_cell Created by Pierre-Alexandre Adamski on 24/05/16.
 */
public class AreaListCellController extends ChildBox{
	@FXML
	Label formulaLabel;
	@FXML
	Label typeLabel;
	@FXML
	Label areaLabel;
	@FXML
	Label stampLabel;

	public AreaListCellController() {
		super("AreaListCell");
	}

	public void setAreaLabel(String areaLabel) {
		this.areaLabel.setText(areaLabel);
	}

	public void setFormulaLabel(String formulaLabel) {
		this.formulaLabel.setText(formulaLabel);
	}

	public void setStampLabel(String stampLabel) {
		this.stampLabel.setText(stampLabel);
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel.setText(typeLabel);
	}
}
