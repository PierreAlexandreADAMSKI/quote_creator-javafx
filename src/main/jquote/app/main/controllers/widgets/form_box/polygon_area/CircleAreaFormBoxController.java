package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.javafx_impl.FloatTextField;
import app.main.javafx_impl.FormBox;
import app.main.adapters.Polygon;
import app.main.javafx_impl.impl.PolygonAreaBox;
import app.main.services.ForButtonListener;
import javafx.fxml.FXML;
import app.main.adapters.Circle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class CircleAreaFormBoxController extends FormBox<MeterSquareQuantityFormBoxController> implements PolygonAreaBox {
	@FXML
	private FloatTextField radiusTextField;

	private Circle polygon;

	public CircleAreaFormBoxController(MeterSquareQuantityFormBoxController controller) {
		super("CircleAreaFormBox", controller);
		polygon = new Circle();
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
		radiusTextField.textProperty().addListener(changeListener);

		radiusTextField.setOnKeyReleased(event -> {
			if (!radiusTextField.getText().equals("") && radiusTextField != null){
				polygon.setRadius(Float.valueOf(radiusTextField.getText()));
			}
		});
	}
}
