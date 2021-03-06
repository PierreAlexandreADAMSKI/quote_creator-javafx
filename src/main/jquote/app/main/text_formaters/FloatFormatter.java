package app.main.text_formaters;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * app.view.main.widgets.form_box.text_formaters Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public class FloatFormatter extends TextFormatter {

	private static final UnaryOperator<Change> filter = change -> {
		final String text = change.getText();
		if (text.matches("[0-9]*[.]?[0-9]*$")) {
			return change;
		}
		change.setText("");
		return null;
	};

	public FloatFormatter(){
		super(filter);
	}

}
