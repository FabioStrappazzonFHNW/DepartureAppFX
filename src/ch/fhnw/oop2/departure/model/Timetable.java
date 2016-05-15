package ch.fhnw.oop2.departure.model;

import ch.fhnw.oop2.departure.util.JavaFxUtils;
import ch.fhnw.oop2.departure.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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

        try {
            String csv = Utils.createCSVString(data, ";");
            Files.write(file.toPath(), csv.getBytes(), StandardOpenOption.WRITE);
            JavaFxUtils.createTextboxAlert("Saved Departures", "Exported departures to csv", "Departures saved in File. Expand to view csv.", csv);
        } catch (IOException e) {
            new UncheckedIOException(e);
        }
    }

}
