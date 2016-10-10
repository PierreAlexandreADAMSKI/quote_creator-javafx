package app.main.services;

import app.main.javafx_impl.FormBox;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * app.main.services Created by Pierre-Alexandre Adamski on 23/05/16.
 */
public class ForButtonListener implements ChangeListener<String> {
	final FormBox INSTANCE;

	public ForButtonListener(FormBox formBox) {
		INSTANCE = formBox;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		final BooleanBinding isFilled = new BooleanBinding() {
			{
				bind(FormService.isFilled(INSTANCE));
			}

			@Override
			protected boolean computeValue() {
				return FormService.isFilled(INSTANCE).get();
			}
		};
		INSTANCE.getButton().disableProperty().unbind();
		INSTANCE.getButton().disableProperty().bind(Bindings.not(isFilled));
	}
}
