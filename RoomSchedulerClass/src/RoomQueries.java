
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement deleteRoom;
    private static ResultSet result;
    
    public static ArrayList<RoomEntry> getAllPossibleRooms(){
        
        ArrayList<RoomEntry> allRooms = new ArrayList<RoomEntry>();
        
        connection = DBConnection.getConnection();
        
        try{
            getRooms = connection.prepareStatement("SELECT room, seats FROM ROOM");
            result = getRooms.executeQuery();
            
            while(result.next()){
                String sRoom = result.getString(1);
                int seats = result.getInt(2);
                
                RoomEntry room = new RoomEntry(sRoom, seats);
                
                allRooms.add(room);
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return allRooms;
    }
    
    public static void addRoom(RoomEntry room){
        
        connection = DBConnection.getConnection();
        
        String roomNumber = room.getRoom();
        int seats = room.getSeats();
        
        try{
            addRoom = connection.prepareStatement("INSERT INTO room (room, seats) VALUES (?,?)");
            addRoom.setString(1, roomNumber);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropRoom(RoomEntry room){
        
        connection = DBConnection.getConnection();
        
        String roomNumber = room.getRoom();
        //int seats = room.getSeats();
        
        try{
            deleteRoom = connection.prepareStatement("DELETE FROM room WHERE room = (?)");
            deleteRoom.setString(1, roomNumber);
            deleteRoom.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
