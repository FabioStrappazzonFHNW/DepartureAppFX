package ch.fhnw.oop2.departure.ws4cLib;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by dimitri on 05.06.2016.
 */
public class IconsUI extends HBox{
    private final PresentationModel model;
    private Region  trainIcon;
    private Text    trainSp;
    private Region  trainType;
    private Background background;

    public IconsUI(PresentationModel _model) {
        model = _model;
        initializeControls();
        layoutControls();
    }
    private void initializeControls() {
        background = new Background(new BackgroundFill(Paint.valueOf("White"), null, null));
        this.backgroundProperty().setValue(background);
    }

    private void layoutControls() {
        this.setPadding(new Insets(0,0,0,0));

        // Train Icon
        trainIcon = new Region();
        trainIcon.getStyleClass().addAll("detailUI", "trainIcon");
        trainIcon.setMaxWidth(22);
        trainIcon.setMaxHeight(21);

        // Train Type
        trainType = new Region();
        trainType.getStyleClass().addAll("detailUI", "trainType");
        trainType.setMinWidth(55);
        trainType.setMinHeight(21);
        trainType.setMaxHeight(22);

        // Train Specification
        trainSp = new Text();
        trainSp.setText(model.getTrainNumber());
        //trainSp.setText( Integer.toString(model.getTrainNumber()));
        trainSp.getStyleClass().add("trainSp");
        trainSp.setTextAlignment(TextAlignment.JUSTIFY);
        trainSp.setFont(Font.font("Verdana", FontWeight.NORMAL,22));

        this.getChildren().addAll(trainIcon, trainType, trainSp);
        this.setPadding(new Insets(48,0,0,0));
        this.setSpacing(18);

    }

}
