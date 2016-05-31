package app.main.adapters;

import javafx.beans.property.*;

/**
 * app.main.adapters Created by Pierre-Alexandre Adamski on 22/05/16.
 */
public class AreaListCellAdapter {
	private StringProperty stamp;
	private StringProperty type;
	private FloatProperty area;
	private StringProperty formula;


	public AreaListCellAdapter() {
	}

	public AreaListCellAdapter(String stamp, Float area, String type, String formula) {
		setArea(area);
		setStamp(stamp);
		setType(type);
		setFormula(formula);
	}



	public void setStamp(String stamp) { stampProperty().set(stamp); }
	public void setType(String type) { typeProperty().set(type); }
	public void setFormula(String formula) { formulaProperty().set(formula); }
	public void setArea(Float area) { areaProperty().set(area); }

	public String getStamp() {
		return stampProperty().get();
	}
	public String getFormula() {
		return formulaProperty().get();
	}
	public String getType() {
		return typeProperty().get();
	}
	public Float getArea() { return areaProperty().get(); }

	public StringProperty stampProperty() {
		if (stamp == null) stamp = new SimpleStringProperty(this, "stamp");
		return stamp;
	}
	public StringProperty formulaProperty() {
		if (formula == null) formula = new SimpleStringProperty(this, "formula");
		return formula;
	}
	public StringProperty typeProperty() {
		if (type == null) type = new SimpleStringProperty(this, "type");
		return type;
	}
	public FloatProperty areaProperty() {
		if (area == null) area = new SimpleFloatProperty(this, "area");
		return area;
	}

	private AreaListCellAdapter(StringProperty stamp, FloatProperty area, StringProperty type) {
		this.area = area;
		this.stamp = stamp;
		this.type = type;
	}

	public AreaListCellAdapter copy(){
		return new AreaListCellAdapter(stamp, area, type);
	}
}
