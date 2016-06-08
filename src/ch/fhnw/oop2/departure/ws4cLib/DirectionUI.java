package ch.fhnw.oop2.departure.ws4cLib;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by Christian Schibli on 05.06.16.
 */
public class DirectionUI extends Region {

    private static final double PREFERRED_WIDTH  = 100;
    private static final double PREFERRED_HEIGHT = 100;


    // all parts
    private Text toDestination;
    private Text destination;
    private PresentationModel model;

    private Pane drawingPane;

    public DirectionUI(PresentationModel model) {
        this.model = model;
        init();
        initializeParts();
        layoutParts();
        // addEventHandlers();
        // addValueChangedListeners();
        addBindings();

    }

    private void init() {
        addStyleSheets(this);
        getStyleClass().add(getStyleClassName());
    }

    private void initializeParts() {

        toDestination = new Text("nach");
        toDestination.getStyleClass().add("toDestination");
        toDestination.setTextOrigin(VPos.CENTER);
        toDestination.setTextAlignment(TextAlignment.CENTER);
        toDestination.setY(50);
        toDestination.setMouseTransparent(true);

        destination = new Text(model.getLastStation());
        destination.getStyleClass().add("destination");
        destination.setTextOrigin(VPos.CENTER);
        destination.setTextAlignment(TextAlignment.CENTER);
        destination.setY(72);
        destination.setMouseTransparent(true);

        // always needed
        drawingPane = new Pane();
        drawingPane.setMaxSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        drawingPane.setMinSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        drawingPane.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }

    private void layoutParts() {
        drawingPane.getChildren().addAll(toDestination, destination);
        getChildren().add(drawingPane);
    }

    private void addBindings() {
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
    }

    private String getStyleClassName() {
        String className = this.getClass().getSimpleName();

        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

}
