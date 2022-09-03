/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.main; 


/**
 *
 * @author jamesmcdonnell
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.mail.MessagingException;
import com.gmailAPI.GmailOperations;
import com.mySQL.mySQlConnect;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.sql.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class qr_send_to_employees {

  
    public static void main(String[] args) throws FileNotFoundException, IOException, GeneralSecurityException, MessagingException {
        
        mySQlConnect.mysqlQueryExecute("DELETE FROM qr_codes;");
 
        int min = 10000;
        int max = 99999;
        int i = 0;
        int random_int;
        //Generate random int value from 10000 to 99999 for the ACCESS CODE THAT WILL BE PLACED INSIDE THE QR 
        String[] Employee_id = mySQlConnect.mySQLReturnEmployeeTable("Employee_id");
        String[] Employee_email = mySQlConnect.mySQLReturnEmployeeTable("Email_Address");
        String[] Employee_name = mySQlConnect.mySQLReturnEmployeeTable("FirstName");
       
        while (Employee_email[i] != null){   

        random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        String qrData = Integer.toString(random_int); // need to convert to string as String format needed to be coded into qr
        
        makeQR(qrData); // modifying the QR code file, simply overrides the file insted of creating a new one
        sendmySQL(qrData, Employee_id[i]); // sending the generated integer to the mysql database along with the employee id 

        GmailOperations.emailWithAttachmentBuilder(Employee_email[i], "Hello " + Employee_name[i] + ". This is your QR Access code. DO NOT SHARE, SCREENSHOT OR FORWARD THIS EMAIL.", new File("./QRCodes/QR.png"));

        i++;
        }

        
       
        // test code       
        //GmailOperations.emailWithAttachmentBuilder("jamiepeanutlover@gmail.com", "Here is your QR access code", new File("./QRCodes/frame-2.png")); 
        
    }


    public static void makeQR(String qrData){
        String filePath = "/Users/jamesmcdonnell/NetBeansProjects/webComputerScienceIA/QRCodes/QR.png"; // filePath of the QR code
        try {
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200 , hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        } 

    }


    public static void sendmySQL(String qrData, String EmployeeID){
        String query = "INSERT INTO qr_codes (QR_data, Employees_Employee_id) VALUES ('" + qrData + "', '" + EmployeeID + "')";
        
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
   
    
    
    
    
    
    
    

