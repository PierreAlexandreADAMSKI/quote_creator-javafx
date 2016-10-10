package app.main.services;


import app.main.controllers.MainStageController;
import app.main.controllers.widgets.form_box.ProductFormBoxController;
import app.main.javafx_impl.impl.Controller;
import javafx.scene.input.KeyEvent;

/**
 * app.view.main.events Created by Pierre-Alexandre Adamski on 06/04/2016.
 */
public interface KeyEventService {
	static void shortCuts(KeyEvent event, Controller controller) {
		if (event.isControlDown()) {
			switch (event.getCode()) {
				case N: {
					if (controller instanceof MainStageController)
						((MainStageController) controller).formScrollPane.setContent(new ProductFormBoxController((MainStageController) controller));
					break;
				}
				//UPGRADEME :: (win) <ControlDown> || (mac) <CommandDown> Key-shortcuts at least : and found a better system
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
					if (controller instanceof MainStageController)
						((MainStageController) controller).onDelButtonAction();
					break;
				}
			}
		}
	}
}
