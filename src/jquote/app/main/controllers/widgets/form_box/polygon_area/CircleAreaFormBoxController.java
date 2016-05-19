package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.MainStageController;
import app.main.javafx.Box;
import app.main.javafx.FloatTextField;
import app.main.widgets_objects.interfaces.Polygon;
import app.main.widgets_objects.interfaces.PolygonAreaBox;
import javafx.fxml.FXML;
import app.main.widgets_objects.polygons.Circle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class CircleAreaFormBoxController extends Box implements PolygonAreaBox {
	@FXML
	private FloatTextField radiusTextField;

	private Circle polygon;

	public CircleAreaFormBoxController() {
		super("CircleAreaFormBox");
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
