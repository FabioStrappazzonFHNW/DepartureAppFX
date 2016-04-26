package ch.fhnw.oop2.departure.model;

import javafx.beans.property.SimpleStringProperty;

public class Departure {


    final private SimpleStringProperty id;
    final private SimpleStringProperty departureTime;
    final private SimpleStringProperty trainNumber;
    final private SimpleStringProperty destination;
    final private SimpleStringProperty via;
    final private SimpleStringProperty platform;

    public Departure(String id, String departureTime,
                     String trainNumber, String destination,
                     String via, String platform) {

        this.id = new SimpleStringProperty(id);
        this.departureTime = new SimpleStringProperty(departureTime);
        this.trainNumber = new SimpleStringProperty(trainNumber);
        this.destination = new SimpleStringProperty(destination);
        this.via = new SimpleStringProperty(via);
        this.platform = new SimpleStringProperty(platform);

    }


    //Getters and Setters
    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getDepartureTime() {
        return departureTime.get();
    }

    public SimpleStringProperty departureTimeProperty() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime.set(departureTime);
    }

    public String getTrainNumber() {
        return trainNumber.get();
    }

    public SimpleStringProperty trainNumberProperty() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber.set(trainNumber);
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getVia() {
        return via.get();
    }

    public SimpleStringProperty viaProperty() {
        return via;
    }

    public void setVia(String via) {
        this.via.set(via);
    }

    public String getPlatform() {
        return platform.get();
    }

    public SimpleStringProperty platformProperty() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform.set(platform);
    }

}
