package app.main.javafx;

import app.main.text_formaters.FloatFormatter;
import javafx.scene.control.TextField;

/**
 * app.view.main.javafx Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public class FloatTextField extends TextField{

	public FloatTextField() {
		this.setTextFormatter(new FloatFormatter());
		//this.setOnKeyReleased();
	}
}
