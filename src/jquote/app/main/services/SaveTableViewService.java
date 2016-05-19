package app.main.services;

import app.main.adapters.JsonRowAdapter;
import app.main.adapters.RowAdapter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * app.service Created by Pierre-Alexandre Adamski on 15/05/2016.
 */
public interface SaveTableViewService {

	//TODO I/O must run out of UI Thread -> found best way to get open() result !...

	static void save(String absolutePath, List<RowAdapter> items) {
		Gson gson = new Gson();
		List<JsonRowAdapter> jsonRowAdapterList = new ArrayList<>();
		Type type = new TypeToken<List<JsonRowAdapter>>() {
		}.getType();

		jsonRowAdapterList.addAll(items.stream().map(JsonRowAdapter::new).collect(Collectors.toList()));

		String json = gson.toJson(jsonRowAdapterList, type);

		try {
			Writer writer = new FileWriter(absolutePath);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static List<RowAdapter> open(String absolutePath) {

		Gson gson = new Gson();
		Type type = new TypeToken<List<JsonRowAdapter>>() {
		}.getType();

		try {
			Reader reader = new FileReader(absolutePath);
			List<JsonRowAdapter> jsonRowAdapterList = gson.fromJson(reader, type);
			reader.close();
			List<RowAdapter> rowAdapterList = new ArrayList<>();
			for (JsonRowAdapter jsonRowAdapter : jsonRowAdapterList) {
				rowAdapterList.add(new RowAdapter(jsonRowAdapter));
			}
			return rowAdapterList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
