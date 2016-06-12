package ch.fhnw.oop2.departure.controller;

import ch.fhnw.oop2.departure.Main;
import ch.fhnw.oop2.departure.model.Departure;
import ch.fhnw.oop2.departure.model.DepartureProxy;
import ch.fhnw.oop2.departure.model.Timetable;
import ch.fhnw.oop2.departure.util.JavaFxUtils;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ernst on 26.04.2016.
 */
public class MainController implements Initializable {
	private ResourceBundle bundle;
	private Main main;
	private Timetable timetable;

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
		tvDepartureTable.getSelectionModel().select(null);
		if (toggleLanguage.getText().equals("DE")) {
			main.loadMainView(new Locale("de", "DE"));
		} else {
			main.loadMainView(new Locale("en", "EN"));
		}
	}

	@FXML
	public void redo(ActionEvent actionEvent) {
		//TODO redo
		JavaFxUtils.createAlert(bundle.getString("info"), bundle.getString("notImplemented"), bundle.getString("redoNotImplemented"));
	}

	@FXML
	public void undo(ActionEvent actionEvent) {
		//TODO undo
		JavaFxUtils.createAlert(bundle.getString("info"), bundle.getString("notImplemented"), bundle.getString("undoNotImplemented"));
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
		FileChooser fileChooser = new FileChooser();
		if (timetable.getFile() != null && !timetable.getFile().getAbsolutePath().endsWith(".csv")) {
			fileChooser.setInitialFileName(timetable.getFile().getPath());
		}
		fileChooser.setTitle("Save File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json", "*.json"));
		File f = fileChooser.showSaveDialog(main.getPrimaryStage());

		String result = timetable.saveJSON(f);

		JavaFxUtils.createTextboxAlert(bundle.getString("saved"), bundle.getString("exported"),
				bundle.getString("expand"), result);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = resources;

		toggleLanguage.setTooltip(new Tooltip(bundle.getString("tooltip")));

		tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		tcId.setCellFactory(TextFieldTableCell.forTableColumn());
		tcId.setOnEditCommit(e -> timetable.getCurrentDeparture().setId(e.getNewValue()));

		tcDepartureTime.setCellValueFactory(cellData -> cellData.getValue().departureTimeProperty());
		tcDepartureTime.setCellFactory(TextFieldTableCell.forTableColumn());
		tcDepartureTime.setOnEditCommit(e -> timetable.getCurrentDeparture().setDepartureTime(e.getNewValue()));

		tcDestination.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
		tcDestination.setCellFactory(TextFieldTableCell.forTableColumn());
		tcDestination.setOnEditCommit(e ->timetable.getCurrentDeparture().setDestination(e.getNewValue()));

		tcPlatform.setCellValueFactory(cellData -> cellData.getValue().platformProperty());
		tcPlatform.setCellFactory(TextFieldTableCell.forTableColumn());
		tcPlatform.setOnEditCommit(e ->timetable.getCurrentDeparture().setPlatform(e.getNewValue()));

		JavaFxUtils.addFilter_OnlyNumbers(txtPlatform);
		
		tvDepartureTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			
			timetable.setCurrentDeparture((Departure)newValue);
			
			boolean disabled = newValue == null;
			txtDepartureTime.setDisable(disabled);
			txtTrainNumber.setDisable(disabled);
			txtDestination.setDisable(disabled);
			txtStops.setDisable(disabled);
			txtPlatform.setDisable(disabled);
		});
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
		
		DepartureProxy c = timetable.getCurrentDeparture();
		c.trainNumberProperty().bindBidirectional(txtTrainNumber.textProperty());
		c.departureTimeProperty().bindBidirectional(txtDepartureTime.textProperty());
		c.destinationProperty().bindBidirectional(txtDestination.textProperty());
		c.viaProperty().bindBidirectional(txtStops.textProperty());
		c.platformProperty().bindBidirectional(txtPlatform.textProperty());
		
		FilteredList<Departure> filteredData = new FilteredList<>(timetable.getDeparturesData(), p -> true);
		txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(departure -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				return departure.getDepartureTime().toLowerCase().contains(lowerCaseFilter) ||
						departure.getDestination().toLowerCase().contains(lowerCaseFilter) ||
						departure.getId().toLowerCase().contains(lowerCaseFilter) ||
						departure.getPlatform().toLowerCase().contains(lowerCaseFilter);

			});
		});
		tvDepartureTable.setItems(filteredData);
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
