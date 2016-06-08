package ch.fhnw.oop2.departure.model;

import ch.fhnw.oop2.departure.util.JavaFxUtils;
import ch.fhnw.oop2.departure.util.Utils;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private ObservableList<Departure> departures = FXCollections.observableArrayList();
    private List<String> headers;
    private File file;

    public Timetable(File file) {
        loadCSV(file);
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
        this.file = file;
        List<List<String>> csv = Utils.readCSV(file, ";");
        csv.stream().skip(1).forEach(l -> {
            try {//TODO not so good, ignores faulty lines
                departures.add(new Departure(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5)));
            } catch (Exception e) {
               // System.out.println(l); //TODO empty catch^^
            }
        });
        headers = csv.get(0);
    }

    //TODO rewrite in a more general way? whatever
    public void saveCSV() {
        List<List<String>> data = new ArrayList<>();

        data.add(headers);
        departures.forEach(d -> {
            List<String> temp = new ArrayList<>();
            temp.add(d.getId());
            temp.add(d.getDepartureTime());
            temp.add(d.getTrainNumber());
            temp.add(d.getDestination());
            temp.add(d.getVia());
            temp.add(d.getPlatform());
            data.add(temp);
        });
        	try (Writer out = new BufferedWriter(new OutputStreamWriter(
            	    new FileOutputStream(file), "UTF-8"))){

        	    out.write(Utils.createCSVString(data, ";"));
        	    JavaFxUtils.createTextboxAlert("Saved Departures", "Exported departures to csv", "Departures saved in File. Expand to view csv.", "");
        	} catch(IOException e){
                new UncheckedIOException(e);
            }
    }

}
