package app.main.controllers.widgets.form_box;

import app.main.controllers.MainStageController;
import app.main.controllers.widgets.form_box.polygon_area.*;
import app.main.widgets_objects.interfaces.FormBox;
import app.main.widgets_objects.interfaces.PolygonAreaBox;
import app.main.javafx.Box;
import app.main.widgets_objects.interfaces.Polygon;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * app.view.main.widgets Created by Pierre-Alexandre Adamski on 14/04/2016.
 */
public class MeterSquareQuantityFormBoxController extends Box implements FormBox {
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

	private MainStageController controller;

	public MeterSquareQuantityFormBoxController(MainStageController controller) {
		super("MeterSquareQuantityFormBox");
		this.controller = controller;
	}

	private void addArea(Polygon polygon) {
		Float area = polygon.getArea();
		surfaceListView.getItems().add(area);
		this.controller.getClickedRowAdapter().setQuantity(this.controller.getClickedRowAdapter().getQuantity() + area);
	}

	private void delArear(int index) {
		Float area = surfaceListView.getItems().get(index);
		surfaceListView.getItems().remove(index);
		this.controller.getClickedRowAdapter().setQuantity(this.controller.getClickedRowAdapter().getQuantity() - area);
	}

	@Override
	protected void comportment() {
		addSurfaceButton.setOnAction(event -> {
			controller.addButton.setDisable(false);
			if (polygonBox.getChildren().get(0) instanceof PolygonAreaBox) {
				this.polygon = ((PolygonAreaBox) polygonBox.getChildren().get(0)).getPolygon();
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
			if (!surfaceListView.getItems().isEmpty() && surfaceListView.getItems().size() > 0) {
				final int index = surfaceListView.getSelectionModel().getSelectedIndex();
				delArear(index);
			}
		});
		triangleItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new TriangleAreaFormBoxController());
			menuButton.setText(triangleItem.getText());
		});
		rectangleItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new RectangleAreaFormBoxController());
			menuButton.setText(rectangleItem.getText());

		});
		trapezeItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new TrapezeAreaFormBoxController());
			menuButton.setText(trapezeItem.getText());
		});
		circleItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new CircleAreaFormBoxController());
			menuButton.setText(circleItem.getText());
		});
		arcItem.setOnAction(event -> {
			polygonBox.getChildren().clear();
			polygonBox.getChildren().add(new ArcAreaFormBoxController());
			menuButton.setText(arcItem.getText());
		});
	}
}

