package jquote.app.main.widgets_objects.polygons;

import jquote.app.main.widgets_objects.impl.Polygon;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Triangle implements Polygon {
	private Float base;
	private Float height;

	public Triangle(Float base, Float height) {
		this.base = base;
		this.height = height;
	}

	public Triangle() {}

	public Float getBase() {
		return base;
	}

	public void setBase(Float base) {
		this.base = base;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	@Override
	public Float getArea() {
		return this.base * this.height / 2;
	}
}
