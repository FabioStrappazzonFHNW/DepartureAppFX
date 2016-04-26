package ch.fhnw.oop2.departure.controller;

import ch.fhnw.oop2.departure.Main;
import ch.fhnw.oop2.departure.model.Departure;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ernst on 26.04.2016.
 */
public class MainController implements Initializable {
    private ResourceBundle bundle;
    private Main main;

    @FXML
    private TableView<Departure> departureTable;
    @FXML
    private TableColumn<Departure, String> id;
    @FXML
    private TableColumn<Departure, String> departureTime;
    @FXML
    private TableColumn<Departure, String> destination;
    @FXML
    private TableColumn<Departure, String> platform;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        departureTime.setCellValueFactory(cellData -> cellData.getValue().departureTimeProperty());
        destination.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        platform.setCellValueFactory(cellData -> cellData.getValue().platformProperty());
    }

    public void setMain(Main main) {
        this.main = main;
        // Add observable list data to the table
        departureTable.setItems(main.getDeparturesData());
    }
}
