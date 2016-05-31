package app.main.controllers.widgets.form_box.polygon_area;

import app.main.controllers.widgets.form_box.MeterSquareQuantityFormBoxController;
import app.main.javafx.*;
import app.main.services.ForButtonListener;
import app.main.adapters.Polygon;
import app.main.javafx.impl.PolygonAreaBox;
import javafx.fxml.FXML;
import app.main.adapters.Triangle;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class TriangleAreaFormBoxController extends FormBox<MeterSquareQuantityFormBoxController> implements PolygonAreaBox {
	@FXML
	public FloatTextField linearTextField;
	@FXML
	public FloatTextField heightTextField;

	private Triangle polygon;

	public TriangleAreaFormBoxController(MeterSquareQuantityFormBoxController controller) {
		super("TriangleAreaFormBox", controller);
		polygon = new Triangle();

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


		//TODO bind FIXME bind
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
