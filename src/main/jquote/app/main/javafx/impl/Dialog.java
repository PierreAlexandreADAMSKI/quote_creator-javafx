package app.main.javafx.impl;

import app.main.services.AppUtil;
import javafx.scene.layout.GridPane;

/**
 * app.main.javafx.impl Created by Pierre-Alexandre Adamski on 30/05/16.
 */
public abstract class Dialog extends GridPane{
	public Dialog(String dialogType) {
		AppUtil.showWidget(this, dialogType);
	}
}
