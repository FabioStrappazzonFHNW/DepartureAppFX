package ch.fhnw.oop2.departure;

import ch.fhnw.oop2.departure.controller.MainController;
import ch.fhnw.oop2.departure.model.Timetable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Timetable timetable;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Departure App FX");


		// Load root layout from fxml file.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class
				.getResource("view/RootLayout.fxml"));
		try {
			rootLayout = loader.load();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}


		timetable = new Timetable();


		loadMainView(new Locale("en", "EN"));

		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void loadMainView(Locale locale) {

		FXMLLoader loader = new FXMLLoader();
		loader.setResources(ResourceBundle.getBundle("bundles.localization", locale));
		loader.setLocation(Main.class.getResource("view/Main.fxml"));
		BorderPane mainView;
		try {
			mainView = loader.load();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		rootLayout.setCenter(mainView);

		// Give the controller access to the timetable.
		MainController controller = loader.getController();
		controller.setTimetable(timetable);
		controller.setMain(this);

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
