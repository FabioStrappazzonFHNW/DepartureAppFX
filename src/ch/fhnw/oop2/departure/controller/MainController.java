package ch.fhnw.oop2.departure.controller;

import ch.fhnw.oop2.departure.model.Departure;
import ch.fhnw.oop2.departure.model.Timetable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ernst on 26.04.2016.
 */
public class MainController implements Initializable {
    private ResourceBundle bundle;
    private Timetable timetable;
    private Departure  selectedDeparture;

    @FXML
    private TableView<Departure> tvDepartureTable;
    @FXML
    private TableColumn<Departure, String> tcId;
    @FXML
    private TableColumn<Departure, String> tcDepartureTime;
    @FXML
    private TableColumn<Departure, String> tcDestination;
    @FXML
    private TableColumn<Departure, String> tcPlatform;
    @FXML
    private TextField txtDepartureTime;
    @FXML
    private TextField txtDestination;
    @FXML
    private TextField txtPlatform;
    @FXML
    private TextField txtTrainNumber;
    @FXML
    private TextArea txtStops;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tcDepartureTime.setCellValueFactory(cellData -> cellData.getValue().departureTimeProperty());
        tcDestination.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        tcPlatform.setCellValueFactory(cellData -> cellData.getValue().platformProperty());

        /*txtDepartureTime.;
        txtDestination;
        txtPlatform;
        txtStops;
        txtTrainNumber;*/
        tvDepartureTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
        	//oldSelection.departureTimeProperty().re
        	selectedDeparture = newSelection;
        	if(oldSelection != null){
        		oldSelection.departureTimeProperty().unbind();
        		txtDepartureTime.textProperty().unbind();
        	}
        	//selectedDeparture.departureTimeProperty().bind(txtDepartureTime.textProperty());
        });
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
        // Add observable list data to the table
        tvDepartureTable.setItems(timetable.getDeparturesData());
    }
}
