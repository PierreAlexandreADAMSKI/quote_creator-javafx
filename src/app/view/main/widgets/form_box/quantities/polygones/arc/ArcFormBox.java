package app.view.main.widgets.form_box.quantities.polygones.arc;

import app.view.main.MainController;
import app.view.main.javafx.FloatTextField;
import app.view.main.widgets.Box;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.PolygonBox;
import javafx.fxml.FXML;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class ArcFormBox extends PolygonBox {
	@FXML
	private FloatTextField radiusTextField;
	@FXML
	private FloatTextField angleTextField;

	private Arc polygon;

	public ArcFormBox(MainController controller) {
		super("ArcFormBox", controller);
		polygon = new Arc();
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
		angleTextField.setOnKeyReleased(event -> {
			if (!angleTextField.getText().equals("") && angleTextField != null){
				polygon.setAngle(Float.valueOf(angleTextField.getText()));
			}
		});
	}
}
