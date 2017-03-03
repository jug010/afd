/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afd.mail.ejb;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ryanrush
 */
@Stateless
public class MailSenderBean {

    public void sendMail(String name, String fromEmail, String phone, String message, 
                        String toEmail, String username, String password) {
        
        try {
            
            
            Properties props = System.getProperties();
            
            
            //FANTASTIC tutorial for all of these properties: https://www.tutorialspoint.com/javamail_api/javamail_api_smtp_servers.htm
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.advancedfloor.net");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587"); //updated to be port 587 instead of 465, per directions on network solutions site
            

            //commenting these out
            /* props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false"); */
            
            //more properties to fill out, suggested by https://www.tutorialspoint.com/javamail_api/javamail_api_authentication.htm
            props.put("mail.pop3s.host", "smtp.advancedfloor.net");
            props.put("mail.pop3s.port", "587");
            props.put("mail.pop3s.starttls.enable", "false");
            
           
            //Creating mail session and default message object - from the youtube video
            /* Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);
            Message mailMessage = new MimeMessage(mailSession); */
            
            
            //Creating mail session and default MIME message object - from https://www.tutorialspoint.com/java/java_sending_email.htm
            //Session mailSession = Session.getDefaultInstance(props, authenticator);
            
            // from https://www.tutorialspoint.com/javamail_api/javamail_api_authentication.htm and http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
            // this fixed it!!!
            Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(
                         username, password);
                   }
                });
            
            Message mailMessage = new MimeMessage(mailSession);

            mailMessage.setFrom(new InternetAddress(fromEmail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mailMessage.setContent(name + "<br />" + fromEmail + "<br />" + phone + "<br /><br />" + message, "text/html");
            mailMessage.setSubject("New email from Website");
            
            
            //Sending message with transport object - from https://www.tutorialspoint.com/java/java_sending_email.htm
            Transport.send(mailMessage);
            
            //Sending message with transport object - from youtube video ****THIS CODE DOESN'T FREAKING WORK!!!!********************
            //Transport transport = mailSession.getTransport("smtp");
            //transport.connect("smtp", username, password);
            
            //transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MailSenderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
