package jquote.app.main.controllers.widgets.form_box.polygon_area;

import jquote.app.main.controllers.MainStageController;
import jquote.app.main.javafx.FloatTextField;
import jquote.app.main.widgets_objects.impl.Polygon;
import javafx.fxml.FXML;
import jquote.app.main.widgets_objects.polygons.Arc;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class ArcAreaFormBoxController extends PolygonAreaBox {
	@FXML
	private FloatTextField radiusTextField;
	@FXML
	private FloatTextField angleTextField;

	private Arc polygon;

	public ArcAreaFormBoxController(MainStageController controller) {
		super("ArcAreaFormBox", controller);
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
