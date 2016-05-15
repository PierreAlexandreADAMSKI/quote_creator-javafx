package app.view.main.widgets.form_box.quantities.polygones.circle;

import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.triangle.TriangleFormBox;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Circle implements Polygon {
	private Float radius;

	public Circle(Float radius) {
		this.radius = radius;
	}
	public Circle() {}

	public void setRadius(Float radius) {
		this.radius = radius;
	}

	public Float getRadius() {
		return radius;
	}

	@Override
	public Float getArea() {
		return (float) (2.0 * Math.PI * Math.pow((double)radius, 2));
	}
}
