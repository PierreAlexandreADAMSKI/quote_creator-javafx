package jquote.app.main.controllers.widgets.form_box.polygon_area;

import jquote.app.main.controllers.MainStageController;
import jquote.app.main.widgets_objects.impl.Polygon;
import jquote.app.main.javafx.Box;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public abstract class PolygonAreaBox extends Box {
	public PolygonAreaBox(String fxmlName, MainStageController controller) {
		super("form_box/polygon_area/", fxmlName, controller);
		comportment();
	}

	public abstract Polygon getPolygon();
	protected abstract void comportment();
}
