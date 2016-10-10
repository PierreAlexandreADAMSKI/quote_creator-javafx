package app.main.javafx_impl.impl;

import app.main.services.AppUtil;
import javafx.scene.layout.VBox;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public abstract class Box extends VBox {

	public Box() { super(); }

	public Box(String fxmlName) {
		AppUtil.showWidget(this, fxmlName);
	}
}
