package ch.fhnw.oop2.departure.ws4cLib;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by dimitri on 19.04.2016.jhehej
 */
public class AppStarter extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootPanel = new BorderPaneUI();

        new ClockSBBUI();
        Scene scene = new Scene(rootPanel);

        String fonts = getClass().getResource("fonts.css").toExternalForm();
        String stylesheet = getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().addAll(stylesheet, fonts);

        primaryStage.setTitle("SBB Custom Control");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
