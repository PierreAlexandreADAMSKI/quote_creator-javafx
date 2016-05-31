package app.main.adapters;

import app.main.adapters.Polygon;
import com.sun.javafx.binding.StringFormatter;

/**
 * app.view.main.widgets.form_box.quantities.polygones Created by Pierre-Alexandre Adamski on 16/04/2016.
 */
public class Rectangle implements Polygon {
	private Float width;
	private Float height;
	private Type type = Type.RECTANGLE;

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
	public String getType() {
		return type.toString();
	}

	@Override
	public Float getArea() {
		return width * height;
	}

	@Override
	public String getFormula() {
		return "("+width.toString()+")"+" * "+"("+height.toString()+")";
	}
}
