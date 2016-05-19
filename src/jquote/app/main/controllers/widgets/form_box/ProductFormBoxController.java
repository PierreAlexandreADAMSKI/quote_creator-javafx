package app.main.controllers.widgets.form_box;

import app.main.controllers.MainStageController;
import app.main.javafx.Box;
import app.main.widgets_objects.interfaces.FormBox;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 04/04/2016.
 */
public class ProductFormBoxController extends Box implements FormBox {
	@FXML
	public TextField nameTextField;
	@FXML
	public TextField productTextField;
	@FXML
	public TextField sellerTextField;
	@FXML
	public TextField sizeTextField;
	@FXML
	public MenuButton unitMenuButton;
	@FXML
	public TextField priceWriteTextField;
	@FXML
	public TextField tvaTextField;

	private MenuItem unityMenuItem = new MenuItem("U");
	private MenuItem meterMenuItem = new MenuItem("m");
	private MenuItem meter2MenuItem = new MenuItem("m2");
	private MenuItem meter3MenuItem = new MenuItem("m3");
	private MenuItem setMenuItem = new MenuItem("ENS");


	public ProductFormBoxController() {
		super("ProductFormBox");
		init();
	}

	private void init() {
		unitMenuButton.getItems().setAll(unityMenuItem, meterMenuItem, meter2MenuItem, meter3MenuItem, setMenuItem);
		unitMenuButton.getItems().stream().forEach(item ->
				item.setOnAction(clickEvent ->
						unitMenuButton.setText(item.getText())));
	}

	@Override
	protected void comportment() {
	}
}
