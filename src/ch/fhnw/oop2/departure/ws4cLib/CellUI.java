package ch.fhnw.oop2.departure.ws4cLib;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Created by dimitri on 25.05.2016.
 */
public class CellUI extends Pane {
    private String  cityValue, timeValue;
    private Pane    pane;
    private Line    line;
    private Line    lastLine;
    private Circle  circle;
    private Text    time;
    private Text    city;
    private boolean last, first;


    public CellUI(String city, String time, boolean isFirst, boolean islast){
        cityValue = city;
        timeValue = time;
        last = islast;
        first = isFirst;
        initializeControls();
        layoutControls();
    }

    private void initializeControls() {
        time = new Text();
        time.setText(timeValue);
        time.setX(5);
        time.setY(10);

        city = new Text();
        city.setText(cityValue);
        city.setX(100);
        city.setY(10);

        line = new Line(65,15,65,60);
        line.setStrokeWidth(1);
        line.setStroke(Color.web("ebebeb"));
        circle = new Circle(65,5,5);
        circle.setFill(Color.web("ebebeb"));
    }

    private void layoutControls(){
        if(last){
            time.getStyleClass().addAll("cell-style-bold");
            city.getStyleClass().addAll("cell-style-bold");
            this.getChildren().addAll(time, circle, city);
        }else if(first){
            time.getStyleClass().addAll("cell-style-bold");
            city.getStyleClass().addAll("cell-style-bold");
            this.getChildren().addAll(time, circle, line, city);
        }else{
            this.getChildren().addAll(time, circle, line, city);
            time.getStyleClass().addAll("cell-style");
            city.getStyleClass().addAll("cell-style");
        }

    }


}
