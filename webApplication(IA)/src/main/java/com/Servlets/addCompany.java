/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Servlets;

// READ THIS TO FIX PROBLEMS OF THE SERVLETS https://stackoverflow.com/questions/72492377/java-servlets-http-status-500-error-instantiating-servlet-class - 

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author jamesmcdonnell
 */
public class addCompany extends HttpServlet{
    
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
        
        String Company_name = req.getParameter("Company_Name");
        int Total_Employees = Integer.parseInt(req.getParameter("Total_Employees"));
        String Company_Email = req.getParameter("Company_Email");
        String query = "INSERT INTO Company_list (CompanyName, Total_Employees, Company_Emails) VALUES ('" + Company_name + "', '" + Total_Employees + "', '" + Company_Email + "')";
        
        Connection con = null;
           
        try{   
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Companies","james","170ilococomcdonnell");
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            System.out.println("SQL insertion query sent");
 
              
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
       
        
        
        
        
        
        
        
    }
    
    
    
}
}
