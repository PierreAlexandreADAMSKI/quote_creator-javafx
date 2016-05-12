package app.view.main.widgets.form_box.quantities;

import app.view.main.MainController;
import app.view.main.widgets.Box;
import app.view.main.widgets.form_box.quantities.polygones.Polygon;
import app.view.main.widgets.form_box.quantities.polygones.arc.ArcFormBox;
import app.view.main.widgets.form_box.quantities.polygones.circle.CircleFormBox;
import app.view.main.widgets.form_box.quantities.polygones.rectangle.RectangleFormBox;
import app.view.main.widgets.form_box.quantities.polygones.trapeze.TrapezeFormBox;
import app.view.main.widgets.form_box.quantities.polygones.triangle.TriangleFormBox;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class MeterSquareQuantityBox extends Box {
	private Polygon polygon;
	@FXML
	public MenuButton menuButton;
	@FXML
	public MenuItem triangleItem;
	@FXML
	public MenuItem rectangleItem;
	@FXML
	public MenuItem trapezeItem;
	@FXML
	public MenuItem circleItem;
	@FXML
	public MenuItem arcItem;
	@FXML
	public HBox polygonBox;
	@FXML
	public Button addSurfaceButton;
	@FXML
	public Button delSurfaceButton;
	@FXML
	public ListView<Float> surfaceListView;

	private MainController controller;

	public MeterSquareQuantityBox(MainController controller) {
		super("MeterSquareQuantityFormBox", controller);
		this.controller = controller;
		initForForm(addSurfaceButton);

		addSurfaceButton.setOnAction(event -> {
			controller.addButton.setDisable(false);
			if (polygonBox.getChildren().get(0) instanceof TriangleFormBox) {
				this.polygon = ((TriangleFormBox) polygonBox.getChildren().get(0)).polygon;
			}
			if (this.polygon != null) {
				addArea(this.polygon);
			}
			if (surfaceListView.getItems().isEmpty() && surfaceListView.getItems().size() == 0)
				controller.addButton.setDisable(true);
			else controller.addButton.setDisable(false);
		});
		delSurfaceButton.setOnAction(event -> {
			if (surfaceListView.getItems().isEmpty() && surfaceListView.getItems().size() == 0)
				controller.addButton.setDisable(true);
			else controller.addButton.setDisable(false);
			if (!surfaceListView.getItems().isEmpty()) {
				final int index = surfaceListView.getSelectionModel().getSelectedIndex();
				delArear(index);
			}
		});
		triangleItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new TriangleFormBox(controller));
			menuButton.setText(triangleItem.getText());
		});
		rectangleItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new RectangleFormBox(controller));
			menuButton.setText(rectangleItem.getText());

		});
		trapezeItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new TrapezeFormBox(controller));
			menuButton.setText(trapezeItem.getText());
		});
		circleItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new CircleFormBox(controller));
			menuButton.setText(circleItem.getText());
		});
		arcItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new ArcFormBox(controller));
			menuButton.setText(arcItem.getText());
		});
	}

	private void addArea(Polygon polygon) {
		Float area = polygon.getArea();
		surfaceListView.getItems().add(area);
		this.controller.currentRowAdapter().setQuantity(this.controller.currentRowAdapter().getQuantity() + area);
	}

	private void delArear(int index) {
		Float area = surfaceListView.getItems().get(index);
		surfaceListView.getItems().remove(index);
		this.controller.currentRowAdapter().setQuantity(this.controller.currentRowAdapter().getQuantity() - area);
	}
}

