package app.main.adapters;

/**
 * app.view.main.adapters Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public class JsonRowAdapter {
	private String name;
	private Boolean sel;
	private String product;
	private String seller;
	private Float size;
	private String unit;
	private Float priceWrite;
	private Float tva;
	private Float priceGen;
	private Float quantity;

	public JsonRowAdapter(RowAdapter rowAdapter) {
		this.name = rowAdapter.getName();
		this.priceGen = rowAdapter.getPriceGen();
		this.priceWrite = rowAdapter.getPriceWrite();
		this.product = rowAdapter.getProduct();
		this.quantity = rowAdapter.getQuantity();
		this.sel = rowAdapter.getSel();
		this.seller = rowAdapter.getSeller();
		this.size = rowAdapter.getSize();
		this.tva = rowAdapter.getTva();
		this.unit = rowAdapter.getUnit();
	}

	public String getName() {
		return name;
	}

	public Float getPriceGen() {
		return priceGen;
	}

	public Float getPriceWrite() {
		return priceWrite;
	}

	public String getProduct() {
		return product;
	}

	public Float getQuantity() {
		return quantity;
	}

	public Boolean getSel() {
		return sel;
	}

	public String getSeller() {
		return seller;
	}

	public Float getSize() {
		return size;
	}

	public Float getTva() {
		return tva;
	}

	public String getUnit() {
		return unit;
	}
}
