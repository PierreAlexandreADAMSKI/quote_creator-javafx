package app.view.main.widgets.form_box.quantities.polygones.triangle;

import app.view.main.MainController;
import app.view.main.javafx.FloatTextField;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.PolygonBox;
import app.view.main.widgets.form_box.text_formaters.FloatFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TriangleFormBox extends PolygonBox {
	@FXML
	public FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Triangle polygon;

	public TriangleFormBox(MainController controller) {
		super("TriangleFormBox", controller);
		polygon = new Triangle();

	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	protected void comportment() {
		heightTextField.setOnKeyReleased(event -> {
			if (!heightTextField.getText().equals("") && heightTextField != null){
				polygon.setHeight(Float.valueOf(heightTextField.getText()));
			}
		});
		linearTextField.setOnKeyReleased(event -> {
			if (!linearTextField.getText().equals("") && linearTextField != null){
				polygon.setBase(Float.valueOf(linearTextField.getText()));
			}
		});
	}
}
