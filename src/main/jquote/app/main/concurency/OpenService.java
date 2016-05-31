package app.main.concurency;

import app.main.adapters.TableRowAdapter;
import app.main.adapters.json.JsonRowAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * app.main.concurencies Created by Pierre-Alexandre Adamski on 26/05/16.
 */
public class OpenService extends Service<List<TableRowAdapter>> {

	private String absolutePath;

	public OpenService(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	@Override
	protected Task<List<TableRowAdapter>> createTask() {
		return new Task<List<TableRowAdapter>>() {
			@Override
			protected List<TableRowAdapter> call() throws Exception {
				Gson gson = new Gson();
				Type type = new TypeToken<List<JsonRowAdapter>>() {
				}.getType();

				Reader reader = new FileReader(absolutePath);
				List<JsonRowAdapter> jsonRowAdapterList = gson.fromJson(reader, type);
				reader.close();
				List<TableRowAdapter> tableRowAdapterList = new ArrayList<>();
				for (JsonRowAdapter jsonRowAdapter : jsonRowAdapterList) {
					tableRowAdapterList.add(new TableRowAdapter(jsonRowAdapter));
				}
				return tableRowAdapterList;
			}
		};

	}
}
