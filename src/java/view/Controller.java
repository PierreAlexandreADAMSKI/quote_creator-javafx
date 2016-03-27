package java.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 * java.view Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public class Controller {
	@FXML
	private TableColumn productWriteCol;
	@FXML
	private TableColumn sellerSelCol;
	@FXML
	private TableColumn sizeSelCol;
	@FXML
	private TableColumn priceWriteCol;
	@FXML
	private TableColumn tvaCol;
	@FXML
	private TableColumn priceGenCol;

	@FXML
	private void initialize() {

	}
		/**
		 * editable cell example
		 *
		 *
		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> t) {
				((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
			}
		});
		 *
		 */


}
