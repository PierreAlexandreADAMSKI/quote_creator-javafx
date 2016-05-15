package app.view.main.widgets.form_box.quantities.polygones.circle;

import app.view.main.MainController;
import app.view.main.javafx.FloatTextField;
import app.view.main.widgets.Box;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.PolygonBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class CircleFormBox extends PolygonBox {
	@FXML
	private FloatTextField radiusTextField;

	private Circle polygon;

	public CircleFormBox(MainController controller) {
		super("CircleFormBox", controller);
		polygon = new Circle();
	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	protected void comportment() {
		radiusTextField.setOnKeyReleased(event -> {
			if (!radiusTextField.getText().equals("") && radiusTextField != null){
				polygon.setRadius(Float.valueOf(radiusTextField.getText()));
			}
		});
	}
}
