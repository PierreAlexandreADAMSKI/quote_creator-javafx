package jquote.app;

import javafx.application.Application;
import javafx.stage.Stage;

import jquote.app.main.services.AppUtil;
import jquote.app.main.services.StageService;

import java.net.URL;
import java.util.Properties;

import static jquote.app.main.services.AppUtil.*;

/**
 * java Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public class App extends Application{
	public static void main(String[] args) throws Exception {
		//TODO fix path.properties access
		//pathPropertyBundle = loadPropertyBundle("path");
		//pathProperties = loadProperties("path");
		launch(args); // for javaFx
	}

	@Override
	public void start(Stage primaryStage) throws Exception { //create exceptions!!!
		StageService.getInstance().setPrimaryStage(primaryStage);
		StageService.getInstance().getPrimaryStage().setTitle("Home");
		AppUtil.showPrimary(this.getClass().getResource("main/views/MainStage.fxml"));
	}
}
