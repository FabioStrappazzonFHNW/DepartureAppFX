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
public class TrainRail extends Region {

	private static final String STYLE_CSS = "styles.css";
	private static final double PREFERRED_WIDTH = 100;
	private static final double PREFERRED_HEIGHT = 100;

	private PresentationModel model;

	// all parts
	private Rectangle area;
	private Rectangle frame;
	private Text rail;
	private Text trainRailValue;

	private Pane drawingPane;

	public TrainRail(PresentationModel model) {
		this.model = model;
		model.addItem();
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
		area.getStyleClass().add("trainRailArea");

		frame = new Rectangle(PREFERRED_WIDTH - 20, PREFERRED_HEIGHT - 20);
		frame.getStyleClass().add("trainRailFrame");
		frame.setX(10);
		frame.setY(10);
		frame.setArcHeight(15);
		frame.setArcWidth(15);
		frame.setStrokeWidth(3);

		rail = new Text("Gleis");
		rail.getStyleClass().add("rail");
		rail.setTextOrigin(VPos.CENTER);
		rail.setX(18);
		rail.setTextAlignment(TextAlignment.CENTER);
		rail.setY(24);

		trainRailValue = new Text(Integer.toString(model.getRailNumber()));
		trainRailValue.getStyleClass().add("trainRailValue");
		trainRailValue.setTextOrigin(VPos.CENTER);
		trainRailValue.setTextAlignment(TextAlignment.CENTER);
		trainRailValue.setY(55);


		// always needed
		drawingPane = new Pane();
		drawingPane.setMaxSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
		drawingPane.setMinSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
		drawingPane.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	}

	private void layoutParts() {
		drawingPane.getChildren().addAll(area, frame, rail, trainRailValue);
		getChildren().add(drawingPane);
	}

	private void addBindings() {
	}

	private void relocateTexts() {
		trainRailValue.setX((PREFERRED_WIDTH - trainRailValue.getLayoutBounds().getWidth()) * 0.5);
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
