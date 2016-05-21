package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.MainStageController;
import app.main.javafx.Box;
import app.main.javafx.FloatTextField;
import app.main.widgets_objects.interfaces.Polygon;
import app.main.widgets_objects.interfaces.PolygonAreaBox;
import javafx.fxml.FXML;
import app.main.widgets_objects.polygons.Arc;
import sun.applet.Main;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class ArcAreaFormBoxController extends Box implements PolygonAreaBox {
	@FXML
	private FloatTextField radiusTextField;
	@FXML
	private FloatTextField angleTextField;

	private Arc polygon;

	public ArcAreaFormBoxController() {
		super("ArcAreaFormBox");
		polygon = new Arc();
	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	protected void comportment(MainStageController controller) {
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
