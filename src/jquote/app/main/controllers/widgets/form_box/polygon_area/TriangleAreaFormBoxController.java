package jquote.app.main.controllers.widgets.form_box.polygon_area;

import jquote.app.main.controllers.MainStageController;
import jquote.app.main.javafx.FloatTextField;
import jquote.app.main.widgets_objects.impl.Polygon;
import javafx.fxml.FXML;
import jquote.app.main.widgets_objects.polygons.Triangle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TriangleAreaFormBoxController extends PolygonAreaBox {
	@FXML
	public FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Triangle polygon;

	public TriangleAreaFormBoxController(MainStageController controller) {
		super("TriangleAreaFormBox", controller);
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
