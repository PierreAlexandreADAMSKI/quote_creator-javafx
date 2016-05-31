package app;

import javafx.application.Application;
import javafx.stage.Stage;

import app.main.services.StageService;

import static app.main.services.AppUtil.*;
import static app.main.services.ReflectionService.getViewsFromApp;

/**
 * java Created by Pierre-Alexandre Adamski on 27/03/2016.
 */
public class App extends Application{
	public static void main(String[] args) throws Exception {
		launch(args); // for javaFx
	}

	@Override
	public void start(Stage primaryStage) throws Exception { //create exceptions!!!
		StageService.getInstance().setPrimaryStage(primaryStage);
		StageService.getInstance().getPrimaryStage().setTitle("Home");
		showPrimary(getViewsFromApp("MainStage"));
	}
}
