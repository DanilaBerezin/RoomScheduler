
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class WaitListQueries {
    private static Connection connection;
    private static PreparedStatement getWaitListEntryByDate;
    private static PreparedStatement getWaitListEntryByFaculty;
    private static PreparedStatement addWaitListEntry;
    private static PreparedStatement deleteWaitListEntry;
    private static ResultSet result;
    
    public static ArrayList<WaitListEntry> getWaitListEntriesByDate(Date date){
        
        connection = DBConnection.getConnection();
        
        ArrayList<WaitListEntry> waitlist = new ArrayList<WaitListEntry>();
        
        try{
            getWaitListEntryByDate = connection.prepareStatement("SELECT name, date, seats, timestamp FROM waitlist WHERE date = (?) ORDER by timestamp");
            getWaitListEntryByDate.setDate(1, date);
            result = getWaitListEntryByDate.executeQuery();
            
            while(result.next()){
                String name = result.getString(1);
                Date d = Date.valueOf(result.getString(2));
                int seats = Integer.valueOf(result.getString(3));
                Timestamp stamp = Timestamp.valueOf(result.getString(4));
                
                waitlist.add(new WaitListEntry(name, d, seats, stamp));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
    
    public static ArrayList<WaitListEntry> getWaitListEntryByFaculty(String name){
        connection = DBConnection.getConnection();
        
        ArrayList<WaitListEntry> waitlist = new ArrayList<WaitListEntry>();
        
        try{
            getWaitListEntryByFaculty = connection.prepareStatement("SELECT name, date, seats, timestamp FROM waitlist WHERE name = (?) ORDER by date, timestamp");
            getWaitListEntryByFaculty.setString(1, name);
            result = getWaitListEntryByFaculty.executeQuery();
            
            while(result.next()){
                Date d = Date.valueOf(result.getString(2));
                int seats = Integer.valueOf(result.getString(3));
                Timestamp stamp = Timestamp.valueOf(result.getString(4));
                
                waitlist.add(new WaitListEntry(name, d, seats, stamp));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
    
    public static void addWaitListEntry(WaitListEntry entry){
        
        connection = DBConnection.getConnection();
        
        String name = entry.getName();
        Date date = entry.getDate();
        int seats = entry.getSeats();
        Timestamp stamp = entry.getTimeStamp();
        
        try{
            addWaitListEntry = connection.prepareStatement("INSERT INTO waitlist (name, date, seats, timestamp) VALUES (?,?,?,?)");
            addWaitListEntry.setString(1, name);
            addWaitListEntry.setDate(2, date);
            addWaitListEntry.setInt(3, seats);
            addWaitListEntry.setTimestamp(4, stamp);
            addWaitListEntry.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteWaitListEntry(WaitListEntry entry){
        
        connection = DBConnection.getConnection();
        
        String name = entry.getName();
        Date date = entry.getDate();
        int seats = entry.getSeats();
        Timestamp stamp = entry.getTimeStamp();
        
        try{
            deleteWaitListEntry = connection.prepareStatement("DELETE FROM waitlist WHERE name = (?) AND date = (?)"
                                                                                                  + "AND seats = (?)"
                                                                                                  + "AND timestamp = (?)");
            deleteWaitListEntry.setString(1, name);
            deleteWaitListEntry.setDate(2, date);
            deleteWaitListEntry.setInt(3, seats);
            deleteWaitListEntry.setTimestamp(4, stamp);
            deleteWaitListEntry.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
