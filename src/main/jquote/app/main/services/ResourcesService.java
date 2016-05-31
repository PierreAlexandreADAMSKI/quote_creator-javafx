package app.main.services;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 * app.main.services Created by Pierre-Alexandre Adamski on 19/05/16.
 */
public interface ResourcesService {
	static String getViewFor(String path) {
		return path.replaceFirst("views","controllers");
	}
}
