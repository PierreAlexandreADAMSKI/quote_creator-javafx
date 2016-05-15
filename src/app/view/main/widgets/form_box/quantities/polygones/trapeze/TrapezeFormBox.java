package app.view.main.widgets.form_box.quantities.polygones.trapeze;

import app.view.main.MainController;
import app.view.main.javafx.FloatTextField;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.PolygonBox;
import javafx.fxml.FXML;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TrapezeFormBox extends PolygonBox {
	@FXML
	FloatTextField linearGroundTextField;
	@FXML
	FloatTextField linearRoofTextField;
	@FXML
	FloatTextField heightTextField;

	private Trapeze polygon;

	public TrapezeFormBox(MainController controller) {
		super("TrapezeFormBox", controller);
		polygon = new Trapeze();
	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	protected void comportment() {
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
