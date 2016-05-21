package app.main.controllers.widgets.table_box;

import app.main.adapters.RowAdapter;
import app.main.controllers.MainStageController;
import app.main.javafx.ParentBox;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import app.main.javafx.QuantityTableCell;

/**
 * app.view.main.widgets.table_box Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class TableViewBoxController extends ParentBox{
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
		super("TableViewBox", controller);
	}


	@Override
	protected void comportment(MainStageController controller) {
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
		quantityCol.setCellFactory(factory -> new QuantityTableCell(controller));
	}

}
