package ch.fhnw.oop2.departure.ws4cLib;

import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by cs on 05.06.16.
 */
public class ClockUI extends Region {

    private static final String STYLE_CSS = "styles.css";
    private static final double PREFERRED_WIDTH  = 100;
    private static final double PREFERRED_HEIGHT = 100;

    // all parts
    private Rectangle area;
    private Text text;

    private Pane drawingPane;

    public ClockUI() {
        init();
        initializeParts();
        layoutParts();
        // addEventHandlers();
        // addValueChangedListeners();
        addBindings();
        Platform.runLater(this::relocateTexts);
    }

    private void init() {
        addStyleSheets(this);
        getStyleClass().add(getStyleClassName());
    }

    private void initializeParts() {
        area = new Rectangle(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        area.getStyleClass().add("placeholder");

        text = new Text("X");
        text.getStyleClass().add("trainRailValue");
        text.setTextOrigin(VPos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(64);


        // always needed
        drawingPane = new Pane();
        drawingPane.setMaxSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        drawingPane.setMinSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        drawingPane.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }

    private void layoutParts() {
        drawingPane.getChildren().addAll(area, text);
        getChildren().add(drawingPane);
    }

    private void addBindings() {
    }

    private void relocateTexts() {
        text.setX((PREFERRED_WIDTH - text.getLayoutBounds().getWidth()) * 0.5);
    }

    // some useful helper-methods

    private void applyCss(Node node) {
        Group group = new Group(node);
        group.getStyleClass().add(getStyleClassName());
        addStyleSheets(group);
        new Scene(group);
        node.applyCss();
    }

    private void addStyleSheets(Parent parent) {
        String stylesheet = getClass().getResource(STYLE_CSS).toExternalForm();
        parent.getStylesheets().add(stylesheet);
    }

    private String getStyleClassName() {
        String className = this.getClass().getSimpleName();

        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

}
