package app.main.javafx;

import app.main.controllers.MainStageController;

/**
 * app.main.javafx Created by Pierre-Alexandre Adamski on 21/05/16.
 */
public class FormBox extends ParentBox {
	public FormBox(String fxmlName, MainStageController controller) {
		super(fxmlName, controller);
	}

	@Override
	protected void comportment(MainStageController controller) {
		super.comportment(controller);
		super.controller.addButton.setDisable(true);
	}
}
