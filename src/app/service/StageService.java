package app.service;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;
import java.util.Map;

public class StageService {
	private static Map<String, Stage> standAloneStages = new HashMap<>();

	public static Stage getStandAloneStage(String name) {
		return standAloneStages.get(name);
	}

	public static void addStandAloneStage(Stage standAloneStage, String name) {
		standAloneStages.put(name, standAloneStage);
	}

	private StageService() {
	}

	private static class StageServiceHolder {
		private static final StageService INSTANCE = new StageService();
	}

	public static StageService getInstance() {
		return StageServiceHolder.INSTANCE;
	}

	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public void closePrimaryStage() {
		primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public static void closeStage(Stage stage) {
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
}
