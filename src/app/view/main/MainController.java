package app.view.main;

import app.view.main.widgets.form_box.product.ProductBox;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.table_box.TableViewBox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
	public AnchorPane rowSetup;
	@FXML
	public MenuButton newMenu;
	@FXML
	public ScrollPane tableScrollPane;

	private MenuItem row = new MenuItem("ligne");

	public RowAdapter rowAdapter = new RowAdapter();


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

		tableScrollPane.setContent(new TableViewBox(this));
	}

	/**
	 * TODO unités : U->unité, ml->mètre linéaire, m2->mètres carré, m3->mètre cube, ens->ensemble
	 * TODO quantité (une colonne clicable qui ouvre un tableau) -> linéaire(hauteur), -ouvertures,
	 * TODO (+) opérations entre (linéaireN, hauteurN, ouvertureN)
	 * TODO pouvoir calculer des surfaces sur polygone N cotés (N est choisi par l'utilisateur ainsi que la formule.
	 */

	@FXML
	public void onAddButtonAction() {
		formScrollPane.setContent(null);
		rowAdapter.setSel(true);
		if (tableScrollPane.getContent() instanceof TableViewBox) {
			final TableViewBox tableViewBox = (TableViewBox) tableScrollPane.getContent();
			tableViewBox.tableBox.getItems().add(rowAdapter);
		}
		/*
		if (treeView.getRoot() == null) {
			//TODO get the project name -> create a project system
			treeView.setRoot(new TreeItem<>("nom du projet"));
		}
		treeView.getRoot().getChildren().add(
				treeView.getRoot().getChildren().size(), new TreeItem<>(rowAdapter.getName())
		);
		treeView.edit();
		treeView.getRoot().getChildren().get(
				treeView.getRoot().getChildren().size() - 1
		).getChildren().addAll(
				new TreeItem<>(rowAdapter.getSel() ? "on" : "off"),
				new TreeItem<>(rowAdapter.getProduct()),
				new TreeItem<>(rowAdapter.getSeller()),
				new TreeItem<>(rowAdapter.getSizeSquare().toString()),
				new TreeItem<>(rowAdapter.getPriceWrite().toString()),
				new TreeItem<>(rowAdapter.getTva().toString()),
				new TreeItem<>(rowAdapter.getPriceGen().toString())
		);
		*/
		rowAdapter = new RowAdapter();
		addButton.setDisable(true);
	}

	@FXML
	public void onDelButtonAction() {
		if (tableScrollPane.getContent() instanceof TableViewBox) {
			final TableViewBox tableViewBox = (TableViewBox) tableScrollPane.getContent();
			if (!tableViewBox.tableBox.getItems().isEmpty()) {
				final int index = tableViewBox.tableBox.getSelectionModel().getSelectedIndex();
				tableViewBox.tableBox.getItems().remove(index);
			/*
			treeView.getRoot().getChildren().remove(index);
			*/
			}
		}
	}
}

