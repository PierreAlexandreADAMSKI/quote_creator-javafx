package app.view.main.widgets.form_box.quantities.polygones.triangle;

import app.view.main.MainController;
import app.view.main.widgets.Box;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TriangleFormBox extends Box {
	@FXML
	public TextField linearTextField;
	@FXML
	public TextField heightTextField;
	public Polygon polygon = new Triangle();

	//TODO found a better way
	public TriangleFormBox(MainController controller) {
		super("TriangleFormBox", controller);
		final Pattern wholeNumberPattern = Pattern.compile("[0-9]*[.]?[0-9]*");

		heightTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				heightTextField.setText(oldValue);
		});
		linearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!wholeNumberPattern.matcher(newValue).matches())
				linearTextField.setText(oldValue);
		});
		heightTextField.setOnKeyReleased(event -> {
			if (!heightTextField.getText().equals("") && heightTextField != null){
				((Triangle) polygon).setHeight(Float.valueOf(heightTextField.getText()));
			}
		});
		linearTextField.setOnKeyReleased(event -> {
			if (!linearTextField.getText().equals("") && linearTextField != null){
				((Triangle) polygon).setBase(Float.valueOf(linearTextField.getText()));
			}
		});
	}
}
