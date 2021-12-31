/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anna_
 */
public class RoomEntry {
    
    private String room;
    private int seats;
    
    public RoomEntry(String room, int seats){
        this.room = room;
        this.seats = seats;
    }

    public String getRoom() {
        return room;
    }

    public int getSeats() {
        return seats;
    }
    
    public boolean isEqual(RoomEntry room){
        return (this.room.equals(room.room));
    }
    
}
