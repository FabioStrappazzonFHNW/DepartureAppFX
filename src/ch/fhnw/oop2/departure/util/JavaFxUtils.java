package ch.fhnw.oop2.departure.util;


import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaFxUtils.
 */
public class JavaFxUtils {

    /**
     * Adds automatic scrolling to last index in a table.
     *
     * @param <S> the generic type
     * @param view the view
     */
    public static <S> void addAutoScrollToTableView(final TableView<S> view) {
        if (view == null) {
            throw new NullPointerException();
        }

        view.getItems().addListener((ListChangeListener<S>) (c ->
        {
            c.next();
            final int size = view.getItems().size();
            if (size > 0) {
                view.scrollTo(size - 1);
            }
        }));
    }


    /**
     * Adds the filter_ windows explorer conform.
     *
     * @param field the field
     */
    public static void addFilter_WindowsExplorerConform(TextField field) {
        field.addEventFilter(KeyEvent.ANY, keyEvent ->
        {
            if (!keyEvent.getCharacter().matches("^[a-zA-Z0-9_]*$") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                keyEvent.consume();
            }
        });
    }

    /**
     * Adds the filter_ only numbers.
     *
     * @param field the field
     */
    public static void addFilter_OnlyNumbers(TextField field) {
        field.addEventFilter(KeyEvent.ANY, keyEvent ->
        {
            if (!keyEvent.getCharacter().matches("^[0-9]*$") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                keyEvent.consume();
            }
        });
    }

    /**
     * Adds the filter_ only alphanumeric.
     *
     * @param field the field
     */
    public static void addFilter_OnlyAlphanumeric(TextField field) {
        field.addEventFilter(KeyEvent.ANY, keyEvent ->
        {
            if (!keyEvent.getCharacter().matches("^[\\p{L}0-9]*$") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                keyEvent.consume();
            }
        });
    }

    /**
     * Adds the filter_ only alphabet.
     *
     * @param field the field
     */
    public static void addFilter_OnlyAlphabet(TextField field) {
        field.addEventFilter(KeyEvent.ANY, keyEvent ->
        {
            if (!keyEvent.getCharacter().matches("\\A[^\\W\\d_]+\\z") && !keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                keyEvent.consume();
            }
        });
    }


    /**
     * Force list refresh on.
     *
     * @param <T> the generic type
     * @param lsv the lsv
     */
    public static <T> void forceListRefreshOn(ListView<T> lsv) {
        ObservableList<T> items = lsv.<T>getItems();
        lsv.<T>setItems(null);
        lsv.<T>setItems(items);
    }

    /**
     * Force table view refresh on.
     *
     * @param <T> the generic type
     * @param tbv the tbv
     */
    public static <T> void forceTableViewRefreshOn(TableView<T> tbv) {
        ObservableList<T> items = tbv.<T>getItems();
        tbv.<T>setItems(null);
        tbv.<T>setItems(items);
    }


    /**
     * Creates the error alert.
     *
     * @param e the error
     * @param contentText the content text
     */
    public static void createErrorAlert(Exception e, String contentText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception caught");
        alert.setHeaderText("There was an Error");
        alert.setContentText(contentText);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }


    /**
     * Creates the textbox alert.
     *
     * @param title the title
     * @param header the header
     * @param contentText the content text
     * @param textbox the textbox
     */
    public static void createTextboxAlert(String title, String header, String contentText, String textbox) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contentText);

        TextArea textArea = new TextArea(textbox);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }


    /**
     * Creates the error alert j unit.
     *
     * @param exception the exception
     * @param contentText the content text
     */
    public static void createErrorAlertJUnit(Throwable exception, String contentText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Test failed");
        alert.setHeaderText("This test failed, further details below.");
        alert.setContentText(contentText);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
}