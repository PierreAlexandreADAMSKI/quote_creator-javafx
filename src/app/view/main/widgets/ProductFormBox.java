package app.view.main.widgets;

import app.App;
import app.view.main.adapters.RowAdapter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	private VBox productFormBox;
	@FXML
	private TextField productTextField;
	@FXML
	private TextField sellerTextField;
	@FXML
	private TextField sizeTextField;
	@FXML
	private TextField priceWriteTextField;
	@FXML
	private TextField tvaTextField;

	public ProductFormBox() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductFormBox.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPadding(new Insets(10));
		((VBox) this.getChildren().get(0)).setSpacing(10);
		((VBox) this.getChildren().get(0)).getChildren().forEach(node -> {
			((HBox) node).getChildren().stream().filter(text ->
					text instanceof Text).forEach(text -> text.setTranslateX(-5));
			((HBox) node).setAlignment(Pos.CENTER_RIGHT);
		});
	}

	public void initForm(RowAdapter adapter) {
		final Pattern wholeNumberPattern = Pattern.compile("[0-9]*[.]?[0-9]*");
		productTextField.setOnKeyTyped(keyTyped -> adapter.setProduct(productTextField.getText()));
		sellerTextField.setOnKeyTyped(keyTyped -> adapter.setProduct(sellerTextField.getText()));

		sizeTextField.setOnKeyTyped(keyTyped -> adapter.setProduct(sizeTextField.getText()));
		sizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				sizeTextField.setText(oldValue);
		});
		priceWriteTextField.setOnKeyTyped(keyTyped -> {
			adapter.setProduct(priceWriteTextField.getText());
			if (!tvaTextField.getText().isEmpty()
					&& !tvaTextField.getText().equals("")
					&& !priceWriteTextField.getText().equals("")) {
				adapter.setPriceGen(Float.valueOf(priceWriteTextField.getText()) +
						(Float.valueOf(priceWriteTextField.getText()) * Float.valueOf(tvaTextField.getText()) / 100.f));
			}
		});
		priceWriteTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				priceWriteTextField.setText(oldValue);
		});
		tvaTextField.setOnKeyTyped(keyTyped -> {
			adapter.setProduct(tvaTextField.getText());
			if (!priceWriteTextField.getText().isEmpty()
					&& !priceWriteTextField.getText().equals("")
					&& !tvaTextField.getText().equals("")) {
				adapter.setPriceGen(Float.valueOf(priceWriteTextField.getText()) +
						(Float.valueOf(priceWriteTextField.getText()) * Float.valueOf(tvaTextField.getText()) / 100.f));
			}
		});
		tvaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				tvaTextField.setText(oldValue);
		});
	}
}
