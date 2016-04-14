package app.view.main.widgets.form_box.product;

import app.view.main.MainController;
import app.view.main.widgets.Box;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.regex.Pattern;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 04/04/2016.
 */
public class ProductBox extends Box {
	@FXML
	public TextField nameTextField;
	@FXML
	public TextField productTextField;
	@FXML
	public TextField sellerTextField;
	@FXML
	public TextField sizeTextField;
	@FXML
	public TextField priceWriteTextField;
	@FXML
	public TextField tvaTextField;


	public ProductBox(MainController controller) {
		super("ProductBox", controller);
		this.initForForm();
	}

	public void init() {

		final Pattern wholeNumberPattern = Pattern.compile("[0-9]*[.]?[0-9]*");
		nameTextField.setOnKeyReleased(keyTyped -> this.mController.rowAdapter.setName(nameTextField.getText()));
		productTextField.setOnKeyReleased(keyTyped -> this.mController.rowAdapter.setProduct(productTextField.getText()));
		sellerTextField.setOnKeyReleased(keyTyped -> this.mController.rowAdapter.setSeller(sellerTextField.getText()));

		sizeTextField.setOnKeyReleased(keyTyped -> {
			if (!sizeTextField.getText().isEmpty() && !sizeTextField.getText().equals(""))
				this.mController.rowAdapter.setSizeSquare(Float.valueOf(sizeTextField.getText()));
		});
		sizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				sizeTextField.setText(oldValue);
		});
		priceWriteTextField.setOnKeyReleased(keyTyped -> {
			if (!priceWriteTextField.getText().isEmpty() && !priceWriteTextField.getText().equals("")) {
				this.mController.rowAdapter.setPriceWrite(Float.valueOf(priceWriteTextField.getText()));
				if (!tvaTextField.getText().isEmpty()
						&& !tvaTextField.getText().equals("")) {
					this.mController.rowAdapter.setPriceGen(Float.valueOf(priceWriteTextField.getText()) +
							(Float.valueOf(priceWriteTextField.getText()) * Float.valueOf(tvaTextField.getText()) / 100.f));
				}
			}
		});
		priceWriteTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				priceWriteTextField.setText(oldValue);
		});
		tvaTextField.setOnKeyReleased(keyTyped -> {
			if (!tvaTextField.getText().isEmpty() && !tvaTextField.getText().equals("")) {
				this.mController.rowAdapter.setTva(Float.valueOf(tvaTextField.getText()));
				if (!priceWriteTextField.getText().isEmpty()
						&& !priceWriteTextField.getText().equals("")) {
					this.mController.rowAdapter.setPriceGen(Float.valueOf(priceWriteTextField.getText()) +
							(Float.valueOf(priceWriteTextField.getText()) * Float.valueOf(tvaTextField.getText()) / 100.f));
				}
			}
		});
		tvaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				tvaTextField.setText(oldValue);
		});
	}
}
