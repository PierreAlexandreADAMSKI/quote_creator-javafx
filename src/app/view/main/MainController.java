package app.view.main;

import app.service.SaveTableViewService;
import app.service.StageService;
import app.view.main.widgets.form_box.product.ProductBox;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.form_box.quantities.MeterSquareQuantityBox;
import app.view.main.widgets.table_box.QuantityTableCell;
import app.view.main.widgets.table_box.TableViewBox;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.jnlp.FileSaveService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * java.view Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public class MainController {
	@FXML
	public ScrollPane formScrollPane;
	@FXML
	public Button addButton;
	@FXML
	public Button delButton;
	@FXML
	public MenuButton newMenu;
	@FXML
	public ScrollPane tableScrollPane;
	public TableViewBox tableViewBox;
	@FXML
	public Button saveButton;

	private MenuItem row = new MenuItem("ligne");

	/**
	 * Current <=> JustAdded
	 */
	private RowAdapter currentRowAdapter;

	/**
	 * OnRowClicked <=> quantity clicked
	 */
	private RowAdapter clickedRowAdapter;
	private QuantityTableCell clickedCell;
	private int clickedRowIndex;


	@FXML
	private void initialize() {
		addButton.setDisable(true);
		row.setOnAction(event -> {
			final ProductBox productFormBox = new ProductBox(this);
			formScrollPane.setContent(null);
			formScrollPane.setContent(productFormBox);
			productFormBox.init();
		});
		newMenu.getItems().setAll(row);

		tableViewBox = new TableViewBox(this);
		tableScrollPane.setContent(tableViewBox);
	}

	public RowAdapter getCurrentRowAdapter() {
		return currentRowAdapter;
	}

	public void setCurrentRowAdapter(RowAdapter currentRowAdapter) {
		this.currentRowAdapter = currentRowAdapter;
	}

	public void setClickedRowAdapter(RowAdapter clickedRowAdapter) {
		this.clickedRowAdapter = clickedRowAdapter;
	}

	public RowAdapter getClickedRowAdapter() {
		return clickedRowAdapter;
	}

	public int getClickedRowIndex() {
		return clickedRowIndex;
	}

	public void setClickedRowIndex(int clickedRowIndex) {
		this.clickedRowIndex = clickedRowIndex;
	}

	public QuantityTableCell getClickedCell() {
		return clickedCell;
	}

	public void setClickedCell(QuantityTableCell clickedCell) {
		this.clickedCell = clickedCell;
	}

	private RowAdapter productAdapterForTableView() {
		final ProductBox productBox = (ProductBox) formScrollPane.getContent();
		return new RowAdapter(productBox.nameTextField.getText(),
				productBox.productTextField.getText(),
				productBox.sellerTextField.getText(),
				Float.valueOf(productBox.sizeTextField.getText()),
				productBox.unitMenuButton.getText(),
				Float.valueOf(productBox.tvaTextField.getText()),
				Float.valueOf(productBox.priceWriteTextField.getText()));
	}

	private MeterSquareQuantityBox quantityAdapterForTableView() {
		final MeterSquareQuantityBox meterSquareQuantityBox = (MeterSquareQuantityBox) formScrollPane.getContent();
		//some quantity service
		//entry.setQuantity(meterSquareQuantityBox.getQuantity());
		return null;
	}


	/**
	 * TODO unités : U->unité, m3->mètre cube, ens->ensemble
	 * TODO OUVERTURES
	 * TODO pouvoir calculer des surfaces sur polygone N cotés (N est choisi par l'utilisateur ainsi que la formule.
	 */

	@FXML
	public void onAddButtonAction() {

		if (formScrollPane.getContent() instanceof ProductBox) {
			this.setCurrentRowAdapter(productAdapterForTableView());
			tableViewBox.tableView.getItems().add(this.getCurrentRowAdapter());
			//((TableViewBox) tableScrollPane.getContent()).tableView.getItems().add(productAdapterForTableView());
		} else if (formScrollPane.getContent() instanceof MeterSquareQuantityBox) {
			//rowAdapter.setQuantity(999.9f);
			final Float quantity = getClickedRowAdapter().getQuantity();
			getClickedRowAdapter().setPriceGen(getClickedRowAdapter().getPriceGen() * quantity);
		}
		formScrollPane.setContent(null);

		//TODO get the project name -> create a project system

		addButton.setDisable(true);
	}

	@FXML
	public void onDelButtonAction() {
		if (tableScrollPane.getContent() instanceof TableViewBox) {
			if (!tableViewBox.tableView.getItems().isEmpty() && tableViewBox.tableView.getItems().size() > 0) {
				final int index = tableViewBox.tableView.getSelectionModel().getSelectedIndex();
				tableViewBox.tableView.getItems().remove(index);

				if (clickedRowIndex == tableViewBox.tableView.getSelectionModel().getSelectedIndex() + 1
						&& formScrollPane.getContent() != null) {
					formScrollPane.setContent(null);
				}
			/*
			treeView.getRoot().getChildren().remove(index);
			*/
			}
		}
	}

	@FXML
	public void onSaveButtonAction() {
		final FileChooser fileChooser = new FileChooser();

		//set title and initial directory
		fileChooser.setTitle("enregistrer sous");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		//Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichier JSON (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extFilter);

		//Show save file dialog
		File file = fileChooser.showSaveDialog(new Stage(StageStyle.DECORATED));

		if (file != null) {
			SaveTableViewService.save(file.getAbsolutePath(), tableViewBox.tableView.getItems());
		}
	}

	@FXML
	public void OnOpenButtonAction() {
		List<RowAdapter> items;
		final FileChooser fileChooser = new FileChooser();

		//set title and initial directory
		fileChooser.setTitle("ouvrir");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		//Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichier JSON (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extFilter);

		//Show save file dialog
		File file = fileChooser.showOpenDialog(new Stage(StageStyle.DECORATED));

		if (file != null) {
			items = SaveTableViewService.open(file.getAbsolutePath());
			tableViewBox.tableView.getItems().setAll(items);
		}
	}
}

