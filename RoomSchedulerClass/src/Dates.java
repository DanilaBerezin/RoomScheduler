/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author anna_
 */
public class Dates {
    
    private static Connection connection;
    private static PreparedStatement getDates;
    private static PreparedStatement addDate;
    private static ResultSet result;
    
    private Date date;
    
    public Dates(Date date){
        this.date = date;
    }
    
    public static ArrayList<Date> getAllDates(){
        
        connection = DBConnection.getConnection();
        ArrayList<Date> dates = new ArrayList<Date>();
        
        try{
            getDates = connection.prepareStatement("SELECT date FROM dates ORDER by date");
            result = getDates.executeQuery();
            
            while (result.next()){
                dates.add(result.getDate(1));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return dates;
    }
    
    public static void addDate(Date date){
        
        connection = DBConnection.getConnection();
        
        try{
            addDate = connection.prepareStatement("INSERT INTO dates (date) values (?)");
            addDate.setDate(1, date);
            addDate.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
