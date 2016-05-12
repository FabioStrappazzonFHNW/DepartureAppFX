package ch.fhnw.oop2.departure;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Locale;
import java.util.ResourceBundle;

import ch.fhnw.oop2.departure.controller.MainController;
import ch.fhnw.oop2.departure.controller.RootLayoutController;
import ch.fhnw.oop2.departure.model.Timetable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

        timetable = new Timetable(new File(
        		getClass().getClassLoader().getResource("olten.csv").getFile())); 

        // Give the controller access to the main app.
        RootLayoutController controller = loader.getController();
        controller.setMain(this);

        
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
            AnchorPane mainView;
			try {
				mainView = loader.load();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainView);

            // Give the controller access to the timetable.
            MainController controller = loader.getController();
            controller.setTimetable(timetable);
            
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
