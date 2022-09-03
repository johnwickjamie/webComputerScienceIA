/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mySQL;
import java.sql.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author jamesmcdonnell
 */
// this class contains methods for connecting to the mySQL database and making general or specific queries

public class mySQlConnect 
{
   
   
    public static void mysqlQueryExecute (String query){
        Connection con = null;
        
       try{                       
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
            Statement stmt = con.createStatement();
            int sql = stmt.executeUpdate(query); // simply executes the query
            System.out.println("Deleted " + sql + " rows");
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }        
        
    }
    
    
    
    
    
    
    
     public static String[] mysqlReturnAllField(String table, String field){
        Connection con = null;
        
       try{                       
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
            String query = "select * from " + table;  // put the query here
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query); // ResultSet is a datatype that is able to store a mysql table (probably a collection of 2 or three dimensional arrays idk)
            myArrayList arr1 = new myArrayList(10); // creates the dynamic array
            
            int i = 0;
            while(rs.next()){     
                arr1.addItem(rs.getString(field)); // adds 
            }
           
            String[] tempArr = arr1.getArray();
            arr1.close(); // need to close these objects because it will take too much memory if let be. The java garbage collector could remove these objects but this will just ensure that the program will not crash
            rs.close();
            return tempArr;
                    
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }
            
        return null;
    }
     
     
    
     
    public static String mySQLQueryReturn_CompanyID (String Employee_id){ // returns the company id for calculating the money owed
     Connection con = null;
        
     try{                       
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
         Statement stmt = con.createStatement();
         ResultSet rs = null;
         rs = stmt.executeQuery("SELECT Employees. Company_list_Company_id \n" +
         "FROM Companies. Employees\n" +
         "WHERE Employees. Employee_id = " + Employee_id + ";");
            
         while(rs.next()) {  // NEED TO USE A WHILE LOOP BECAUSE: https://stackoverflow.com/questions/25879523/java-sql-sqlexception-before-start-of-result-set. if no while loop you will recieve a output: "Before start of result set"
             String a = rs.getString("Company_list_Company_id"); 
             rs.close(); // closes the arrays + objects 
             return a;
         }
         
           
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
            
     }
            
     return null;
        
        
    }
    public static String[] mySQLReturn_Bookingid (String Employee_id){ // returns the Booking id. Used for: calculating the number of times an employee has booked a facility for calculating money owed
     Connection con = null;
        
     try{                       
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
         Statement stmt = con.createStatement();
         ResultSet rs = null;
         rs = stmt.executeQuery("SELECT Facility_Bookings. Booking_id FROM Companies. Facility_Bookings WHERE Employee_id =" + Employee_id + ";");
         myArrayList arr1 = new myArrayList(10); // creates the dynamic array
            
         while(rs.next()){     
             arr1.addItem(rs.getString("Booking_id")); // adds to the dynamic array
                
         }
        
         rs.close();
         return arr1.getArray();

            
         
           
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
            
     }
            
     return null;
        
        
    }

    public static String mySQLnumberOfRows (String table){ // returns the company id for calculating the money owed
        Connection con = null;
           
        try{                       
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table + ";");
               
            while(rs.next()) {  // NEED TO USE A WHILE LOOP BECAUSE: https://stackoverflow.com/questions/25879523/java-sql-sqlexception-before-start-of-result-set. if no while loop you will recieve a output: "Before start of result set"
                String a = rs.getString("COUNT(*)"); 
                rs.close(); // closes the arrays + objects 
                return a;
            }
            
              
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
               
        }
               
        return null;
           
           
       }
    
    
    public static String[] mySQLReturnEmployeeTable(String field){
        Connection con = null;
        
       try{                       
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
            String query = "SELECT * FROM Employees;";  // put the query here
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query); // ResultSet is a datatype that is able to store a mysql table (probably a collection of 2 or three dimensional arrays idk)
            myArrayList arr1 = new myArrayList(10); // creates the dynamic array
            
            while(rs.next()){     
                arr1.addItem(rs.getString(field)); // adds 
            }
           
            String[] tempArr = arr1.getArray();
            arr1.close(); // need to close these objects because it will take too much memory if let be. The java garbage collector could remove these objects but this will just ensure that the program will not crash
            rs.close();
            return tempArr;
                    
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }
            
        return null;
        
        
        
        
    }

    
    
    
    
     
}
