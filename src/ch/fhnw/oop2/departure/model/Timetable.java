package ch.fhnw.oop2.departure.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ch.fhnw.oop2.departure.util.JavaFxUtils;
import ch.fhnw.oop2.departure.util.SimpleStringPropertyDeserializer;
import ch.fhnw.oop2.departure.util.SimpleStringPropertySerializer;
import ch.fhnw.oop2.departure.util.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Timetable {
	private ObservableList<Departure> departures = FXCollections.observableArrayList();
	
	private File file;

	public Timetable(File file) {
		this.file = file;
		if (file.getAbsolutePath().endsWith(".csv")) {
			loadCSV(file);
		} else {
			loadJSON(file);
		}
	}

	/**
	 * Returns the data as an observable list of Departures.
	 *
	 * @return
	 */
	public ObservableList<Departure> getDeparturesData() {
		return departures;
	}

	public void delete(Departure selectedItem) {
		departures.remove(selectedItem);
	}

	public void createDeparture() {
		departures.addAll(new Departure("", "", "", "", "", ""));
	}

	public void loadCSV(File file) {
		List<List<String>> csv = Utils.readCSV(file, ";");
		csv.stream().skip(1).forEach(l -> {
			try {// TODO not so good, ignores faulty lines
				departures.add(new Departure(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5)));
			} catch (Exception e) {
				// System.out.println(l); //TODO empty catch^^
			}
		});
	}

	public void loadJSON(File file) {
		Gson gson = getGson();
		Type collectionType = new TypeToken<List<Departure>>() {
		}.getType();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {

			List<Departure> eles = gson.fromJson(reader, collectionType);
			departures.addAll(eles);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	public void saveJSON() {
		try {
			if (file.getAbsolutePath().endsWith(".csv")) {
				file = Files.move(file.toPath(), Paths.get(file.getAbsolutePath().replace(".csv", ".json")),
						StandardCopyOption.REPLACE_EXISTING).toFile();
			}
			Gson gson = getGson();
			try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
				String result = gson.toJson(departures.toArray());
				out.write(result);
				JavaFxUtils.createTextboxAlert("Saved Departures", "Exported departures to json",
						"Departures saved in File. Expand to view json.", result);
			} catch (IOException e) {
				new UncheckedIOException(e);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Gson getGson() {
		GsonBuilder gsonb = new GsonBuilder();
		gsonb.registerTypeAdapter(SimpleStringProperty.class, new SimpleStringPropertyDeserializer());
		gsonb.registerTypeAdapter(SimpleStringProperty.class, new SimpleStringPropertySerializer());

		return gsonb.create();
	}

	

}
