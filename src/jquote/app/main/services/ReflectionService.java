package app.main.services;

import app.App;
import app.main.javafx.Box;

import java.io.IOException;
import java.net.URL;

/**
 * app.main.services Created by Pierre-Alexandre Adamski on 19/05/16.
 */
public interface ReflectionService {
	static URL getResourceFromApp(String dest) {
		return App.class.getResource("main/controllers/" + dest + ".fxml");
	}

	static URL getResourceFromBox(String dest, Box c) {
		return c.getClass().getResource(dest + ".fxml");
	}
}
