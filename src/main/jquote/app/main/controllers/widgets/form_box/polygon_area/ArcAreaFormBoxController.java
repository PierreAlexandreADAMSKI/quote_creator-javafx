package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.javafx_impl.FloatTextField;
import app.main.javafx_impl.FormBox;
import app.main.adapters.Polygon;
import app.main.javafx_impl.impl.PolygonAreaBox;
import app.main.services.ForButtonListener;
import javafx.fxml.FXML;
import app.main.adapters.Arc;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class ArcAreaFormBoxController extends FormBox<MeterSquareQuantityFormBoxController> implements PolygonAreaBox {
	@FXML
	private FloatTextField radiusTextField;
	@FXML
	private FloatTextField angleTextField;

	private Arc polygon;

	public ArcAreaFormBoxController(MeterSquareQuantityFormBoxController controller) {
		super("ArcAreaFormBox", controller);
		polygon = new Arc();
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
		angleTextField.textProperty().addListener(changeListener);

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
