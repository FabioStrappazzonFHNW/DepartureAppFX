package ch.fhnw.oop2.departure.model;

import java.io.File;
import java.util.List;

import ch.fhnw.oop2.departure.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Timetable {
	private ObservableList<Departure> departures = FXCollections.observableArrayList();
	
	public Timetable(File file){
		List<List<String>> csv = Utils.readCSV(file, ";");
        csv.forEach(l -> departures.add(new Departure(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5))));
	}
	
	/**
     * Returns the data as an observable list of Departures.
     *
     * @return
     */
	public ObservableList<Departure> getDeparturesData() {
        return departures;
    }
}
