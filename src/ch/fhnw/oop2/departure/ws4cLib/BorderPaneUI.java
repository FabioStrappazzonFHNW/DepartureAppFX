package ch.fhnw.oop2.departure.ws4cLib;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;


/**
 * Created by dimitri on 21.05.2016.
 */
public class BorderPaneUI extends BorderPane  {
    private Button button;
    private PresentationModel model;
    private ListUI list;
    private HeaderUI header;
    private IconsUI icons;

    public BorderPaneUI(){
        initializeControls();
        layoutControls();
        addEventHandlers();
    }
    private void initializeControls() {
        model = new PresentationModel();
        list = new ListUI(model);
        header = new HeaderUI(model);
        icons = new IconsUI(model);
        button = new Button("Settings");

    }

    private void layoutControls() {
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().addAll("settings-button");
        this.backgroundProperty().setValue(new Background(new BackgroundFill(Paint.valueOf("White"), null, null)));

        setTop(header);
        setLeft(list);
        setCenter(icons);
        setBottom(button);
    }

    private void addEventHandlers() {
        button.setOnAction(event -> model.addItem());
    }

}
