package app.main.controllers.widgets.dialog;

		import app.main.javafx.impl.Dialog;
		import javafx.fxml.FXML;
		import javafx.scene.control.Button;
		import javafx.scene.control.Label;

/**
 * app.main.controllers.widgets.AlertDialog Created by Pierre-Alexandre Adamski on 30/05/16.
 */
public class AlertDialog extends Dialog {
	@FXML
	private Label messageLabel;
	@FXML
	private Label detailsLabel;
	@FXML
	public Button okButton;

	public AlertDialog(String messageLabel, String detailsLabel) {
		super("AlertDialog");
		this.detailsLabel.setText(detailsLabel);
		this.messageLabel.setText(messageLabel);

		//okButton.setOnAction(event -> );
	}


}
