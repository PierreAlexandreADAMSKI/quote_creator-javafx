package app.main.adapters;

import app.main.adapters.Polygon;
import javafx.beans.property.FloatProperty;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Triangle implements Polygon {

	private Float base;
	private Float height;
	private Type type = Type.TRIANGLE;

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
	public String getType() {
		return type.toString();
	}

	@Override
	public Float getArea() {
		return base * height / 2;
	}

	@Override
	public String getFormula() {
		return base+" * "+height+" / 2";
	}
}
