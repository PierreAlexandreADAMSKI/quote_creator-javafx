package app.view.main;

import app.view.main.widgets.form_box.product.ProductBox;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.form_box.quantities.MeterSquareQuantityBox;
import app.view.main.widgets.table_box.QuantityTableCell;
import app.view.main.widgets.table_box.TableViewBox;
import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.beans.binding.FloatBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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

	private MenuItem row = new MenuItem("ligne");

	private QuantityTableCell currentClickedCell;
	private int currentClickedRowIndex;


	public int getCurrentClickedRowIndex() {
		return currentClickedRowIndex;
	}

	public void setCurrentClickedRowIndex(int currentClickedRowIndex) {
		this.currentClickedRowIndex = currentClickedRowIndex;
	}

	public QuantityTableCell getCurrentClickedCell() {
		return currentClickedCell;
	}

	public void setCurrentClickedCell(QuantityTableCell currentClickedCell) {
		this.currentClickedCell = currentClickedCell;
	}

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

	/**
	 * TODO unités : U->unité, ml->mètre linéaire, m2->mètres carré, m3->mètre cube, ens->ensemble
	 * TODO quantité (une colonne clicable qui ouvre un tableau) -> linéaire(hauteur), -ouvertures,
	 * TODO (+) opérations entre (linéaireN, hauteurN, ouvertureN)
	 * TODO pouvoir calculer des surfaces sur polygone N cotés (N est choisi par l'utilisateur ainsi que la formule.
	 */

	@FXML
	public void onAddButtonAction() {

		if (formScrollPane.getContent() instanceof ProductBox) {
			tableViewBox.tableView.getItems().add(productAdapterForTableView());
			//((TableViewBox) tableScrollPane.getContent()).tableView.getItems().add(productAdapterForTableView());
		} else if (formScrollPane.getContent() instanceof MeterSquareQuantityBox) {
			if (currentClickedRowIndex >= -1) {
				//rowAdapter.setQuantity(999.9f);
				final Float quantity = currentRowAdapter().getQuantity();
				currentClickedCell.text = quantity.toString();
				currentRowAdapter().setPriceGen(currentRowAdapter().getPriceGen() * quantity);
			}
		}
		formScrollPane.setContent(null);

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
				new TreeItem<>(rowAdapter.getSize().toString()),
				new TreeItem<>(rowAdapter.getPriceWrite().toString()),
				new TreeItem<>(rowAdapter.getTva().toString()),
				new TreeItem<>(rowAdapter.getPriceGen().toString())
		);
		*/
		addButton.setDisable(true);
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


	public RowAdapter currentRowAdapter() {
		return currentClickedCell.getTableView().getItems().get(currentClickedRowIndex);
	}

		@FXML
	public void onDelButtonAction() {
		if (tableScrollPane.getContent() instanceof TableViewBox) {
			if (!tableViewBox.tableView.getItems().isEmpty()) {
				final int index = tableViewBox.tableView.getSelectionModel().getSelectedIndex();
				tableViewBox.tableView.getItems().remove(index);
			/*
			treeView.getRoot().getChildren().remove(index);
			*/
			}
		}
	}


}

