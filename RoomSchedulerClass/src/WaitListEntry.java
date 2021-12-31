/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author anna_
 */
public class WaitListEntry {

    private String name;
    private Date date;
    private int seats;
    private Timestamp timeStamp;
    
    public WaitListEntry(String name, Date date, int seats, Timestamp timeStamp){
        this.name = name;
        this.date = date;
        this.seats = seats;
        this.timeStamp = timeStamp;
    }
    
    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }
}
