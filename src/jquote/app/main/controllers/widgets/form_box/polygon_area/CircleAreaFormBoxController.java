package jquote.app.main.controllers.widgets.form_box.polygon_area;

import jquote.app.main.controllers.MainStageController;
import jquote.app.main.javafx.FloatTextField;
import jquote.app.main.widgets_objects.impl.Polygon;
import javafx.fxml.FXML;
import jquote.app.main.widgets_objects.polygons.Circle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class CircleAreaFormBoxController extends PolygonAreaBox {
	@FXML
	private FloatTextField radiusTextField;

	private Circle polygon;

	public CircleAreaFormBoxController(MainStageController controller) {
		super("CircleAreaFormBox", controller);
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
