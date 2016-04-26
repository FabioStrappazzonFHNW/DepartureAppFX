package ch.fhnw.oop2.departure;

import ch.fhnw.oop2.departure.controller.MainController;
import ch.fhnw.oop2.departure.controller.RootLayoutController;
import ch.fhnw.oop2.departure.model.Departure;
import ch.fhnw.oop2.departure.util.Utils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Departure App FX");


        initRootLayout();
        loadCSV();
    }

    public void loadCSV() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("olten.csv").getFile());
        List<List<String>> csv = Utils.readCSV(file, ";");
        csv.forEach(l -> departures.add(new Departure(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5))));
    }


    //TODO REMOVE
    private ObservableList<Departure> departures = FXCollections.observableArrayList();

    /**
     * Returns the data as an observable list of Departures.
     *
     * @return
     */
    public ObservableList<Departure> getDeparturesData() {
        return departures;
    }


    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadMainView(new Locale("en", "EN"));
    }

    public void loadMainView(Locale locale) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(ResourceBundle.getBundle("bundles.localization", locale));
            loader.setLocation(Main.class.getResource("view/Main.fxml"));
            AnchorPane mainView = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainView);

            // Give the controller access to the main app.
            MainController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
