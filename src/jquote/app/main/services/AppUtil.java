package app.main.services;

import app.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public interface AppUtil {

	static void showPrimary(URL url) { //create Exceptions!!!
		try {
			final FXMLLoader loader = new FXMLLoader(url);
			final AnchorPane anchorPane = loader.load();
			final Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
			StageService.getInstance().getPrimaryStage().setScene(scene);
			StageService.getInstance().getPrimaryStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	static void showStandAlone(URL url, StageStyle stageStyle) throws IOException {
		final FXMLLoader loader = new FXMLLoader(url);
		final Scene scene = new Scene(loader.load());
		StageService.addStandAloneStage(new Stage(stageStyle), url.getFile());
		StageService.getStandAloneStage(url.getFile()).setScene(scene);
		StageService.getStandAloneStage(url.getFile()).show();
	}



	//TODO if comes up to be necessary
	static void storeProperties(String key, Properties properties) {
		if (key == null) throw new NullPointerException("key cannot be NULL in HashTable<>");
		if (properties == null) throw new NullPointerException("value cannot be NULL in HashTable<>");
		//properties.store();
	}

	//TODO : found a better root path to call ClassLoader()
	static Properties loadProperties(String filePrefix) {
		System.out.println("[...] Loading " + filePrefix + ".properties");
		Properties properties = new Properties();
		String propertyPath = filePrefix+".properties";
		InputStream is = AppUtil.class.getClassLoader().getResourceAsStream(propertyPath);
		try {
			properties.load(is);
		} catch (IOException e) {
			System.err.println("Erreur lors du chargement du fichier de propriétés");
		}
		return properties;
	}

	static ResourceBundle loadPropertyBundle(String filePrefix) {
		System.out.println("[...] Loading " + filePrefix + ".properties");
		return ResourceBundle.getBundle(filePrefix + ".properties");
	}
}
