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
public class ReservationEntry {
    
    private String name;
    private String room;
    private Date date;
    private int seats;
    private Timestamp timeStamp;
    
    public ReservationEntry(String name, String room, Date date, int seats, Timestamp timestamp){
        this.name = name;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timeStamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
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
    
    public RoomEntry toRoomEntry(){
        return new RoomEntry(this.room, this.seats);
    }
    
    /*
    public String toString(){
        return "Faculty name: " + this.name + " Room reserved: " + this.room
                                + " Date reserved: " + String.valueOf(this.date)
                                + " Seats in the room: " + String.valueOf(this.seats)
                                + " timestamp of reservation: " + String.valueOf(this.timeStamp);
    }
    */
}
