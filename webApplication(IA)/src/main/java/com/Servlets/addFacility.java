/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jamesmcdonnell
 */
public class addFacility extends HttpServlet{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
        
        String FacilityType = req.getParameter("FacilityType");
        String Bookable = req.getParameter("Bookable");
        String FacilityName = req.getParameter("FacilityName");
        String query = "INSERT INTO Facility_list (FacilityType, FacilityName,  Bookable) VALUES ('" + FacilityType + "', '" + FacilityName + "', '" + Bookable + "')";

        
        
        Connection con = null;
           
        try{   
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            System.out.println("SQL insertion query sent");
 
              
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
               
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
       
    }
    
}
