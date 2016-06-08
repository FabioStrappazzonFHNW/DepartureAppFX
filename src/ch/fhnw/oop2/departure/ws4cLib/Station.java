package ch.fhnw.oop2.departure.ws4cLib;

import java.util.Date;
import java.text.*;


/**
 * Created by dimitri on 23.05.2016.
 */
public class Station {
    private String name;
    private Date arriveTime;
    private boolean last = false;
    private boolean first = false;
    DateFormat df = new SimpleDateFormat("HH:MM");

    public Station(String _name, Date _arriveTime, boolean _first, boolean _last){
        name = _name;
        arriveTime = _arriveTime;
        first = _first;
        last = _last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArriveTime() {
        return df.format(arriveTime);
    }

    public boolean isLast(){
        return last;
    }

    public boolean isFirst(){
        return first;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }
}
