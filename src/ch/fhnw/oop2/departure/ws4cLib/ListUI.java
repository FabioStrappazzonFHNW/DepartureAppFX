package ch.fhnw.oop2.departure.ws4cLib;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static javafx.geometry.Pos.BOTTOM_CENTER;

/**
 * Created by dimitri on 21.05.2016.
 */
public class ListUI extends VBox {

    private ListView<Station> listView;
    private final PresentationModel model;
    private Background background;

    public ListUI(PresentationModel _model) {
        this.model = _model;
        initializeControls();
        layoutControls();
        addValueChangedListeners();
        addBindings();
    }


    private void initializeControls() {
        background = new Background(new BackgroundFill(Paint.valueOf("White"), null, null));
        listView = new ListView<>(model.getAllItems());
        listView.setCellFactory(param -> new StationCell());
        this.backgroundProperty().setValue(background);
        listView.backgroundProperty().setValue(background);
    }

    private void layoutControls() {
        this.setMaxWidth(180);
        this.prefWidth(160);
        this.setMinWidth(100);
        this.setPadding(new Insets(20,0,0,10));
        setVgrow(listView, Priority.ALWAYS);
        getChildren().addAll(listView);
    }

    private void addValueChangedListeners() {
    }

    private void addBindings() {
    }
}
