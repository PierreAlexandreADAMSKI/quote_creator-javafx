package app.main.javafx;

import app.main.javafx.impl.Controller;
import app.main.javafx.impl.ParentBox;
import javafx.scene.control.Button;

/**
 * app.main.javafx Created by Pierre-Alexandre Adamski on 21/05/16.
 */
public class FormBox<T extends Controller> extends ParentBox<T> {

	private T controller;
	private Button button;

	public FormBox() { super(); }

	public FormBox(String fxmlName, T controller) {
		super(fxmlName, controller);
	}

	public T getController() {
		return controller;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	@Override
	public void comportment(T controller) {
		this.controller = controller;
	}
}
