package app.templates;

import app.main.javafx.impl.StandAloneRoot;
import app.main.services.AppUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * app.templates Created by Pierre-Alexandre Adamski on 06/07/16.
 */
public class TemplatesViewer extends StandAloneRoot {
	public AnchorPane rootPane;
	public ImageView imageView;
	public HBox imageViewBox;
	private Image image;


	public TemplatesViewer(File file) throws IOException {
		image = new Image(new FileInputStream(file));
		AppUtil.showStandAlone(this, "TemplatesViewer", StageStyle.DECORATED);
	}

	@FXML
	private void initialize() {
		imageViewBox.setOnZoom(zoom -> {
			imageView.setScaleX(imageView.getScaleX() * zoom.getZoomFactor());
			imageView.setScaleY(imageView.getScaleY() * zoom.getZoomFactor());
		});
		imageViewBox.setOnZoomStarted(Event::consume);
		imageViewBox.setOnZoomFinished(Event::consume);
		imageView.setImage(image);
	}
}
