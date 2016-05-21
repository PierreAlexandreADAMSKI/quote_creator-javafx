package app.main.controllers.widgets.form_box;

import app.main.adapters.RowAdapter;
import app.main.controllers.MainStageController;
import app.main.javafx.FormBox;
import app.main.services.FormService;
import app.main.javafx.ParentBox;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 04/04/2016.
 */
public class ProductFormBoxController extends FormBox {
	@FXML
	public TextField nameTextField;
	@FXML
	public TextField productTextField;
	@FXML
	public TextField sellerTextField;
	@FXML
	public TextField sizeTextField;
	@FXML
	public MenuButton unitMenuButton;
	@FXML
	public TextField priceWriteTextField;
	@FXML
	public TextField tvaTextField;


	private MenuItem unityMenuItem = new MenuItem("U");
	private MenuItem meterMenuItem = new MenuItem("m");
	private MenuItem meter2MenuItem = new MenuItem("m2");
	private MenuItem meter3MenuItem = new MenuItem("m3");
	private MenuItem setMenuItem = new MenuItem("ENS");


	public ProductFormBoxController(MainStageController controller) {
		super("ProductFormBox", controller);
		init();
	}

	private void init() {
		unitMenuButton.getItems().setAll(unityMenuItem, meterMenuItem, meter2MenuItem, meter3MenuItem, setMenuItem);
		unitMenuButton.getItems().stream().forEach(item ->
				item.setOnAction(clickEvent ->
						unitMenuButton.setText(item.getText())));
	}

	@Override
	protected void comportment(MainStageController controller) {
		super.comportment(controller);
		controller.setCurrentRowAdapter(new RowAdapter());

		/** Bindings **/
		ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
			final BooleanBinding isFilled = new BooleanBinding() {
				{
					bind(FormService.isFilled(ProductFormBoxController.this));
				}

				@Override
				protected boolean computeValue() {
					return FormService.isFilled(ProductFormBoxController.this).get();
				}
			};
			controller.addButton.disableProperty().unbind();
			controller.addButton.disableProperty().bind(Bindings.not(isFilled));
		};

		Bindings.bindBidirectional(nameTextField.textProperty(), controller.getCurrentRowAdapter().nameProperty());
		Bindings.bindBidirectional(productTextField.textProperty(), controller.getCurrentRowAdapter().productProperty());
		Bindings.bindBidirectional(sellerTextField.textProperty(), controller.getCurrentRowAdapter().sellerProperty());
		Bindings.bindBidirectional(sizeTextField.textProperty(), controller.getCurrentRowAdapter().sizeProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(unitMenuButton.textProperty(), controller.getCurrentRowAdapter().unitProperty());
		Bindings.bindBidirectional(priceWriteTextField.textProperty(), controller.getCurrentRowAdapter().priceWriteProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(tvaTextField.textProperty(), controller.getCurrentRowAdapter().tvaProperty(), new NumberStringConverter());

		/** Listeners **/

		nameTextField.textProperty().addListener(changeListener);
		productTextField.textProperty().addListener(changeListener);
		sellerTextField.textProperty().addListener(changeListener);
		sizeTextField.textProperty().addListener(changeListener);
		unitMenuButton.textProperty().addListener(changeListener);
		priceWriteTextField.textProperty().addListener(changeListener);
		tvaTextField.textProperty().addListener(changeListener);

	}

	@Override
	public String toString() {
		return "ProductFormBoxController{" +
				", nameTextField=" + nameTextField +
				", productTextField=" + productTextField +
				", sellerTextField=" + sellerTextField +
				", sizeTextField=" + sizeTextField +
				", unitMenuButton=" + unitMenuButton +
				", priceWriteTextField=" + priceWriteTextField +
				", tvaTextField=" + tvaTextField +
				", unityMenuItem=" + unityMenuItem +
				", meterMenuItem=" + meterMenuItem +
				", meter2MenuItem=" + meter2MenuItem +
				", meter3MenuItem=" + meter3MenuItem +
				", setMenuItem=" + setMenuItem +
				'}';
	}
}
