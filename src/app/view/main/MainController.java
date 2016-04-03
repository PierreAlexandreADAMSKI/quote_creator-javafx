package app.view.main;

import app.view.main.adapters.RowAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * java.view Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public class MainController {
	@FXML
	private TableView<RowAdapter> table;
	private TableColumn<RowAdapter, Boolean> selCol = new TableColumn<>("");
	private TableColumn<RowAdapter, String> productWriteCol = new TableColumn<>("Produit");
	private TableColumn<RowAdapter, String> sellerSelCol = new TableColumn<>("revendeur");
	private TableColumn<RowAdapter, Float> sizeSelCol = new TableColumn<>("taille (m*m)");
	private TableColumn<RowAdapter, Float> priceWriteCol = new TableColumn<>("prix HT (€/m2)");
	private TableColumn<RowAdapter, Float> tvaCol = new TableColumn<>("TVA");
	private TableColumn<RowAdapter, Float> priceGenCol = new TableColumn<>("prix TTC (€/m2)");

	protected Double selColSize;


	@FXML
	private void initialize() {
		table.setItems(FXCollections.observableArrayList(new RowAdapter("aa", "bb", 20.2, 100, 20)));
		productWriteCol.setCellValueFactory(new PropertyValueFactory<>("product"));
		productWriteCol.setOnEditCommit(edit -> table.getItems().get(edit.getTablePosition().getRow()).setProduct(edit.getNewValue()));
		sellerSelCol.setCellValueFactory(new PropertyValueFactory<>("seller"));
		sizeSelCol.setCellValueFactory(new PropertyValueFactory<>("size"));
		priceWriteCol.setCellValueFactory(new PropertyValueFactory<>("priceWrite"));
		tvaCol.setCellValueFactory(new PropertyValueFactory<>("tva"));
		priceGenCol.setCellValueFactory(new PropertyValueFactory<>("priceGen"));
		selCol.setCellValueFactory(new PropertyValueFactory<>("selCol"));

		/* add a CheckBoxCell */
		final CheckBox checkBox = new CheckBox();
		selCol.setCellFactory(param -> new CheckBoxCell());
		selCol.setPrefWidth(30);
		selCol.setResizable(false);
		//table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().setAll(selCol, productWriteCol, sellerSelCol, sizeSelCol, priceWriteCol, tvaCol, priceGenCol);

		/*common params for all the columns */
		for (TableColumn column : table.getColumns()) {
			column.setSortable(false);
		}

	}

	/**
	 * customized cell
	 **/
	private class CheckBoxCell extends TableCell<RowAdapter, Boolean> {
		final CheckBox checkBox = new CheckBox();

		public CheckBoxCell() {
			/* checkbox action on mouse click event */
			checkBox.setAlignment(Pos.CENTER);
			checkBox.setOnAction(check -> table.getItems().get(getTableRow().getIndex()).setSel(checkBox.isSelected()));
			if (this.isSelected()) {
				/* action when pressing "clear" key for the selected cell*/
				this.setOnKeyPressed(key -> {
					if (key.getCode().equals(KeyCode.CLEAR)) {
						table.getItems().remove(getTableRow().getIndex());
					}
				});
			}
			setPrefSize(checkBox.getPrefWidth(), checkBox.getPrefHeight());
			setGraphic(checkBox);
		}

		/**
		 * places an add button in the row only if the row is not empty.
		 */
		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			setText(null);
			if (!empty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			} else {
				setGraphic(null);
			}
		}
	}

	@FXML
	public void onAddSel() {

	}

	/**
	 * editable cell example
	 *
	 *
	 lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
	@Override public void handle(CellEditEvent<Person, String> t) {
	((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
	}
	});
	 *
	 */


}
