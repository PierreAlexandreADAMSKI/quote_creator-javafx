package app.main.adapters;

import app.main.adapters.Polygon;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Arc implements Polygon {
	private Float radius = 360.f;
	private Float angle;
	private Type type = Type.ARC;

	public Arc(Float angle, Float radius) {
		this.angle = angle;
		this.radius = radius;
	}

	public Arc() {}

	public Float getAngle() {
		return angle;
	}

	public void setAngle(Float angle) {
		this.angle = angle;
	}

	public Float getRadius() {
		return radius;
	}

	public void setRadius(Float radius) {
		this.radius = radius;
	}

	@Override
	public String getType() {
		return type.toString();
	}

	@Override
	public Float getArea() {
		return (float) (Math.toRadians((double) angle) * Math.pow((double) radius, 2)) / 2.f;
	}

	@Override
	public String getFormula() {
		return "("+Math.toRadians((double) angle)+" * π * "+radius+"^2) / 2";
	}
}
