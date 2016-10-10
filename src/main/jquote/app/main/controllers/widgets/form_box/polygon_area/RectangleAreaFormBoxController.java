package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.javafx_impl.FloatTextField;
import app.main.javafx_impl.FormBox;
import app.main.adapters.Polygon;
import app.main.javafx_impl.impl.PolygonAreaBox;
import app.main.services.ForButtonListener;
import javafx.fxml.FXML;
import app.main.adapters.Rectangle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class RectangleAreaFormBoxController extends FormBox<MeterSquareQuantityFormBoxController> implements PolygonAreaBox {
	@FXML
	FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Rectangle polygon;
	public RectangleAreaFormBoxController(MeterSquareQuantityFormBoxController controller) {
		super("RectangleAreaFormBox", controller);
		polygon = new Rectangle();
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
		linearTextField.textProperty().addListener(changeListener);
		heightTextField.textProperty().addListener(changeListener);

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
