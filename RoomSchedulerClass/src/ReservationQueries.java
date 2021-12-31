
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anna_
 */
public class ReservationQueries {
    
    private static Connection connection;
    private static PreparedStatement getReservationByDate;
    private static PreparedStatement getReservationByFaculty;
    private static PreparedStatement addReservation;
    private static PreparedStatement deleteReservation;
    private static ResultSet result;
    
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date){
        
        connection = DBConnection.getConnection();
        
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        
        try{
            getReservationByDate = connection.prepareStatement("SELECT name, room, date, seats, timestamp FROM reservations WHERE date = (?)");
            getReservationByDate.setDate(1, date);
            result = getReservationByDate.executeQuery();
            
            while(result.next()){
                String name = result.getString(1);
                String room = result.getString(2);
                Date d = Date.valueOf(result.getString(3));
                int seats = Integer.valueOf(result.getString(4));
                Timestamp stamp = Timestamp.valueOf(result.getString(5));
                
                reservations.add(new ReservationEntry(name, room, d, seats, stamp));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return reservations;
    }
    
    public static ArrayList<RoomEntry> getRoomsReservedByDate(Date date){
        
        ArrayList<ReservationEntry> reservations = getReservationsByDate(date);
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        
        for(int i = 0; i < reservations.size(); i++){
            ReservationEntry entry = reservations.get(i);
            String room = entry.getRoom();
            int seats = entry.getSeats();
            
            rooms.add(new RoomEntry(room, seats));
        }
        
        return rooms;
    }
    
    public static void addReservationEntry(ReservationEntry entry){
        
        connection = DBConnection.getConnection();
        
        String name = entry.getName();
        String room = entry.getRoom();
        Date date = entry.getDate();
        int seats = entry.getSeats();
        Timestamp stamp = entry.getTimeStamp();
        
        try{
            addReservation = connection.prepareStatement("INSERT INTO reservations (name, room, date, seats, timestamp) VALUES (?,?,?,?,?)");
            addReservation.setString(1, name);
            addReservation.setString(2, room);
            addReservation.setDate(3, date);
            addReservation.setInt(4, seats);
            addReservation.setTimestamp(5, stamp);
            addReservation.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ReservationEntry> getReservationByFaculty(String name){
        connection = DBConnection.getConnection();
        
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        
        try{
            getReservationByFaculty = connection.prepareStatement("SELECT name, room, date, seats, timestamp FROM reservations WHERE name = (?)");
            getReservationByFaculty.setString(1, name);
            result = getReservationByFaculty.executeQuery();
            
            while(result.next()){
                String room = result.getString(2);
                Date d = Date.valueOf(result.getString(3));
                int seats = Integer.valueOf(result.getString(4));
                Timestamp stamp = Timestamp.valueOf(result.getString(5));
                
                reservations.add(new ReservationEntry(name, room, d, seats, stamp));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return reservations;
    }
    
    public static void deleteReservation(ReservationEntry entry){
        connection = DBConnection.getConnection();
        
        String name = entry.getName();
        String room = entry.getRoom();
        Date date = entry.getDate();
        int seats = entry.getSeats();
        Timestamp stamp = entry.getTimeStamp();
        
        try{
            deleteReservation = connection.prepareStatement("DELETE FROM reservations (name, room, date, seats, timestamp) WHERE name = (?) AND room = (?)"
                                                                                                                                      + "AND date = (?)"
                                                                                                                                      + "AND seats = (?)"
                                                                                                                                      + "AND timestamp = (?)");
            deleteReservation.setString(1, name);
            deleteReservation.setString(2, room);
            deleteReservation.setDate(3, date);
            deleteReservation.setInt(4, seats);
            deleteReservation.setTimestamp(5, stamp);
            deleteReservation.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
