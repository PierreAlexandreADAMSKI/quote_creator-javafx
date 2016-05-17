package jquote.app.main.widgets_objects.polygons;

import jquote.app.main.widgets_objects.impl.Polygon;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Rectangle implements Polygon {
	private Float width;
	private Float height;

	public Rectangle(Float height, Float width) {
		this.height = height;
		this.width = width;
	}
	public Rectangle() {}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	@Override
	public Float getArea() {
		return width * height;
	}
}
