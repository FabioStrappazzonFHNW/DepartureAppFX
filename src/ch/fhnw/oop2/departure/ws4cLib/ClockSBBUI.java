package ch.fhnw.oop2.departure.ws4cLib;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.ClockBuilder;

/**
 * @author Dieter Holz
 */
public class ClockSBBUI  {
    private Clock clock;

    public ClockSBBUI(){
        init();
    }
    public void init(){
        clock = ClockBuilder.create()
                .prefSize(200, 200)
                .skinType(Clock.ClockSkinType.DB)
                .running(true)
                .build();
    }
}