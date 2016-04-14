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
	private ObjectProperty<Number> sizeSquare;
	private ObjectProperty<Number> priceWrite;
	private ObjectProperty<Number> tva;
	private ObjectProperty<Number> priceGen;


	public RowAdapter() {
		setName("untitled");
		setProduct(null);
		setSeller(null);
		setSizeSquare(null);
		setTva(null);
		setPriceWrite(null);
		setPriceGen(null);
		setSel(true);
	}

	public RowAdapter(String name,String product, String seller, Number sizeSquare, Number tva, Number priceWrite) {
		setName(name);
		setProduct(product);
		setSeller(seller);
		setSizeSquare(sizeSquare);
		setTva(tva);
		setPriceWrite(priceWrite);
		setPriceGen(priceWrite.floatValue() + (priceWrite.floatValue() * tva.floatValue() / 100.f));
		setSel(true);
	}

	public void setName(String name) {
		nameProperty().set(name);
	}

	public void setPriceGen(Number priceGen) {
		priceGenProperty().set(priceGen);
	}

	public void setPriceWrite(Number priceWrite) {
		priceWriteProperty().set(priceWrite);
	}

	public void setProduct(String product) {
		productProperty().set(product);
	}

	public void setSel(boolean sel) {
		selProperty().set(sel);
	}

	public void setSeller(String seller) {
		sellerProperty().set(seller);
	}

	public void setSizeSquare(Number sizeSquare) {
		sizeSquareProperty().set(sizeSquare);
	}

	public void setTva(Number tva) {
		tvaProperty().set(tva);
	}

	public String getName() {
		return nameProperty().get();
	}

	public Number getPriceGen() {
		return priceGenProperty().get();
	}

	public Number getPriceWrite() {
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

	public Number getSizeSquare() {
		return sizeSquareProperty().get();
	}

	public Number getTva() {
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

	public ObjectProperty<Number> sizeSquareProperty() {
		if (sizeSquare == null) sizeSquare = new SimpleObjectProperty<>(this, "size");
		return sizeSquare;
	}

	public ObjectProperty<Number> priceWriteProperty() {
		if (priceWrite == null) priceWrite = new SimpleObjectProperty<>(this, "priceWrite");
		return priceWrite;
	}

	public ObjectProperty<Number> tvaProperty() {
		if (tva == null) tva = new SimpleObjectProperty<>(this, "tva");
		return tva;
	}

	public ObjectProperty<Number> priceGenProperty() {
		if (priceGen == null) priceGen = new SimpleObjectProperty<>(this, "priceGen");
		return priceGen;
	}
}
