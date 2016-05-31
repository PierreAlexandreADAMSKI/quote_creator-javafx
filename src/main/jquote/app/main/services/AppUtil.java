package app.main.services;

import app.App;
import app.main.controllers.widgets.dialog.AlertDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import javafx.geometry.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static app.main.services.ReflectionService.getResourceFromObject;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public interface AppUtil {
	HashMap<String, Properties> propertiesBundle = new HashMap<>();
	HashMap<String, HashMap<String, String>> propertiesDictionary = new HashMap<>();

	static void showPrimary(URL url) { //create Exceptions!!!
		try {
			final FXMLLoader loader = new FXMLLoader(url);
			final AnchorPane anchorPane = loader.load();
			final Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
			//scene.getStylesheets().add(App.class.getResource("modena.css").toExternalForm());
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

	static void showWidget(Object root, String fxmlName){
		FXMLLoader loader = new FXMLLoader(getResourceFromObject(fxmlName, root));
		loader.setRoot(root);
		loader.setController(root);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void showAlertDialog( Node rootPane, Point2D point, String message, String details){
		final PopOver popup = new PopOver();
		final AlertDialog alertDialog = new AlertDialog(message, details);
		alertDialog.okButton.setOnAction(event -> popup.hide(new Duration(500)));
		popup.setContentNode(alertDialog);
		popup.show(rootPane, point.getX(), point.getY());
	}


	//ADDME if comes up to be necessary
	static void storeProperties(String key, Properties properties) {
		if (key == null) throw new NullPointerException("key cannot be NULL in HashTable<>");
		if (properties == null) throw new NullPointerException("value cannot be NULL in HashTable<>");
		//properties.store();
	}

	//UPGRADEME with spring
	//TODO loadAllProperties recursively
	static Properties loadAllProperties(ClassLoader baseLoader) {
		return null;
	}

	static Properties loadProperties(ClassLoader loader, String filePrefix) {
		System.out.println("[...] Loading " + filePrefix + ".properties");
		Properties properties = new Properties();
		String propertyPath = "app/"+filePrefix+".properties";
		InputStream is = loader.getResourceAsStream(propertyPath);
		try {
			properties.load(is);
			//propertiesBundle & propertiesDictionary must be filled in
			//loadAllProperties at the very beginning and called as static member
			propertiesBundle.put(filePrefix, properties);
			propertiesDictionary.put(filePrefix, new HashMap<>());
			properties.stringPropertyNames().forEach(name ->
					propertiesDictionary.get(filePrefix).put(name, properties.getProperty(name)));
		} catch (IOException e) {
			System.err.println("Erreur lors du chargement du fichier de propriétés");
		}
		return properties;
	}

	static String loadProperty(ClassLoader loader,String property) {
		String[] split = property.split("\\.");
		return loadProperties(loader, split[0]).getProperty(property);
	}

	static ResourceBundle loadPropertyBundle(String filePrefix) {
		System.out.println("[...] Loading " + filePrefix + ".properties");
		return ResourceBundle.getBundle(filePrefix + ".properties");
	}
}
