package ch.fhnw.oop2.departure.controller;

import ch.fhnw.oop2.departure.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Locale;

/**
 * Created by Matthias Ernst on 26.04.2016.
 */
public class RootLayoutController {
    @FXML
    Button toggleLanguage;

    @FXML
    public void toggleLanguage(ActionEvent e) {
        if (toggleLanguage.getText().equals("DE")) {
            toggleLanguage.setText("EN");
            main.loadMainView(new Locale("de", "DE"));
        } else {
            toggleLanguage.setText("DE");
            main.loadMainView(new Locale("en", "EN"));
        }
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
}
