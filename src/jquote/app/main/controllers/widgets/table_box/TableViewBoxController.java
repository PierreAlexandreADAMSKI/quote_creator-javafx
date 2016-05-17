package jquote.app.main.controllers.widgets.table_box;

import jquote.app.main.adapters.RowAdapter;
import jquote.app.main.controllers.MainStageController;
import jquote.app.main.controllers.widgets.form_box.ProductFormBoxController;
import jquote.app.main.javafx.Box;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import jquote.app.main.javafx.QuantityTableCell;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class TableViewBoxController extends Box {


	private MainStageController mController;
	@FXML
	public TableView<RowAdapter> tableView;
	@FXML
	public TableColumn<RowAdapter, Boolean> selCol;
	@FXML
	public TableColumn<RowAdapter, String> productCol;
	@FXML
	public TableColumn<RowAdapter, String> sellerCol;
	@FXML
	public TableColumn<RowAdapter, Float> sizeCol;
	@FXML
	public TableColumn<RowAdapter, String> unitCol;
	@FXML
	public TableColumn<RowAdapter, Float> priceHTCol;
	@FXML
	public TableColumn<RowAdapter, Float> quantityCol;
	@FXML
	public TableColumn<RowAdapter, Float> tvaCol;
	@FXML
	public TableColumn<RowAdapter, Float> priceTTCCol;


	public TableViewBoxController(MainStageController controller) {
		super("main/controllers/table_box", "TableViewBox", controller);
		this.mController = controller;

		initTable();
		initEvents();
	}

	private void initTable() {
		productCol.setCellValueFactory(new PropertyValueFactory<>("product"));
		sellerCol.setCellValueFactory(new PropertyValueFactory<>("seller"));
		sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
		unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));
		priceHTCol.setCellValueFactory(new PropertyValueFactory<>("priceWrite"));
		tvaCol.setCellValueFactory(new PropertyValueFactory<>("tva"));
		priceTTCCol.setCellValueFactory(new PropertyValueFactory<>("priceGen"));
		selCol.setCellValueFactory(new PropertyValueFactory<>("selCol"));
		selCol.setCellFactory(CheckBoxTableCell.forTableColumn(selCol));
		selCol.setEditable(true);
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		quantityCol.setCellFactory(factory -> new QuantityTableCell(this.mController));
	}

	private void initEvents(){
		this.mController.delButton.disableProperty().bind(
				Bindings.not(Bindings.size(tableView.getItems()).greaterThan(0)));

		//TODO CUSTOM EVENT CLASS -> <Class>KeyEventHandler<Class/>
		this.setOnKeyReleased(event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case N: {
						final ProductFormBoxController productFormBox = new ProductFormBoxController(this.mController);
						this.mController.formScrollPane.setContent(productFormBox);
						productFormBox.init();
						break;
					}
					/* BUG
					case D: {
						if (!tableView.getItems().isEmpty()) {
							final RowAdapter temp = tableView.getSelectionModel().getSelectedItem();
							tableView.getItems().add(temp);
						}
						break;
					}
					*/
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
	}
}