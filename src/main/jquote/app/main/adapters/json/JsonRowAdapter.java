package app.main.adapters.json;

import app.main.adapters.TableRowAdapter;

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

	public JsonRowAdapter(TableRowAdapter tableRowAdapter) {
		this.name = tableRowAdapter.getName();
		this.priceGen = tableRowAdapter.getPriceGen();
		this.priceWrite = tableRowAdapter.getPriceWrite();
		this.product = tableRowAdapter.getProduct();
		this.quantity = tableRowAdapter.getQuantity();
		this.sel = tableRowAdapter.getSel();
		this.seller = tableRowAdapter.getSeller();
		this.size = tableRowAdapter.getSize();
		this.tva = tableRowAdapter.getTva();
		this.unit = tableRowAdapter.getUnit();
	}

	public String getName() {
		return name;
	}

	public Float getPriceGen() {
		return priceWrite + (priceWrite * tva / 100f);
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
