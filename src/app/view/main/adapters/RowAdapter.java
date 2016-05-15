package app.view.main.adapters;

import javafx.beans.property.*;

/**
 * app.quore Created by Pierre-Alexandre Adamski on 01/04/2016.
 */
public class RowAdapter {
	private StringProperty name;
	private BooleanProperty sel;
	private StringProperty product;
	private StringProperty seller;
	private FloatProperty size;
	private StringProperty unit;
	private FloatProperty priceWrite;
	private FloatProperty tva;
	private FloatProperty priceGen;
	private FloatProperty quantity;


	public RowAdapter() {
		setName("untitled");
		setProduct("empty");
		setSeller("empty");
		setSize(0f);
		setUnit("empty");
		setTva(0f);
		setPriceWrite(0f);
		setPriceGen(0f);
		setSel(false);
		setQuantity(0f);
	}

	public RowAdapter(String name, String product, String seller, Float size, String unit, Float tva, Float priceWrite) {
		setName(name);
		setProduct(product);
		setSeller(seller);
		setSize(size);
		setUnit(unit);
		setTva(tva);
		setPriceWrite(priceWrite);
		setPriceGen(priceWrite + (priceWrite * tva / 100f));
		setSel(false);
		setQuantity(0f);
	}

	public RowAdapter(JsonRowAdapter jsonRowAdapter) {
		setName(jsonRowAdapter.getName());
		setProduct(jsonRowAdapter.getProduct());
		setSeller(jsonRowAdapter.getSeller());
		setSize(jsonRowAdapter.getSize());
		setUnit(jsonRowAdapter.getUnit());
		setTva(jsonRowAdapter.getTva());
		setPriceWrite(jsonRowAdapter.getPriceWrite());
		setPriceGen(jsonRowAdapter.getPriceGen());
		setSel(jsonRowAdapter.getSel());
		setQuantity(jsonRowAdapter.getQuantity());
	}

	public void setName(String name) {
		nameProperty().set(name);
	}

	public void setPriceGen(Float priceGen) {
		priceGenProperty().set(priceGen);
	}

	public void setPriceWrite(Float priceWrite) {
		priceWriteProperty().set(priceWrite);
	}

	public void setProduct(String product) {
		productProperty().set(product);
	}

	public void setSel(boolean sel) {
		selProperty().set(sel);}

	public void setSeller(String seller) {
		sellerProperty().set(seller);
	}

	public void setSize(Float size) {
		sizeProperty().set(size);
	}

	public void setQuantity(Float quantity) {
		quantityProperty().set(quantity);
	}

	public void setUnit(String unit) {
		unitProperty().set(unit);
	}

	public void setTva(Float tva) {
		tvaProperty().set(tva);
	}

	/*public void setQuantity(Float quantity) {
		quantityProperty().set(quantity);
	}*/

	public String getName() {
		return nameProperty().get();
	}

	public Float getPriceGen() {
		return priceGenProperty().get();
	}

	public Float getPriceWrite() {
		return priceWriteProperty().get();
	}

	public String getProduct() {
		return productProperty().get();
	}

	public boolean getSel() {
		return selProperty().get();
	}

	public String getSeller() {
		return sellerProperty().get();
	}

	public Float getSize() {
		return sizeProperty().get();
	}

	public String getUnit() { return unitProperty().get(); }

	public Float getQuantity() {
		return quantityProperty().get();
	}

	public Float getTva() {
		return tvaProperty().get();
	}

	public StringProperty nameProperty() {
		if (name == null) name = new SimpleStringProperty(this, "name");
		return name;
	}

	public BooleanProperty selProperty() {
		if (sel == null) sel = new SimpleBooleanProperty(this, "sel");
		return sel;
	}

	public StringProperty productProperty() {
		if (product == null) product = new SimpleStringProperty(this, "product");
		return product;
	}

	public StringProperty sellerProperty() {
		if (seller == null) seller = new SimpleStringProperty(this, "seller");
		return seller;
	}

	public FloatProperty sizeProperty() {
		if (size == null) size = new SimpleFloatProperty(this, "size");
		return size;
	}

	private StringProperty unitProperty() {
		if (unit == null) unit = new SimpleStringProperty(this, "unit");
		return unit;
	}

	public FloatProperty priceWriteProperty() {
		if (priceWrite == null) priceWrite = new SimpleFloatProperty(this, "priceWrite");
		return priceWrite;
	}

	public FloatProperty tvaProperty() {
		if (tva == null) tva = new SimpleFloatProperty(this, "tva");
		return tva;
	}

	public FloatProperty priceGenProperty() {
		if (priceGen == null) priceGen = new SimpleFloatProperty(this, "priceGen");
		return priceGen;
	}

	public FloatProperty quantityProperty() {
		if (quantity == null) quantity = new SimpleFloatProperty(this, "quantity");
		return quantity;
	}
}
