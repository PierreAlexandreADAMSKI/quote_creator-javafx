package app.main.widgets_objects.polygons;

import app.main.widgets_objects.interfaces.Polygon;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Trapeze implements Polygon {
	private Float bottomBase;
	private Float topBase;
	private Float height;

	public Trapeze(Float bottomBase, Float height, Float topBase) {
		this.bottomBase = bottomBase;
		this.height = height;
		this.topBase = topBase;
	}

	public Trapeze() {}

	public Float getBottomBase() {
		return bottomBase;
	}

	public void setBottomBase(Float bottomBase) {
		this.bottomBase = bottomBase;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getTopBase() {
		return topBase;
	}

	public void setTopBase(Float topBase) {
		this.topBase = topBase;
	}

	@Override
	public Float getArea() {
		return ((bottomBase + topBase) * height)/2.f;
	}
}
