package ch.fhnw.oop2.departure.controller;

import ch.fhnw.oop2.departure.Main;
import ch.fhnw.oop2.departure.model.Departure;
import ch.fhnw.oop2.departure.model.Timetable;
import ch.fhnw.oop2.departure.util.JavaFxUtils;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ernst on 26.04.2016.
 */
public class MainController implements Initializable {
	private ResourceBundle bundle;
	private Timetable timetable;
	private Departure departureProxy = new Departure();


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
	@FXML
	private Button toggleLanguage;
	@FXML
	private TextField txtFilter;

	@FXML
	public void toggleLanguage(ActionEvent e) {
		if (toggleLanguage.getText().equals("DE")) {
			main.loadMainView(new Locale("de", "DE"));
		} else {
			main.loadMainView(new Locale("en", "EN"));
		}
	}

	@FXML
	public void redo(ActionEvent actionEvent) {
		//TODO redo
	}

	@FXML
	public void undo(ActionEvent actionEvent) {
		//TODO undo
	}

	@FXML
	public void clear(ActionEvent actionEvent) {
		timetable.delete(tvDepartureTable.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void add(ActionEvent actionEvent) {
		timetable.createDeparture();
		tvDepartureTable.getSelectionModel().selectLast();
		tvDepartureTable.scrollTo(tvDepartureTable.getItems().size() - 1);
	}

	@FXML
	public void save(ActionEvent actionEvent) {
		timetable.saveJSON();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = resources;
		tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		tcId.setCellFactory(TextFieldTableCell.forTableColumn());
		tcId.setOnEditCommit((t) ->
				t.getTableView().getItems().get(
						t.getTablePosition().getRow()
				).setId(t.getNewValue())
		);

		tcDepartureTime.setCellValueFactory(cellData -> cellData.getValue().departureTimeProperty());
		tcDepartureTime.setCellFactory(TextFieldTableCell.forTableColumn());
		tcDepartureTime.setOnEditCommit((t) ->
				t.getTableView().getItems().get(
						t.getTablePosition().getRow()
				).setDepartureTime(t.getNewValue())
		);

		tcDestination.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
		tcDestination.setCellFactory(TextFieldTableCell.forTableColumn());
		tcDestination.setOnEditCommit((t) ->
				t.getTableView().getItems().get(
						t.getTablePosition().getRow()
				).setDestination(t.getNewValue())
		);

		tcPlatform.setCellValueFactory(cellData -> cellData.getValue().platformProperty());
		tcPlatform.setCellFactory(TextFieldTableCell.forTableColumn());
		tcPlatform.setOnEditCommit((t) ->
				t.getTableView().getItems().get(
						t.getTablePosition().getRow()
				).setPlatform(t.getNewValue())
		);

		JavaFxUtils.addFilter_OnlyNumbers(txtPlatform);

		//Proxybinding
		departureProxy.trainNumberProperty().bindBidirectional(txtTrainNumber.textProperty());
		departureProxy.departureTimeProperty().bindBidirectional(txtDepartureTime.textProperty());
		departureProxy.destinationProperty().bindBidirectional(txtDestination.textProperty());
		departureProxy.viaProperty().bindBidirectional(txtStops.textProperty());
		departureProxy.platformProperty().bindBidirectional(txtPlatform.textProperty());

		//OnDemand Binding
		tvDepartureTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue != null) {
				departureProxy.trainNumberProperty().unbindBidirectional(oldValue.trainNumberProperty());
				departureProxy.departureTimeProperty().unbindBidirectional(oldValue.departureTimeProperty());
				departureProxy.destinationProperty().unbindBidirectional(oldValue.destinationProperty());
				departureProxy.viaProperty().unbindBidirectional(oldValue.viaProperty());
				departureProxy.platformProperty().unbindBidirectional(oldValue.platformProperty());
			}
			departureProxy.trainNumberProperty().bindBidirectional(newValue.trainNumberProperty());
			departureProxy.departureTimeProperty().bindBidirectional(newValue.departureTimeProperty());
			departureProxy.destinationProperty().bindBidirectional(newValue.destinationProperty());
			departureProxy.viaProperty().bindBidirectional(newValue.viaProperty());
			departureProxy.platformProperty().bindBidirectional(newValue.platformProperty());
		});
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
		// Add observable list data to the table
		FilteredList<Departure> filteredData = new FilteredList<>(timetable.getDeparturesData(), p -> true);
		txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(departure -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                return departure.getDepartureTime().toLowerCase().contains(lowerCaseFilter) ||
                		departure.getDestination().toLowerCase().contains(lowerCaseFilter) ||
                		departure.getId().toLowerCase().contains(lowerCaseFilter) ||
                		departure.getPlatform().toLowerCase().contains(lowerCaseFilter);
                
            });
        });
		tvDepartureTable.setItems(filteredData);
	}

	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}
}
