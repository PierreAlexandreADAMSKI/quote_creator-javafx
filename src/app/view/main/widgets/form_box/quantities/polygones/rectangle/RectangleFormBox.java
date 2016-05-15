package app.view.main.widgets.form_box.quantities.polygones.rectangle;

import app.view.main.MainController;
import app.view.main.javafx.FloatTextField;
import app.view.main.widgets.Box;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.PolygonBox;
import app.view.main.widgets.form_box.text_formaters.FloatFormatter;
import javafx.fxml.FXML;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class RectangleFormBox extends PolygonBox {
	@FXML
	FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Rectangle polygon;
	public RectangleFormBox(MainController controller) {
		super("RectangleFormBox", controller);
		polygon = new Rectangle();
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
				polygon.setWidth(Float.valueOf(linearTextField.getText()));
			}
		});
	}
}
