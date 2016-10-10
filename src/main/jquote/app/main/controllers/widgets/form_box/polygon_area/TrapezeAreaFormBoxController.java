package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.javafx_impl.FloatTextField;
import app.main.javafx_impl.FormBox;
import app.main.adapters.Polygon;
import app.main.javafx_impl.impl.PolygonAreaBox;
import app.main.services.ForButtonListener;
import javafx.fxml.FXML;
import app.main.adapters.Trapeze;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TrapezeAreaFormBoxController extends FormBox<MeterSquareQuantityFormBoxController> implements PolygonAreaBox {
	@FXML
	FloatTextField linearGroundTextField;
	@FXML
	FloatTextField linearRoofTextField;
	@FXML
	FloatTextField heightTextField;

	private Trapeze polygon;

	public TrapezeAreaFormBoxController(MeterSquareQuantityFormBoxController controller) {
		super("TrapezeAreaFormBox", controller);
		polygon = new Trapeze();
	}

	@Override
	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	public void comportment(MeterSquareQuantityFormBoxController controller) {
		this.setButton(controller.addSurfaceButton);
		final ForButtonListener changeListener = new ForButtonListener(this);
		/** Bindings **/
		linearGroundTextField.textProperty().addListener(changeListener);
		linearRoofTextField.textProperty().addListener(changeListener);
		heightTextField.textProperty().addListener(changeListener);

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
