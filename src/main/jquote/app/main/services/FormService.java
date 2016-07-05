package app.main.services;

import app.main.javafx.FloatTextField;
import app.main.javafx.FormBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


/**
 * app.main.services Created by Pierre-Alexandre Adamski on 19/05/16.
 */
public interface FormService {

	static <T extends FormBox> BooleanProperty isFilled(T formBox) {
		BooleanProperty isFilledProperty = new SimpleBooleanProperty(formBox, "isFilledProperty", false);
		isFilledProperty.setValue(!(formBox == null || formBox.getChildren().isEmpty()) && recursiveCheck(formBox.getChildren()));
		return isFilledProperty;
	}

	static boolean recursiveCheck(ObservableList<Node> children) {
		for (Node child : children) {
			if (child instanceof HBox && !recursiveCheck(((Parent) child).getChildrenUnmodifiable()))
				return false;
			if (child instanceof TextField) {
				String text = ((TextField) child).getText();
				if(text == null)
					return false;
				if (text.isEmpty() || text.equals(""))
					return false;
				if (child instanceof FloatTextField && Float.valueOf(text) == 0f)
					return false;
			}
			if (child instanceof MenuButton) {
				String text = ((MenuButton) child).getText();
				if(text.isEmpty() || text.equals("..."))
					return false;
			}
			if (child instanceof ListView && ((ListView) child).getItems().isEmpty())
				return false;
		}
		return true;
	}
}
