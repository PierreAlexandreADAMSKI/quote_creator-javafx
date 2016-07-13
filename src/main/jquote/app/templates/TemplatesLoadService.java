package app.templates;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import org.apache.batik.transcoder.Transcoder;
import org.apache.fop.svg.PDFTranscoder;

/**
 * app.templates Created by Pierre-Alexandre Adamski on 06/07/16.
 */
public class TemplatesLoadService extends Service<String>{
	Transcoder transcoder = new PDFTranscoder();

	private String absolutePath;

	public TemplatesLoadService(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	@Override
	protected Task<String> createTask() {

		return null;
	}
}
