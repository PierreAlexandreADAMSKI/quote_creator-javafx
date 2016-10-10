package app.templates;

import app.main.javafx_impl.impl.StandAloneRoot;
import app.main.services.AppUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * app.templates Created by Pierre-Alexandre Adamski on 06/07/16.
 */
public class TemplatesViewer extends StandAloneRoot {
	public AnchorPane rootPane;
	public HBox webBox;
	public Tab tab1;
	public Tab tab2;
	public Button printButton = new Button("imprimer");


	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();

	private ChangeListener<ObservableList<Node>> tabChangeListener = (observable, oldValue, newValue) -> {
		List<Integer> newBinaryDisable = new ArrayList<>();
		List<Integer> oldBinaryDisable = new ArrayList<>();
		newValue.filtered(node -> node instanceof CheckBox).forEach(checkBox -> {
			if(checkBox.isDisable()) newBinaryDisable.add(0);
			else newBinaryDisable.add((1));
		});
		oldValue.filtered(node -> node instanceof CheckBox).forEach(checkBox -> {
			if(checkBox.isDisable()) oldBinaryDisable.add(0);
			else oldBinaryDisable.add((1));
		});
		if (!newBinaryDisable.equals(oldBinaryDisable)) webEngine.reload();
	};

	public TemplatesViewer(String file) throws IOException {
		AppUtil.showStandAlone(this, "TemplatesViewer", StageStyle.DECORATED);

		//Setup web view
		webBox.getChildren().add(browser);
		File copy = File.createTempFile(file.substring(file.lastIndexOf("/") + 1), null);
		copy.deleteOnExit();
		webEngine.load(file);

		//setup tabs
		tab2.setContent(printButton);

		printButton.setOnAction(event -> {
			PrinterJob job = PrinterJob.createPrinterJob();
			if (job != null && job.showPrintDialog(browser.getScene().getWindow())) {

				boolean success = job.printPage(browser);
				if (success) {
					//TODO job status
					job.endJob();
				}
				/*
				webEngine.print(job);
				job.endJob();
				*/
			}
		});

	}

	public TemplatesViewer(List<File> peers) throws IOException {

	}

	@FXML
	private void initialize() {

	}
}
