package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.MainStageController;
import app.main.javafx.Box;
import app.main.javafx.FloatTextField;
import app.main.widgets_objects.interfaces.Polygon;
import app.main.widgets_objects.interfaces.PolygonAreaBox;
import javafx.fxml.FXML;
import app.main.widgets_objects.polygons.Trapeze;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TrapezeAreaFormBoxController extends Box implements PolygonAreaBox {
	@FXML
	FloatTextField linearGroundTextField;
	@FXML
	FloatTextField linearRoofTextField;
	@FXML
	FloatTextField heightTextField;

	private Trapeze polygon;

	public TrapezeAreaFormBoxController() {
		super("TrapezeAreaFormBox");
		polygon = new Trapeze();
	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	protected void comportment(MainStageController controller) {
		linearGroundTextField.setOnKeyReleased(event -> {
			if (!linearGroundTextField.getText().equals("") && linearGroundTextField != null){
				polygon.setBottomBase(Float.valueOf(linearGroundTextField.getText()));
			}
		});
		linearRoofTextField.setOnKeyReleased(event -> {
			if (!linearRoofTextField.getText().equals("") && linearRoofTextField != null){
				polygon.setTopBase(Float.valueOf(linearRoofTextField.getText()));
			}
		});
		heightTextField.setOnKeyReleased(event -> {
			if (!heightTextField.getText().equals("") && heightTextField != null){
				polygon.setHeight(Float.valueOf(heightTextField.getText()));
			}
		});
	}
}
