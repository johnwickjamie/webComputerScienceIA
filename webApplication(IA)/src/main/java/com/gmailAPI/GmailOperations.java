package com.gmailAPI;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class GmailOperations {

  
private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); 
private static final String user = "me"; // current email with the access token
static Gmail service = null;
private static File filePath = new File(System.getProperty("user.dir") + "/credentials.json"); //finds the credentials.json file

  

// testing the class main method
/*
    public static void main(String[] args)throws FileNotFoundException, IOException, GeneralSecurityException, MessagingException {
        
        Gmail service = getGmailService();
        MimeMessage Mimessages = createEmailWithAttachment("jamiepeanutlover@gmail.com", "me", "QR code Access", "Here is your QR access code", new File("./QRCodes/frame-2.png")); // to change to just send a email with text, change the method here to "createEmail"
        Message message = createMessageWithEmail(Mimessages);
        
        service.users().messages().send("me", message).execute();
        
    }
*/




    public static void emailWithAttachmentBuilder (String toEmail, String body, File file)throws FileNotFoundException, IOException, GeneralSecurityException, MessagingException {
        Gmail service = getGmailService();
        MimeMessage Mimessages = createEmailWithAttachment(toEmail, "me", "QR code Access", body, file); // to change to just send a email with text, change the method here to "createEmail"
        Message message = createMessageWithEmail(Mimessages);
        
        service.users().messages().send("me", message).execute();
        
        
    }
    
    public static void emailBuilder (String toEmail, String body)throws FileNotFoundException, IOException, GeneralSecurityException, MessagingException {
        Gmail service = getGmailService();
        MimeMessage Mimessages = createEmail(toEmail, "me", "deez nuts", body); // to change to just send a email with text, change the method here to "createEmail"
        Message message = createMessageWithEmail(Mimessages);
        
        service.users().messages().send("me", message).execute();
        
        
    }

       
    // this method sends email with just text
    public static void sendMessage (Gmail service, String userId, MimeMessage email) throws MessagingException, IOException, FileNotFoundException, GeneralSecurityException{
        Message message = createMessageWithEmail(email);
		message = service.users().messages().send(userId, message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString()); 
        
    }
    
    public static Message createMessageWithEmail (MimeMessage email) throws MessagingException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
            email.writeTo(baos);
            String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
            Message message = new Message();
            message.setRaw(encodedEmail);
    return message;
        
        
    }




    public static Gmail getGmailService() throws FileNotFoundException, IOException, GeneralSecurityException {
        InputStream in = new FileInputStream(filePath); // Read credentials.json
	GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        // Credential builder

	Credential authorize = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
            .setJsonFactory(JSON_FACTORY)
            .setClientSecrets(clientSecrets.getDetails().getClientId().toString(),
		clientSecrets.getDetails().getClientSecret().toString())
            .build()
            .setAccessToken("ya29.a0AVA9y1uscl2OLaPSTJsO_CNnBWgpbvgOeqOcuxYZ_Kb7GrcYc8dGcZh6w4mXnmdB90vfxis3FaVrtd_a1Cp9el8DHB-CKtuO8K8osPlx0pbsoTDEX4QLvk9WuKN5X_VvNcCavX9-1z3Sr_dnviULc78KlW6SaCgYKATASAQASFQE65dr8EcQeSgisIAg_ZpPtU8JhEA0163")//Replace this
            .setRefreshToken("1//04UTqI7aK_AgGCgYIARAAGAQSNwF-L9Irctj35-JdBR_Po5ad0EXtV1vJpoESRLL4YJoev61ouSFPXDAt2sxk0JQ8AADJXVpa0lc");
         
        // create Gmail Service
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorize).setApplicationName(GmailOperations.APPLICATION_NAME).build();
         
         
		
     return service;            
    }
    
    
    
    
    public static MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from)); //me
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to)); //
		email.setSubject(subject); 

        email.setText(bodyText);
        
		return email;
	}

    
    public static void getEmailBody () throws IOException, FileNotFoundException, GeneralSecurityException{
         getGmailService();
        
        // creating and executing a search query
        
        Gmail.Users.Messages.List request = service.users().messages().list(user).setQ("from:" + "jamiepeanutlover@gmail.com");
        ListMessagesResponse messageResponse = request.execute();
        request.setPageToken(messageResponse.getNextPageToken());
        
        // get the ID for the message you are looking for
        
        String messageId = messageResponse.getMessages().get(0).getId();
        
        Message message = service.users().messages().get(user, messageId).execute();
        
        String emailBody = StringUtils
            .newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));

	System.out.println("Email body : " + emailBody);
          
        
    }
    
    
    public static MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText ,File file) throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from)); //me
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to)); //
		email.setSubject(subject); 
        
		MimeBodyPart mimeBodyPart = new MimeBodyPart(); 
		mimeBodyPart.setContent(bodyText, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);  // because we are sending text + a file we need to use multipart

		mimeBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(file);

		mimeBodyPart.setDataHandler(new DataHandler(source));
		mimeBodyPart.setFileName(file.getName());
		

		multipart.addBodyPart(mimeBodyPart);
		email.setContent(multipart,"text/html");
        
        
		return email;
	}
    
    


  
}