package app.main.services;

import app.App;

import java.net.URL;

/**
 * app.main.services Created by Pierre-Alexandre Adamski on 19/05/16.
 */
public interface ReflectionService {
	static URL getViewsFromApp(String dest) {
		ClassLoader loader = App.class.getClassLoader();
		String pathTo = AppUtil.loadProperty(loader, "path.controllers");
		return App.class.getResource(pathTo + dest + ".fxml");
	}

	static URL getResourceFromObject(String dest, Object c) {
		return c.getClass().getResource(dest + ".fxml");
	}
}
