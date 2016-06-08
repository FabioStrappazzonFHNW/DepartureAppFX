package ch.fhnw.oop2.departure.ws4cLib;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

/**
 * Created by dimitri on 23.05.2016.
 */
public class StationCell extends ListCell<Station>{
    private Background background;
    @Override
    protected void updateItem(Station item, boolean empty){
        super.updateItem(item, empty);

        background = new Background(new BackgroundFill(Paint.valueOf("White"),null,null));
        this.setBackground(background);
        this.setPadding(new Insets(0,0,0,0));

        setText(null);
        setGraphic(null);
        if(item != null && !empty){
            setGraphic(new CellUI(item.getName(), item.getArriveTime(), item.isFirst(), item.isLast()));
        }



    }
}

