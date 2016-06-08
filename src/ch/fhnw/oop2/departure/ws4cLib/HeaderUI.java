package ch.fhnw.oop2.departure.ws4cLib;

import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.ClockBuilder;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * Created by dimitri on 21.05.2016.
 */
public class HeaderUI extends BorderPane {
    private Integer prefWidth = 434;
    private Integer maxWidth = 700;
    private Integer minWidth = 434;

    private PresentationModel model;
    private DirectionUI directionUI;
    private TrainRail railShield;
    private Clock clock;

    public HeaderUI(PresentationModel _model){
        model = _model;
        initializeControls();
        layoutControls();
    }

    private void initializeControls() {
        railShield = new TrainRail(model);
        directionUI = new DirectionUI(model);

        clock = ClockBuilder.create()
                .prefSize(100, 100)
                .skinType(Clock.ClockSkinType.DB)
                .running(true)
                .build();
    }

    private void layoutControls() {
        this.setPrefWidth(prefWidth);
        this.setMinWidth(minWidth);
        this.setMaxWidth(maxWidth);

        this.backgroundProperty().setValue(new Background(new BackgroundFill(Paint.valueOf("White"), null, null)));

        this.setPadding(new Insets(10,10,10,10));
        this.setMargin(directionUI, new Insets(0,0,0,10));

        this.setLeft(railShield);
        this.setCenter(directionUI);
        this.setRight(clock);

        //this.getChildren().addAll(clock, directionUI, railShield);
    }
}
