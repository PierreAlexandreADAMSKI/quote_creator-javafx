package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.MainStageController;
import app.main.javafx.Box;
import app.main.javafx.FloatTextField;
import app.main.widgets_objects.interfaces.Polygon;
import app.main.widgets_objects.interfaces.PolygonAreaBox;
import javafx.fxml.FXML;
import app.main.widgets_objects.polygons.Triangle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TriangleAreaFormBoxController extends Box implements PolygonAreaBox {
	@FXML
	public FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Triangle polygon;

	public TriangleAreaFormBoxController() {
		super("TriangleAreaFormBox");
		polygon = new Triangle();

	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	protected void comportment(MainStageController controller) {
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
