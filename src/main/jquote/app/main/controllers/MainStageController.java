package app.main.controllers;

import app.main.concurency.OpenService;
import app.main.concurency.SaveService;
import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.controllers.widgets.form_box.ProductFormBoxController;
import app.main.controllers.widgets.table_box.TableViewBoxController;
import app.main.javafx.impl.RootController;
import app.main.adapters.TableRowAdapter;
import app.main.javafx.QuantityTableCell;
import app.main.services.AppUtil;
import app.templates.TemplatesViewer;
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

import java.io.File;
import java.io.IOException;
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

	private MenuItem rowMenuItem = new MenuItem("nouveau produit");
	private MenuItem templateMenuItem = new MenuItem("modèles");

	private TemplatesViewer templatesViewer;
	private File template;
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

		addButton.setDisable(true);

		delButton.disableProperty().bind(tableViewBoxController.tableView.getSelectionModel().selectedIndexProperty().isEqualTo(-1));

		rowMenuItem.setOnAction(event -> {
			formScrollPane.setContent(null);
			formScrollPane.setContent(new ProductFormBoxController(this));
		});

		templateMenuItem.setOnAction(event1 -> {
					final FileChooser fileChooser = new FileChooser();

					//set title and initial directory
					fileChooser.setTitle("ouvrir");
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

					//Set extension filter
					FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter("Fichier SVG (*.svg)", "*.svg");
					FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter("Fichier PDF (*.pdf)", "*.pdf");
					fileChooser.getExtensionFilters().addAll(extensionFilter1, extensionFilter2);

					//Show save file dialog
					template = fileChooser.showOpenDialog(new Stage(StageStyle.DECORATED));

					if (template != null) {
						try {
							templatesViewer = new TemplatesViewer(template);
						} catch (IOException e) {
							template = null;
							e.printStackTrace();
						}
					}
				});
		newMenu.getItems().setAll(rowMenuItem, templateMenuItem);

		tableScrollPane.setOnKeyReleased(event -> shortCuts(event, this));
	}


	/**
	 * UPGRADEME :: pouvoir calculer des surfaces sur polygone N cotés (N est choisi par l'utilisateur avec ou non la formule) à voir.
	 */

	@FXML
	public void onAddButtonAction() {
		//unbind if needed then disable add button
		if (addButton.disableProperty().isBound()) {
			addButton.disableProperty().unbind();
		}
		addButton.setDisable(true);

		//action following the type of form
		if (formScrollPane.getContent() instanceof ProductFormBoxController) {
			tableViewBoxController.tableView.getItems().add(currentTableRowAdapter);
			currentTableRowAdapter.setPriceGen(currentTableRowAdapter.getTvaPriceWrite() * currentTableRowAdapter.getQuantity());
		} else if (formScrollPane.getContent() instanceof MeterSquareQuantityFormBoxController) {
			//TODO : create objectProperty :: currentTableRowAdapter.setAreaListCellAdapter()
		}
		formScrollPane.setContent(null);

		//UPGRADEME get the project name -> create a project system
	}

	@FXML
	public void onDelButtonAction() {
		if (addButton.disableProperty().isBound()) {
			addButton.disableProperty().unbind();
		}
		if (tableScrollPane.getContent() instanceof TableViewBoxController) {
			final int index = tableViewBoxController.tableView.getSelectionModel().getSelectedIndex();
			tableViewBoxController.tableView.getItems().remove(index);
			if (formScrollPane.getContent() != null) {
				formScrollPane.setContent(null);
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
						AppUtil.showAlertDialog(rootPane, position, "ERREUR lors de la sauvegarde du fichier :", file.getAbsolutePath());
						break;
					case CANCELLED:
						AppUtil.showAlertDialog(rootPane, position, "ANNULATION de la sauvegarde du fichier :", file.getAbsolutePath());
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
						AppUtil.showAlertDialog(rootPane, position, "ERREUR lors de l'ouverture du fichier :", file.getAbsolutePath());
						break;
					case CANCELLED:
						AppUtil.showAlertDialog(rootPane, position, "ANNULATION de l'ouverture du fichier :", file.getAbsolutePath());
					case READY:
					case SCHEDULED:
					case RUNNING:
						break;
					case SUCCEEDED:
						tableViewBoxController.tableView.getItems().clear();
						openService.getValue().forEach(item -> {
							setCurrentTableRowAdapter(item);
							tableViewBoxController.tableView.getItems().add(item);
						});
						break;
				}
			});
		}
	}
}

