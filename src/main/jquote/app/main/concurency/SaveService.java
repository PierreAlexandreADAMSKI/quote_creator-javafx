package app.main.concurency;

import app.main.adapters.TableRowAdapter;
import app.main.adapters.json.JsonRowAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * app.main.concurencies Created by Pierre-Alexandre Adamski on 26/05/16.
 */
public class SaveService extends Service<Void> {

	private String absolutePath;
	private List<TableRowAdapter> items;

	public SaveService(String absolutePath, List<TableRowAdapter> items) {
		this.absolutePath = absolutePath;
		this.items = items;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				final Gson gson = new Gson();
				final List<JsonRowAdapter> jsonRowAdapterList = new ArrayList<>();
				final Type type = new TypeToken<List<JsonRowAdapter>>() {
				}.getType();

				jsonRowAdapterList.addAll(items.stream().map(JsonRowAdapter::new).collect(Collectors.toList()));

				final String json = gson.toJson(jsonRowAdapterList, type);

				Writer writer = new FileWriter(absolutePath);
				writer.write(json);
				writer.close();

				return null;
			}
		};
	}
}
