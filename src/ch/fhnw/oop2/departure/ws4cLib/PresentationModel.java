package ch.fhnw.oop2.departure.ws4cLib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

/**
 * Created by dimitri on 21.05.2016.
 */



public class PresentationModel {
    private final StringProperty windowTitle = new SimpleStringProperty("SBB Träffpunkt");
    private final IntegerProperty railNumber = new SimpleIntegerProperty(0);
    private final StringProperty trainNumber = new SimpleStringProperty("S20027");

    private final ObservableList<Station> allItems = FXCollections.observableArrayList();


    // all getters and setters
    public void addItem(){
        allItems.add(new Station("Olten", new Date(), true, false));
        allItems.add(new Station("Bern", new Date(), false, false));
        allItems.add(new Station("Thun", new Date(), false, false));
        allItems.add(new Station("Spiez", new Date(), false, true));
        railNumber.set(12);

    }
    public ObservableList<Station> getAllItems(){
        return allItems;
    }

    // Window Title Property
    public String getWindowTitle() {
        return windowTitle.get();
    }
    public StringProperty windowTitleProperty() {
        return windowTitle;
    }
    public void setWindowTitle(String windowTitle) {
        this.windowTitle.set(windowTitle);
    }

    // Rail Number Property
    public int getRailNumber(){return railNumber.get();}
    public IntegerProperty railProberty(){return  railNumber;}

    // Train Number Property
    public String getTrainNumber(){return  trainNumber.get();}
    public StringProperty trainProperty(){return trainNumber;}

    //
    public String getLastStation(){
        int lenght = allItems.size()-1;
        return allItems.get(lenght).getName();
    }


}
