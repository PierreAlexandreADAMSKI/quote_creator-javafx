package app.main.javafx;

import app.main.controllers.MainStageController;
import app.main.javafx.Box;

/**
 * app.main.controllers.widgets.form_box Created by Pierre-Alexandre Adamski on 19/05/16.
 */
public class ParentBox extends Box{
	protected MainStageController controller;
	public ParentBox(String fxmlName, MainStageController controller) {
		super(fxmlName);
		comportment(controller);
	}
	@Override
	protected void comportment(MainStageController controller) {
		this.controller = controller;
	}

}
