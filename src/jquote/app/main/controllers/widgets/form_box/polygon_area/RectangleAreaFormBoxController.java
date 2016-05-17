package jquote.app.main.controllers.widgets.form_box.polygon_area;

import jquote.app.main.controllers.MainStageController;
import jquote.app.main.javafx.FloatTextField;
import jquote.app.main.widgets_objects.impl.Polygon;
import javafx.fxml.FXML;
import jquote.app.main.widgets_objects.polygons.Rectangle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class RectangleAreaFormBoxController extends PolygonAreaBox {
	@FXML
	FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Rectangle polygon;
	public RectangleAreaFormBoxController(MainStageController controller) {
		super("RectangleAreaFormBox", controller);
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
