package app.main.controllers;

import app.main.concurency.OpenService;
import app.main.concurency.SaveService;
import app.main.controllers.widgets.dialog.AlertDialog;
import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.controllers.widgets.form_box.ProductFormBoxController;
import app.main.controllers.widgets.table_box.TableViewBoxController;
import app.main.javafx.impl.RootController;
import app.main.adapters.TableRowAdapter;
import app.main.javafx.QuantityTableCell;
import app.main.services.AppUtil;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.util.List;

import static app.main.services.KeyEventService.shortCuts;

/**
 * java.view Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public final class MainStageController extends RootController {
	public AnchorPane rootPane;
	public ScrollPane formScrollPane;
	public Button addButton;
	public Button delButton;
	public MenuButton newMenu;
	public ScrollPane tableScrollPane;
	public TableViewBoxController tableViewBoxController;
	public Button saveButton;
	public Button openButton;

	private MenuItem row = new MenuItem("ligne");
	/**
	 * Current <=> JustAdded
	 */
	private TableRowAdapter currentTableRowAdapter;

	/**
	 * OnRowClicked <=> quantity clicked
	 */
	private TableRowAdapter clickedTableRowAdapter;
	private QuantityTableCell clickedCell;
	private int clickedRowIndex;

	public TableRowAdapter getCurrentTableRowAdapter() {
		return currentTableRowAdapter;
	}

	public void setCurrentTableRowAdapter(TableRowAdapter currentTableRowAdapter) {
		this.currentTableRowAdapter = currentTableRowAdapter;
	}

	public void setClickedTableRowAdapter(TableRowAdapter clickedTableRowAdapter) {
		this.clickedTableRowAdapter = clickedTableRowAdapter;
	}

	public TableRowAdapter getClickedTableRowAdapter() {
		return clickedTableRowAdapter;
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

	@FXML
	private void initialize() {

		tableViewBoxController = new TableViewBoxController(this);
		tableScrollPane.setContent(tableViewBoxController);

		addButton.setDisable(false);

		delButton.disableProperty().bind(Bindings.size(tableViewBoxController.tableView.getItems()).lessThan(0));

		row.setOnAction(event -> {
			formScrollPane.setContent(null);
			formScrollPane.setContent(new ProductFormBoxController(this));
		});
		newMenu.getItems().setAll(row);

		rootPane.setOnKeyReleased(event -> shortCuts(event, this));
	}


	/**
	 * FIXME :: unités : U->unité, m3->mètre cube, ens->ensemble
	 * <p>
	 * UPGRADEME :: pouvoir calculer des surfaces sur polygone N cotés (N est choisi par l'utilisateur avec ou non la formule) à voir.
	 */

	@FXML
	public void onAddButtonAction() {
		addButton.disableProperty().unbind();

		if (formScrollPane.getContent() instanceof ProductFormBoxController) {
			tableViewBoxController.tableView.getItems().add(currentTableRowAdapter);
			currentTableRowAdapter.setPriceGen(currentTableRowAdapter.getPriceWrite(), currentTableRowAdapter.getTva());
		} else if (formScrollPane.getContent() instanceof MeterSquareQuantityFormBoxController) {
			//TODO : create objectProperty :: currentTableRowAdapter.setAreaListCellAdapter()
		}
		formScrollPane.setContent(null);

		//UPGRADEME get the project name -> create a project system
	}

	@FXML
	public void onDelButtonAction() {
		if (tableScrollPane.getContent() instanceof TableViewBoxController) {
			if (!tableViewBoxController.tableView.getItems().isEmpty() && tableViewBoxController.tableView.getItems().size() > 0) {
				final int index = tableViewBoxController.tableView.getSelectionModel().getSelectedIndex();
				if (!tableViewBoxController.tableView.getItems().isEmpty())
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
			final SaveService saveService = new SaveService(file.getAbsolutePath(), tableViewBoxController.tableView.getItems());
			saveService.start();
			saveService.stateProperty().addListener((observable, oldValue, newValue) -> {
				final Point2D position = new Point2D(saveButton.getLayoutX() + saveButton.getWidth(), saveButton.getLayoutY());
				switch (newValue) {
					case FAILED:
						AppUtil.showAlertDialog(rootPane,position,"ERREUR lors de la sauvegarde du fichier :", file.getAbsolutePath());
						break;
					case CANCELLED:
						AppUtil.showAlertDialog(rootPane,position,"ANNULATION de la sauvegarde du fichier :", file.getAbsolutePath());
						break;
					case READY:
						break;
					case SCHEDULED:
						break;
					case RUNNING:
						break;
					case SUCCEEDED:
				}
			});
		}
	}

	@FXML
	public void OnOpenButtonAction() {
		List<TableRowAdapter> items;
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
			final OpenService openService = new OpenService(file.getAbsolutePath());
			openService.start();
			openService.stateProperty().addListener((observable, oldValue, newValue) -> {
				final Point2D position = new Point2D(openButton.getLayoutX() + openButton.getWidth(), openButton.getLayoutY());
				switch (newValue) {
					case FAILED:
						AppUtil.showAlertDialog(rootPane,position,"ERREUR lors de l'ouverture du fichier :", file.getAbsolutePath());
						break;
					case CANCELLED:
						AppUtil.showAlertDialog(rootPane,position,"ANNULATION de l'ouverture du fichier :", file.getAbsolutePath());
						break;
					case READY:
						break;
					case SCHEDULED:
						break;
					case RUNNING:
						break;
					case SUCCEEDED:
						tableViewBoxController.tableView.getItems().setAll(openService.getValue());
						break;
				}
			});
		}
	}
}

