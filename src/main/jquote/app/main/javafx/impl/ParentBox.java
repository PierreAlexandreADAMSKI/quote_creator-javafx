package app.main.javafx.impl;

/**
 * app.main.controllers.widgets.form_box Created by Pierre-Alexandre Adamski on 19/05/16.
 */
public abstract class ParentBox<T extends Controller> extends Box implements ParentController{

	public ParentBox() { super(); }

	public ParentBox(String fxmlName, T controller) {
		super(fxmlName);
		comportment(controller);
	}

	public abstract void comportment(T controller);
}
