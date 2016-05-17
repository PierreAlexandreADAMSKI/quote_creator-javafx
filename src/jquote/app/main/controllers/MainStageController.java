package jquote.app.main.controllers;

import jquote.app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import jquote.app.main.controllers.widgets.form_box.ProductFormBoxController;
import jquote.app.main.controllers.widgets.table_box.TableViewBoxController;
import jquote.app.main.services.SaveTableViewService;
import jquote.app.main.adapters.RowAdapter;
import jquote.app.main.javafx.QuantityTableCell;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.List;

/**
 * java.view Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public class MainStageController {
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
	public TableViewBoxController tableViewBoxController;
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
			final ProductFormBoxController productFormBox = new ProductFormBoxController(this);
			formScrollPane.setContent(null);
			formScrollPane.setContent(productFormBox);
			productFormBox.init();
		});
		newMenu.getItems().setAll(row);

		tableViewBoxController = new TableViewBoxController(this);
		tableScrollPane.setContent(tableViewBoxController);
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
		final ProductFormBoxController productFormBoxController = (ProductFormBoxController) formScrollPane.getContent();
		return new RowAdapter(productFormBoxController.nameTextField.getText(),
				productFormBoxController.productTextField.getText(),
				productFormBoxController.sellerTextField.getText(),
				Float.valueOf(productFormBoxController.sizeTextField.getText()),
				productFormBoxController.unitMenuButton.getText(),
				Float.valueOf(productFormBoxController.tvaTextField.getText()),
				Float.valueOf(productFormBoxController.priceWriteTextField.getText()));
	}

	private MeterSquareQuantityFormBoxController quantityAdapterForTableView() {
		final MeterSquareQuantityFormBoxController meterSquareQuantityFormBoxController = (MeterSquareQuantityFormBoxController) formScrollPane.getContent();
		//some quantity service
		//entry.setQuantity(meterSquareQuantityFormBoxController.getQuantity());
		return null;
	}


	/**
	 * TODO unités : U->unité, m3->mètre cube, ens->ensemble
	 * TODO pouvoir calculer des surfaces sur polygone N cotés (N est choisi par l'utilisateur ouvec ou non la formule.
	 */

	@FXML
	public void onAddButtonAction() {

		if (formScrollPane.getContent() instanceof ProductFormBoxController) {
			this.setCurrentRowAdapter(productAdapterForTableView());
			tableViewBoxController.tableView.getItems().add(this.getCurrentRowAdapter());
			//((TableViewBoxController) tableScrollPane.getContent()).tableView.getItems().add(productAdapterForTableView());
		} else if (formScrollPane.getContent() instanceof MeterSquareQuantityFormBoxController) {
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
		if (tableScrollPane.getContent() instanceof TableViewBoxController) {
			if (!tableViewBoxController.tableView.getItems().isEmpty() && tableViewBoxController.tableView.getItems().size() > 0) {
				final int index = tableViewBoxController.tableView.getSelectionModel().getSelectedIndex();
				tableViewBoxController.tableView.getItems().remove(index);

				if (clickedRowIndex == tableViewBoxController.tableView.getSelectionModel().getSelectedIndex() + 1 //chelou le +1 mais bon
						&& formScrollPane.getContent() != null) {
					formScrollPane.setContent(null);
				}
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
			SaveTableViewService.save(file.getAbsolutePath(), tableViewBoxController.tableView.getItems());
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
			tableViewBoxController.tableView.getItems().setAll(items);
		}
	}
}

