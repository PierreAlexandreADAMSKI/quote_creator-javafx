package app.main.controllers.widgets.form_box;

import app.main.adapters.AreaListCellAdapter;
import app.main.adapters.Polygon;
import app.main.adapters.TableRowAdapter;
import app.main.controllers.MainStageController;
import app.main.javafx_impl.AreaListCell;
import app.main.javafx_impl.FormBox;
import app.main.javafx_impl.impl.PolygonAreaBox;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * app.main.controllers.widgets.form_box Created by Pierre-Alexandre Adamski on 01/06/16.
 */
public class MeterCubeQuantityFormBoxController extends FormBox<MainStageController> {
	private Polygon polygon;
	@FXML
	public TextField stampTextField;
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
	public HBox hBox;
	@FXML
	public Button addSurfaceButton;
	@FXML
	public Button subSurfaceButton;
	@FXML
	private Button delSurfaceButton;
	@FXML
	public ListView<AreaListCellAdapter> surfaceListView;

	private FormBox polygonBox;
	/**
	 * Current <=> JustAdded
	 */
	private AreaListCellAdapter currentAdapter;


	private AreaListCell doubleClickedCell;
	private int doubleClickedRowIndex;

	public MeterCubeQuantityFormBoxController(MainStageController controller) {
		super("MeterCubeQuantityFormBox", controller);
		surfaceListView.setCellFactory(factory -> new AreaListCell(/*this*/));
	}

	public AreaListCell getDoubleClickedCell() {
		return doubleClickedCell;
	}

	public void setDoubleClickedCell(AreaListCell doubleClickedCell) {
		this.doubleClickedCell = doubleClickedCell;
	}

	public int getDoubleClickedRowIndex() {
		return doubleClickedRowIndex;
	}

	public void setDoubleClickedRowIndex(int doubleClickedRowIndex) {
		this.doubleClickedRowIndex = doubleClickedRowIndex;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	private void addArea(MouseEvent event) {
		if (polygonBox instanceof PolygonAreaBox) {
			polygon = ((PolygonAreaBox) polygonBox).getPolygon();
		}
		if (polygon != null) {
			String stamp = stampTextField.getText();
			Float area = polygon.getArea();
			if (event.getSource() instanceof Button)
				if (((Button) event.getSource()).getText().equals("-"))
					area = -1.f * area;
			String type = polygon.getType();
			String formula = polygon.getFormula();
			surfaceListView.getItems().add(new AreaListCellAdapter(stamp, area, type, formula));
			final TableRowAdapter clickedTableRowAdapter = this.getController().getClickedTableRowAdapter();
			clickedTableRowAdapter.setQuantity(
					clickedTableRowAdapter.getQuantity() + area);
			clickedTableRowAdapter.setPriceGen(
					clickedTableRowAdapter.getPriceGen() * clickedTableRowAdapter.getQuantity());

		}
	}


	private void delArea(MouseEvent event) {
		if (!surfaceListView.getItems().isEmpty() && surfaceListView.getItems().size() > 0) {
			final int index = surfaceListView.getSelectionModel().getSelectedIndex();
			final AreaListCellAdapter cell = surfaceListView.getItems().get(index);
			if (cell.getArea() < 0.f)
				this.getController().getClickedTableRowAdapter().setQuantity(
						this.getController().getClickedTableRowAdapter().getQuantity() - cell.getArea());
			else
				this.getController().getClickedTableRowAdapter().setQuantity(
						this.getController().getClickedTableRowAdapter().getQuantity() - cell.getArea());
		}
	}

	@Override
	public void comportment(MainStageController controller) {
		super.comportment(controller);

		addSurfaceButton.setDisable(true);
		currentAdapter = new AreaListCellAdapter();

		/** so it's more clear **/
		final MeterCubeQuantityFormBoxController INSTANCE = this;

		addSurfaceButton.setOnMouseClicked(this::addArea);
		subSurfaceButton.setOnMouseClicked(this::addArea);
		delSurfaceButton.setOnMouseClicked(this::delArea);

		/** Bindings **/
		controller.addButton.disableProperty().unbind();
		controller.addButton.disableProperty().bind(Bindings.size(surfaceListView.getItems()).lessThan(0));
		surfaceListView.disableProperty().bind(Bindings.equal("...", menuButton.textProperty()));

		/** Listeners **/
/*
		triangleItem.setOnAction(event -> {
			polygonBox = null;
			polygonBox = new TriangleAreaFormBoxController(INSTANCE);
			updateHBox();
			menuButton.setText(triangleItem.getText());
		});
		rectangleItem.setOnAction(event -> {
			polygonBox = null;
			polygonBox = new RectangleAreaFormBoxController(INSTANCE);
			updateHBox();
			menuButton.setText(rectangleItem.getText());
		});
		trapezeItem.setOnAction(event -> {
			polygonBox = null;
			polygonBox = new TrapezeAreaFormBoxController(INSTANCE);
			updateHBox();
			menuButton.setText(trapezeItem.getText());
		});
		circleItem.setOnAction(event -> {
			polygonBox = null;
			polygonBox = new CircleAreaFormBoxController(INSTANCE);
			updateHBox();
			menuButton.setText(circleItem.getText());
		});
		arcItem.setOnAction(event -> {
			polygonBox = null;
			polygonBox = new ArcAreaFormBoxController(INSTANCE);
			updateHBox();
			menuButton.setText(arcItem.getText());
		});
*/
	}
	private void updateHBox() {
		if (!hBox.getChildren().isEmpty())
			hBox.getChildren().clear();
		hBox.getChildren().add(polygonBox);
	}
}
