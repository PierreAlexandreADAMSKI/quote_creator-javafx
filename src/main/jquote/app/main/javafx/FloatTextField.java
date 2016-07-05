package app.main.javafx;

import app.main.text_formaters.FloatFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * app.view.main.javafx Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public class FloatTextField extends TextField{

	public FloatTextField() {
		this.setTextFormatter(new FloatFormatter());
		this.textProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue.startsWith("."))
				FloatTextField.this.textProperty().setValue("0" + newValue);
			if (oldValue.endsWith("."))
				FloatTextField.this.textProperty().setValue(newValue + "0");
		});
	}

	@Override
	public String getText(int start, int end) {
		String text = super.getText(start, end);
		if (text.startsWith("."))
			text = "0" + text;
		if (text.endsWith("."))
			text = text + "0";
		return text;
	}


}
