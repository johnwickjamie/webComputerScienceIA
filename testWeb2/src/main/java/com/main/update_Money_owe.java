/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.main;

import com.mySQL.mySQlConnect;



/**
 *
 * @author jamesmcdonnell
 */
public class update_Money_owe {
    
    

    
    public static void main(String[] args) {
     int i = 0;
     int x;
     int companyid;
     boolean b;
     
     int numOfCompanies = Integer.parseInt(mySQlConnect.mySQLnumberOfRows("Company_list")); // returns the number of companies there are in the database
     int moneyOWED [] = new int [numOfCompanies];
     
     
     String[] employee_id = mySQlConnect.mysqlReturnAllField("Employees", "Employee_id"); // returning the list of employee ids
     String[] companyID = mySQlConnect.mysqlReturnAllField("Company_list", "Company_id");
     

     
     while (employee_id[i] != null){
         companyid = Integer.parseInt(mySQlConnect.mySQLQueryReturn_CompanyID(employee_id[i])); // returns the company id the employee is apart of 
         String[] numOfBookings = mySQlConnect.mySQLReturn_Bookingid(employee_id[i]); // booking id is used to see how many bookings the employee has made
         
         x = 0;
         int totalNumOfBookings = 0;
         while (numOfBookings[x] != null){ // while loop used as some of the array list will be null so can't just find the length of the array
             totalNumOfBookings++;   
             x++;  
             
         }
         

         
            b = false;
             for (x = 0; x < numOfCompanies; x++){ // iterates through the company id array to find the mathing id and adds to the money owed array the cost of all the bookings
                 if (companyid == Integer.parseInt(companyID[x])){
                     moneyOWED[x] = (totalNumOfBookings * 7) + moneyOWED[x]; // one booking $7
                     System.out.println(totalNumOfBookings);
                    }     

             }  
             
         
         
     i++;    
     }
     
     for (x = 0; x < numOfCompanies; x++){ 
         System.out.println("Money owed: " + " | " + "Company ID: ");
         System.out.println(moneyOWED[x] + " | " + companyID[x]); 

     }
     

        
        
    }


    
}
