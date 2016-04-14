package app.view.main.widgets.table_box;

import app.view.main.MainController;
import app.view.main.adapters.RowAdapter;
import app.view.main.widgets.Box;
import app.view.main.widgets.form_box.product.ProductBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class TableViewBox extends Box {

	private MainController mController;
	@FXML
	public TableView<RowAdapter> tableBox;
	private TableColumn<RowAdapter, Boolean> selCol = new TableColumn<>("");
	private TableColumn<RowAdapter, String> productWriteCol = new TableColumn<>("Produit");
	private TableColumn<RowAdapter, String> sellerSelCol = new TableColumn<>("revendeur");
	private TableColumn<RowAdapter, Float> sizeSelCol = new TableColumn<>("taille (m2)");
	private TableColumn<RowAdapter, Float> priceWriteCol = new TableColumn<>("prix HT (€/m2)");
	private TableColumn<RowAdapter, Float> tvaCol = new TableColumn<>("TVA");
	private TableColumn<RowAdapter, Float> priceGenCol = new TableColumn<>("prix TTC (€/m2)");
	private TableColumn<RowAdapter, Boolean> quantityCol = new TableColumn<>("Quantité");


	public TableViewBox(MainController controller) {
		super("TableViewBox", controller);
		this.mController = controller;

		initTable();
	}

	private void initTable() {
		this.mController.delButton.disableProperty().bind(
				Bindings.not(Bindings.size(tableBox.getItems()).greaterThan(0)));

		//TODO CUSTOM EVENT CLASS -> <Class>KeyEventHandler<Class/>
		this.setOnKeyReleased(event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case N: {
						final ProductBox productFormBox = new ProductBox(this.mController);
						this.mController.formScrollPane.setContent(productFormBox);
						productFormBox.init();
						break;
					}
					case D: {
						if (!tableBox.getItems().isEmpty()) {
							final RowAdapter temp = tableBox.getSelectionModel().getSelectedItem();
							tableBox.getItems().add(temp);
						}
						break;
					}
					default:
						break;
				}
			} else {
				switch (event.getCode()) {
					case BACK_SPACE: {
						this.mController.onDelButtonAction();
						break;
					}
				}
			}
		});

		//for the selCol to be editable
		tableBox.setEditable(true);

		tvaCol.setPrefWidth(60);
		selCol.setPrefWidth(30);
		selCol.setResizable(false);

		tableBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		productWriteCol.setCellValueFactory(new PropertyValueFactory<>("product"));
		sellerSelCol.setCellValueFactory(new PropertyValueFactory<>("seller"));
		sizeSelCol.setCellValueFactory(new PropertyValueFactory<>("size"));
		priceWriteCol.setCellValueFactory(new PropertyValueFactory<>("priceWrite"));
		tvaCol.setCellValueFactory(new PropertyValueFactory<>("tva"));
		priceGenCol.setCellValueFactory(new PropertyValueFactory<>("priceGen"));
		selCol.setCellValueFactory(new PropertyValueFactory<>("selCol"));
		selCol.setCellFactory(CheckBoxTableCell.forTableColumn(selCol));
		selCol.setEditable(true);
		quantityCol.setCellValueFactory(features -> new SimpleBooleanProperty(features.getValue() != null));
		quantityCol.setCellFactory(factory -> new QuantityTableCell(this.mController));

		tableBox.getColumns().setAll(selCol, productWriteCol,
				sellerSelCol, sizeSelCol,
				priceWriteCol, tvaCol,
				priceGenCol, quantityCol);

		/*common params for all the columns */
		for (TableColumn column : tableBox.getColumns()) {
			column.setSortable(false);
		}
	}
}
