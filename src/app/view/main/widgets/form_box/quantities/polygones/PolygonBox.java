package app.view.main.widgets.form_box.quantities.polygones;

import app.view.main.MainController;
import app.view.main.widgets.Box;

import java.util.regex.Pattern;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public abstract class PolygonBox extends Box {
	public PolygonBox(String fxmlName, MainController controller) {
		super(fxmlName, controller);
		comportment();
	}

	public abstract Polygon getPolygon();
	protected abstract void comportment();
}
