package jquote.app.main.controllers.widgets.form_box;

import jquote.app.main.controllers.MainStageController;
import jquote.app.main.javafx.Box;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 04/04/2016.
 */
public class ProductFormBoxController extends Box {
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


	public ProductFormBoxController(MainStageController controller) {
		super("main/controllers/form_box","ProductFormBox", controller);
		this.initForForm(controller.addButton);
	}

	public void init() {
		final Pattern wholeNumberPattern = Pattern.compile("[0-9]*[.]?[0-9]*");

		sizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				sizeTextField.setText(oldValue);
		});
		priceWriteTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				priceWriteTextField.setText(oldValue);
		});
		tvaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				tvaTextField.setText(oldValue);
		});

		unitMenuButton.getItems().setAll(unityMenuItem,meterMenuItem,meter2MenuItem,meter3MenuItem,setMenuItem);
		unitMenuButton.getItems().stream().forEach(item -> item.setOnAction(clickEvent -> unitMenuButton.setText(item.getText())));
	}
}
