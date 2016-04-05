package app.view.main.widgets;

import app.App;
import app.view.main.MainController;
import app.view.main.adapters.RowAdapter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 04/04/2016.
 */
public class ProductFormBox extends VBox {
	@FXML
	public TextField nameTextField;
	@FXML
	public VBox productFormBox;
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

	public ProductFormBox() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductFormBox.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initForm(MainController controller) {
		this.setPadding(new Insets(10));
		((VBox) this.getChildren().get(0)).setSpacing(10);
		((VBox) this.getChildren().get(0)).getChildren().forEach(node -> {
			((HBox) node).getChildren().stream().filter(text ->
					text instanceof Text).forEach(text -> text.setTranslateX(-5));
			((HBox) node).setAlignment(Pos.CENTER_RIGHT);
		});

		this.setOnMouseMoved(event -> {
			if (((VBox) this.getChildren().get(0)).getChildren().stream().filter(textField ->
					textField instanceof TextField).allMatch(predicate ->
					!((TextField) predicate).getText().isEmpty() && !((TextField) predicate).getText().equals(""))) {
				controller.addButton.setDisable(false);
			}
		});

		final Pattern wholeNumberPattern = Pattern.compile("[0-9]*[.]?[0-9]*");
		nameTextField.setOnKeyReleased(keyTyped -> controller.rowAdapter.setName(nameTextField.getText()));
		productTextField.setOnKeyReleased(keyTyped -> controller.rowAdapter.setProduct(productTextField.getText()));
		sellerTextField.setOnKeyReleased(keyTyped -> controller.rowAdapter.setSeller(sellerTextField.getText()));

		sizeTextField.setOnKeyReleased(keyTyped -> {
			if (!sizeTextField.getText().isEmpty() && !sizeTextField.getText().equals(""))
				controller.rowAdapter.setSizeSquare(Float.valueOf(sizeTextField.getText()));
		});
		sizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				sizeTextField.setText(oldValue);
		});
		priceWriteTextField.setOnKeyReleased(keyTyped -> {
			if (!priceWriteTextField.getText().isEmpty() && !priceWriteTextField.getText().equals("")) {
				controller.rowAdapter.setPriceWrite(Float.valueOf(priceWriteTextField.getText()));
				if (!tvaTextField.getText().isEmpty()
						&& !tvaTextField.getText().equals("")) {
					controller.rowAdapter.setPriceGen(Float.valueOf(priceWriteTextField.getText()) +
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
				controller.rowAdapter.setTva(Float.valueOf(tvaTextField.getText()));
				if (!priceWriteTextField.getText().isEmpty()
						&& !priceWriteTextField.getText().equals("")) {
					controller.rowAdapter.setPriceGen(Float.valueOf(priceWriteTextField.getText()) +
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
