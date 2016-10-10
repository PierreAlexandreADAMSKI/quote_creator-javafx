package app.main.controllers.widgets.form_box;

import app.main.adapters.TableRowAdapter;
import app.main.controllers.MainStageController;
import app.main.javafx_impl.FloatTextField;
import app.main.javafx_impl.FormBox;
import app.main.services.FormService;
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
public class ProductFormBoxController extends FormBox<MainStageController> {
	@FXML
	public TextField nameTextField;
	@FXML
	public TextField productTextField;
	@FXML
	public TextField sellerTextField;
	@FXML
	public FloatTextField sizeTextField;
	@FXML
	public MenuButton unitMenuButton;
	@FXML
	public FloatTextField priceWriteTextField;
	@FXML
	public FloatTextField tvaTextField;


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
	public void comportment(MainStageController controller) {
		super.comportment(controller);

		if (controller.addButton.disableProperty().isBound())
			controller.addButton.disableProperty().unbind();

		controller.addButton.setDisable(true);

		controller.setCurrentTableRowAdapter(new TableRowAdapter());

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

		Bindings.bindBidirectional(nameTextField.textProperty(), controller.getCurrentTableRowAdapter().nameProperty());
		Bindings.bindBidirectional(productTextField.textProperty(), controller.getCurrentTableRowAdapter().productProperty());
		Bindings.bindBidirectional(sellerTextField.textProperty(), controller.getCurrentTableRowAdapter().sellerProperty());
		Bindings.bindBidirectional(sizeTextField.textProperty(), controller.getCurrentTableRowAdapter().sizeProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(unitMenuButton.textProperty(), controller.getCurrentTableRowAdapter().unitProperty());
		Bindings.bindBidirectional(priceWriteTextField.textProperty(), controller.getCurrentTableRowAdapter().priceWriteProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(tvaTextField.textProperty(), controller.getCurrentTableRowAdapter().tvaProperty(), new NumberStringConverter());

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
