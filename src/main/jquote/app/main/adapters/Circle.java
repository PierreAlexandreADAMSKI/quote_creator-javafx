package app.main.adapters;

import app.main.adapters.Polygon;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Circle implements Polygon {
	private Float radius;
	private Type type = Type.CIRCLE;

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
	public String getType() {
		return type.toString();
	}

	@Override
	public Float getArea() {
		return (float) (Math.PI * Math.pow((double)radius, 2));
	}

	@Override
	public String getFormula() {
		return "π * "+Math.pow((double)radius, 2);
	}
}
