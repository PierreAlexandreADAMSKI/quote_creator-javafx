package app.main.services;


import app.main.controllers.MainStageController;
import app.main.controllers.widgets.form_box.ProductFormBoxController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * app.view.main.events Created by Pierre-Alexandre Adamski on 06/04/2016.
 */
public interface KeyEventService {
	static void shortCuts(KeyEvent event, MainStageController controller) {
		if (event.isControlDown()) {
			switch (event.getCode()) {
				case N: {
					controller.formScrollPane.setContent(new ProductFormBoxController());
					break;
				}
				//TODO :: ControlDown Key shortcut at least :
				case D : //duplicate
				case C : //copy
				case V : //paste
				case F : //fullscreen
				case E : //edit tableView -> next step after PDF export
					/** ... **/
				default:
					break;
			}
		} else {
			switch (event.getCode()) {
				case BACK_SPACE: {
					controller.onDelButtonAction();
					break;
				}
			}
		}
	}
}
