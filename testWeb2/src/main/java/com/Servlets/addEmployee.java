/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class addEmployee extends HttpServlet{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
        
        String FirstName = req.getParameter("FirstName");
        String lastName = req.getParameter("LastName");
        String Email_address = req.getParameter("Email_address");
        String password = req.getParameter("Password");
        int company_id = Integer.parseInt(req.getParameter("Company_id"));
        String query = "INSERT INTO Employees (FirstName, LastName, Email_Address, Password,  Company_list_Company_id) VALUES ('" + FirstName + "', '" + lastName + "', '" + Email_address + "', '" + password + "', '" + company_id + "')";
        
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
